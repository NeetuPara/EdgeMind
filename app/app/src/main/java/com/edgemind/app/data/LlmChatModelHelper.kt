package com.edgemind.app.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.ai.edge.litertlm.Backend
import com.google.ai.edge.litertlm.Content
import com.google.ai.edge.litertlm.Contents
import com.google.ai.edge.litertlm.Conversation
import com.google.ai.edge.litertlm.ConversationConfig
import com.google.ai.edge.litertlm.Engine
import com.google.ai.edge.litertlm.EngineConfig
import com.google.ai.edge.litertlm.Message
import com.google.ai.edge.litertlm.MessageCallback
import com.google.ai.edge.litertlm.SamplerConfig
import java.io.ByteArrayOutputStream
import java.io.File

private const val TAG = "LlmHelper"

typealias ResultListener = (partialResult: String, done: Boolean) -> Unit
typealias CleanUpListener = () -> Unit

data class LlmModelInstance(
    val engine: Engine,
    val conversation: Conversation,
    val backend: Backend,
    val visionEnabled: Boolean = false,
)

/**
 * Inference helper wrapping LiteRT LM SDK.
 * Design matches Edge Gallery's LlmChatModelHelper:
 *  - No OpenCL file probing (unreliable on Exynos)
 *  - Try configured backend(s) in order, fall back cleanly
 *  - visionBackend is ALWAYS GPU (SDK requirement), never CPU
 */
object LlmChatModelHelper {

    fun initialize(
        context: Context,
        model: Model,
        supportImage: Boolean = false,
        onDone: (error: String) -> Unit,
    ) {
        val dir = context.getExternalFilesDir(null)
        val modelPath = File(dir, "${model.normalizedName}/${model.version}/${model.downloadFileName}").absolutePath

        val preferredBackend = if (model.accelerators.contains(Accelerator.GPU)) Backend.GPU else Backend.CPU

        Log.d(TAG, "Initializing '${model.name}' preferredBackend=$preferredBackend supportImage=$supportImage")

        try {
            // Match EdgeGallery: visionBackend is GPU if supportImage is true, otherwise null
            val visionBackend = if (supportImage) Backend.GPU else null

            val engineConfig = EngineConfig(
                modelPath = modelPath,
                backend = preferredBackend,
                visionBackend = visionBackend,
                maxNumTokens = model.llmMaxToken,
            )

            val initStart = System.currentTimeMillis()
            val engine = Engine(engineConfig)
            engine.initialize()
            val initMs = System.currentTimeMillis() - initStart
            Log.d(TAG, "Engine.initialize() took ${initMs}ms, backend=$preferredBackend")

            val conversation = engine.createConversation(
                ConversationConfig(
                    samplerConfig = SamplerConfig(
                        topK = model.defaultTopK,
                        topP = model.defaultTopP.toDouble(),
                        temperature = model.defaultTemperature.toDouble(),
                    ),
                ),
            )

            val visionEnabled = supportImage
            model.instance = LlmModelInstance(
                engine = engine,
                conversation = conversation,
                backend = preferredBackend,
                visionEnabled = visionEnabled,
            )
            Log.d(TAG, "✅ '${model.name}' ready: backend=$preferredBackend, visionEnabled=$visionEnabled")
            onDone("")

        } catch (e: Exception) {
            val errorMsg = e.message ?: "Failed to initialize model"
            Log.e(TAG, "Backend $preferredBackend failed during initialize: $errorMsg")
            onDone(errorMsg)
        }
    }

    fun runInference(
        model: Model,
        input: String,
        resultListener: ResultListener,
        cleanUpListener: CleanUpListener,
        onError: (message: String) -> Unit = {},
        images: List<Bitmap> = listOf(),
    ) {
        val instance = model.instance as? LlmModelInstance
        if (instance == null) {
            onError("Model not initialized")
            return
        }

        Log.d(TAG, "runInference '${model.name}' backend=${instance.backend} visionEnabled=${instance.visionEnabled} images=${images.size} input='${input.take(60)}'")

        if (images.isNotEmpty() && !instance.visionEnabled) {
            Log.w(TAG, "⚠️ Vision not available")
            onError("⚠️ Vision is not enabled for this model.")
            return
        }

        val conversation = instance.conversation

        val contents = mutableListOf<Content>()
        for (image in images) {
            val stream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, stream)
            Log.d(TAG, "Image added: ${stream.size()} bytes")
            contents.add(Content.ImageBytes(stream.toByteArray()))
        }
        if (input.trim().isNotEmpty()) {
            contents.add(Content.Text(input))
        }

        val inferStart = System.currentTimeMillis()
        var firstTokenMs = 0L

        try {
            conversation.sendMessageAsync(
                Contents.of(contents),
                object : MessageCallback {
                    override fun onMessage(message: Message) {
                        if (firstTokenMs == 0L) {
                            firstTokenMs = System.currentTimeMillis() - inferStart
                            Log.d(TAG, "⏱ First token: ${firstTokenMs}ms (backend=${instance.backend})")
                        }
                        resultListener(message.toString(), false)
                    }

                    override fun onDone() {
                        val totalMs = System.currentTimeMillis() - inferStart
                        Log.d(TAG, "⏱ Inference done: ${totalMs}ms")
                        resultListener("", true)
                    }

                    override fun onError(throwable: Throwable) {
                        val msg = throwable.message ?: "Inference error"
                        Log.e(TAG, "Inference error: $msg")
                        onError(msg)
                        cleanUpListener()
                    }
                },
            )
        } catch (e: Exception) {
            val msg = e.message ?: "sendMessageAsync failed"
            Log.e(TAG, "sendMessageAsync threw: $msg", e)
            onError(msg)
        }
    }

    fun resetConversation(model: Model) {
        val instance = model.instance as? LlmModelInstance ?: return
        try {
            val conversation = instance.engine.createConversation(
                ConversationConfig(
                    samplerConfig = SamplerConfig(
                        topK = model.defaultTopK,
                        topP = model.defaultTopP.toDouble(),
                        temperature = model.defaultTemperature.toDouble(),
                    ),
                ),
            )
            model.instance = instance.copy(conversation = conversation)
            Log.d(TAG, "Conversation reset for '${model.name}'")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to reset conversation: ${e.message}")
        }
    }

    fun cleanUp(model: Model) {
        val instance = model.instance as? LlmModelInstance ?: return
        try { instance.conversation.close() } catch (_: Exception) {}
        try { instance.engine.close() } catch (_: Exception) {}
        model.instance = null
        Log.d(TAG, "Cleaned up '${model.name}'")
    }

    fun isGpuAvailable(model: Model): Boolean {
        return (model.instance as? LlmModelInstance)?.backend == Backend.GPU
    }
}
