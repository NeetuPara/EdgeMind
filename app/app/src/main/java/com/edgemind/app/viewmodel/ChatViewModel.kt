package com.edgemind.app.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edgemind.app.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.UUID
import javax.inject.Inject

private const val TAG = "ChatViewModel"

enum class AttachmentType { IMAGE, PDF, DOCUMENT, TEXT }

data class Attachment(
    val path: String,
    val name: String,
    val type: AttachmentType,
)

data class UiChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val role: String,       // "user" | "assistant"
    val content: String,
    val imagePath: String? = null,
    val fileName: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
)

data class ChatUiState(
    val messages: List<UiChatMessage> = listOf(
        UiChatMessage(role = "assistant", content = "Hello! How can I help you today?")
    ),
    val isGenerating: Boolean = false,
    val isPreparing: Boolean = false,
    val currentSessionId: String? = null,
    val sessions: List<ChatSession> = emptyList(),
    val activeModel: Model? = null,
    val activeTaskId: String? = null,
    val error: String? = null,
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val chatDao: ChatDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    fun setActiveModel(model: Model, taskId: String) {
        _uiState.update { it.copy(activeModel = model, activeTaskId = taskId) }
        loadSessionsForModel(model.name)
    }

    fun setModelLoading(loading: Boolean) {
        _uiState.update { it.copy(isPreparing = loading) }
    }

    fun setError(error: String) {
        _uiState.update { it.copy(error = error) }
    }

    private fun loadSessionsForModel(modelName: String) {
        viewModelScope.launch {
            chatDao.getSessionsForModel(modelName).collect { sessions ->
                _uiState.update { it.copy(sessions = sessions) }
            }
        }
    }

    fun loadSession(sessionId: String) {
        viewModelScope.launch {
            val messages = chatDao.getMessagesForSessionSync(sessionId)
            _uiState.update {
                it.copy(
                    currentSessionId = sessionId,
                    messages = messages.map { msg ->
                        UiChatMessage(
                            id = msg.id,
                            role = msg.role,
                            content = msg.content,
                            imagePath = msg.imagePath,
                            timestamp = msg.timestamp,
                        )
                    }.ifEmpty {
                        listOf(UiChatMessage(role = "assistant", content = "Hello! How can I help you today?"))
                    },
                )
            }
        }
    }

    fun startNewChat() {
        _uiState.update {
            it.copy(
                currentSessionId = null,
                messages = listOf(UiChatMessage(role = "assistant", content = "Hello! How can I help you today?")),
                error = null,
            )
        }
    }

    fun deleteSession(sessionId: String) {
        viewModelScope.launch {
            chatDao.deleteMessagesForSession(sessionId)
            chatDao.deleteSession(sessionId)
            if (_uiState.value.currentSessionId == sessionId) {
                startNewChat()
            }
        }
    }

    fun sendMessage(
        text: String,
        attachments: List<Attachment> = emptyList(),
    ) {
        val model = _uiState.value.activeModel ?: return
        val taskId = _uiState.value.activeTaskId ?: return

        // Process attachments
        val images = mutableListOf<Bitmap>()
        val fileTexts = mutableListOf<String>()
        var imagePath: String? = null
        var fileName: String? = null

        for (attachment in attachments) {
            when (attachment.type) {
                AttachmentType.IMAGE -> {
                    val bmp = BitmapFactory.decodeFile(attachment.path)
                    if (bmp != null) {
                        images.add(bmp)
                        if (imagePath == null) imagePath = attachment.path
                    }
                }
                AttachmentType.PDF -> {
                    fileName = attachment.name
                    val extractedImages = extractPdfImages(appContext, attachment.path)
                    if (extractedImages.isNotEmpty()) {
                        images.addAll(extractedImages)
                        fileTexts.add("[Attached PDF: ${attachment.name} with ${extractedImages.size} pages]")
                    } else {
                        fileTexts.add("[Error reading PDF: ${attachment.name}]")
                    }
                }
                AttachmentType.TEXT -> {
                    fileName = attachment.name
                    val content = readTextFile(attachment.path)
                    if (content.isNotEmpty()) {
                        fileTexts.add("[Content from ${attachment.name}]:\n$content")
                    }
                }
                AttachmentType.DOCUMENT -> {
                    fileName = attachment.name
                    fileTexts.add("[File: ${attachment.name} — DOCX parsing not yet supported, text extraction only]")
                }
            }
        }

        // Build final prompt: user text + extracted file contents
        val fullPrompt = buildString {
            if (fileTexts.isNotEmpty()) {
                append(fileTexts.joinToString("\n\n"))
                if (text.isNotBlank()) {
                    append("\n\n")
                    append(text)
                }
            } else {
                append(text)
            }
        }

        val displayText = if (text.isNotBlank()) text else "📎 ${attachments.joinToString(", ") { it.name }}"

        // Add user message
        val userMsg = UiChatMessage(
            role = "user",
            content = displayText,
            imagePath = imagePath,
            fileName = if (fileName != null && imagePath == null) fileName else null,
        )

        _uiState.update {
            it.copy(
                messages = it.messages + userMsg,
                isGenerating = true,
                isPreparing = true,
                error = null,
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            // Ensure session exists
            var sessionId = _uiState.value.currentSessionId
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString()
                val title = if (displayText.length > 30) "${displayText.take(30)}..." else displayText
                chatDao.insertSession(ChatSession(
                    id = sessionId,
                    title = title,
                    modelName = model.name,
                    taskId = taskId,
                ))
                _uiState.update { it.copy(currentSessionId = sessionId) }
            }

            // Save user message to DB
            chatDao.insertMessage(ChatMessage(
                id = userMsg.id,
                sessionId = sessionId,
                role = "user",
                content = displayText,
                imagePath = imagePath,
            ))

            // Add empty assistant message for streaming
            val assistantId = UUID.randomUUID().toString()
            _uiState.update {
                it.copy(
                    messages = it.messages + UiChatMessage(id = assistantId, role = "assistant", content = ""),
                    isPreparing = false,
                )
            }

            // Run inference
            val responseBuffer = StringBuilder()

            fun doInference() {
                LlmChatModelHelper.runInference(
                    model = model,
                    input = fullPrompt,
                    images = images,
                    resultListener = { partialResult, done ->
                        responseBuffer.append(partialResult)
                        _uiState.update { state ->
                            state.copy(
                                messages = state.messages.map { msg ->
                                    if (msg.id == assistantId) msg.copy(content = responseBuffer.toString())
                                    else msg
                                },
                                isGenerating = !done,
                            )
                        }
                        if (done) {
                            viewModelScope.launch(Dispatchers.IO) {
                                chatDao.insertMessage(ChatMessage(
                                    id = assistantId,
                                    sessionId = sessionId!!,
                                    role = "assistant",
                                    content = responseBuffer.toString(),
                                ))
                            }
                        }
                    },
                    cleanUpListener = {
                        _uiState.update { it.copy(isGenerating = false) }
                    },
                    onError = { errorMsg ->
                        _uiState.update { it.copy(isGenerating = false, error = errorMsg) }
                    },
                )
            }

            try {
                doInference()
            } catch (e: Exception) {
                Log.e(TAG, "Inference error", e)
                _uiState.update { it.copy(isGenerating = false, error = e.message) }
            }
        }
    }

    /**
     * Renders a PDF file into a list of Bitmaps using Android's native PdfRenderer.
     * This allows Vision models to "see" the layout (tables, charts) rather than just reading raw text.
     */
    private fun extractPdfImages(context: Context, path: String): List<Bitmap> {
        val bitmaps = mutableListOf<Bitmap>()
        var fileDescriptor: ParcelFileDescriptor? = null
        var pdfRenderer: PdfRenderer? = null
        
        try {
            val file = File(path)
            fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            pdfRenderer = PdfRenderer(fileDescriptor)
            
            // Limit to first 3 pages to prevent Out-Of-Memory errors on mobile
            val pageCount = minOf(pdfRenderer.pageCount, 3) 
            
            for (i in 0 until pageCount) {
                val page = pdfRenderer.openPage(i)
                // Render at a high enough resolution for the AI to read text (~1080p width)
                val width = 1080
                val height = (width.toFloat() / page.width * page.height).toInt()
                
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                // Fill with white background (PDFs are transparent by default)
                bitmap.eraseColor(android.graphics.Color.WHITE)
                
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                bitmaps.add(bitmap)
                page.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error rendering PDF to image", e)
        } finally {
            pdfRenderer?.close()
            fileDescriptor?.close()
        }
        return bitmaps
    }

    private fun readTextFile(path: String): String {
        return try {
            val reader = BufferedReader(FileReader(path))
            val content = reader.readText()
            reader.close()
            if (content.length > 10000) content.take(10000) + "\n[...truncated]" else content
        } catch (e: Exception) {
            Log.e(TAG, "Text file read error", e)
            "Error reading file: ${e.message}"
        }
    }

    fun stopGeneration() {
        val model = _uiState.value.activeModel ?: return
        val instance = model.instance as? LlmModelInstance ?: return
        try {
            instance.conversation.cancelProcess()
        } catch (_: Exception) {}
        _uiState.update { it.copy(isGenerating = false) }
    }

    fun resetSession() {
        val model = _uiState.value.activeModel ?: return
        val taskId = _uiState.value.activeTaskId ?: return
        LlmChatModelHelper.resetConversation(model)
        startNewChat()
    }
}
