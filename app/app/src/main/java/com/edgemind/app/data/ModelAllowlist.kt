package com.edgemind.app.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * JSON model for parsing model_allowlist.json
 */
data class DefaultConfig(
    @SerializedName("topK") val topK: Int?,
    @SerializedName("topP") val topP: Float?,
    @SerializedName("temperature") val temperature: Float?,
    @SerializedName("accelerators") val accelerators: String?,
    @SerializedName("maxTokens") val maxTokens: Int?,
)

data class AllowedModel(
    val name: String,
    val modelId: String,
    val modelFile: String,
    val description: String,
    val sizeInBytes: Long,
    val commitHash: String,
    val defaultConfig: DefaultConfig,
    val taskTypes: List<String>,
    val llmSupportImage: Boolean? = null,
    val llmSupportAudio: Boolean? = null,
    val minDeviceMemoryInGb: Int? = null,
    val bestForTaskTypes: List<String>? = null,
) {
    fun toModel(): Model {
        val downloadUrl =
            "https://huggingface.co/$modelId/resolve/$commitHash/$modelFile?download=true"

        val accelList = mutableListOf<Accelerator>()
        defaultConfig.accelerators?.split(",")?.forEach { item ->
            when (item.trim()) {
                "cpu" -> accelList.add(Accelerator.CPU)
                "gpu" -> accelList.add(Accelerator.GPU)
            }
        }

        return Model(
            name = name,
            url = downloadUrl,
            downloadFileName = modelFile,
            sizeInBytes = sizeInBytes,
            info = description,
            version = commitHash,
            llmSupportImage = llmSupportImage == true,
            llmSupportAudio = llmSupportAudio == true,
            llmMaxToken = defaultConfig.maxTokens ?: 1024,
            minDeviceMemoryInGb = minDeviceMemoryInGb ?: 6,
            defaultTopK = defaultConfig.topK ?: 64,
            defaultTopP = defaultConfig.topP ?: 0.95f,
            defaultTemperature = defaultConfig.temperature ?: 1.0f,
            accelerators = accelList.ifEmpty { listOf(Accelerator.GPU, Accelerator.CPU) },
            taskTypes = taskTypes,
            bestForTaskTypes = bestForTaskTypes ?: listOf(),
            learnMoreUrl = "https://huggingface.co/$modelId",
        )
    }
}

data class ModelAllowlist(val models: List<AllowedModel>)

/**
 * Load model allowlist from assets/model_allowlist.json
 */
fun loadModelAllowlist(context: Context): List<Model> {
    return try {
        val jsonStr = context.assets.open("model_allowlist.json")
            .bufferedReader().use { it.readText() }
        val allowlist = Gson().fromJson(jsonStr, ModelAllowlist::class.java)
        allowlist.models
            .filter { it.taskTypes.any { t -> t == TaskIds.LLM_CHAT || t == TaskIds.LLM_ASK_IMAGE || t == TaskIds.LLM_ASK_PDF } }
            .map { it.toModel() }
    } catch (e: Exception) {
        emptyList()
    }
}
