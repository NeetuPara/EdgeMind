package com.edgemind.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.edgemind.app.data.*
import com.edgemind.app.worker.DownloadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

private const val TAG = "ModelManagerVM"

data class ModelManagerUiState(
    val allModels: List<Model> = emptyList(),
    val tasks: List<Task> = emptyList(),
    val downloadStatuses: Map<String, DownloadStatus> = emptyMap(),
    val initStatuses: Map<String, String> = emptyMap(),  // "not_init", "initializing", "ready", "error"
    val loading: Boolean = true,
)

@HiltViewModel
class ModelManagerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ModelManagerUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadModels()
    }

    private fun loadModels() {
        viewModelScope.launch(Dispatchers.IO) {
            val allModels = loadModelAllowlist(context)
            Log.d(TAG, "Loaded ${allModels.size} models from allowlist")

            val chatTask = Task(
                id = TaskIds.LLM_CHAT,
                label = "AI Chat",
                description = "Have text conversations with on-device AI models",
                icon = "chat",
                models = allModels.filter { TaskIds.LLM_CHAT in it.taskTypes }.toMutableList(),
            )

            val visionTask = Task(
                id = TaskIds.LLM_ASK_IMAGE,
                label = "Ask Image",
                description = "Upload images and ask AI to analyze them",
                icon = "image",
                models = allModels.filter { it.llmSupportImage }.toMutableList(),
            )

            val pdfTask = Task(
                id = TaskIds.LLM_ASK_PDF,
                label = "Ask PDF",
                description = "Upload PDF documents and ask questions about them",
                icon = "description",
                models = allModels.filter { TaskIds.LLM_ASK_PDF in it.taskTypes }.toMutableList(),
            )

            val downloadStatuses = mutableMapOf<String, DownloadStatus>()
            for (model in allModels) {
                val modelFile = getModelFile(model)
                downloadStatuses[model.name] = if (modelFile.exists()) {
                    DownloadStatus(status = DownloadStatusType.SUCCEEDED, totalBytes = model.sizeInBytes, receivedBytes = model.sizeInBytes)
                } else {
                    DownloadStatus()
                }
            }

            _uiState.update {
                it.copy(
                    allModels = allModels,
                    tasks = listOf(chatTask, visionTask, pdfTask),
                    downloadStatuses = downloadStatuses,
                    loading = false,
                )
            }
        }
    }

    fun getModelsForTask(taskId: String): List<Model> {
        return _uiState.value.tasks.find { it.id == taskId }?.models ?: emptyList()
    }

    fun downloadModel(model: Model) {
        viewModelScope.launch {
            val hfToken = context.getHfToken()
            val dir = model.normalizedName
            val workData = Data.Builder()
                .putString(WorkerKeys.KEY_MODEL_NAME, model.name)
                .putString(WorkerKeys.KEY_MODEL_URL, model.url)
                .putString(WorkerKeys.KEY_MODEL_COMMIT_HASH, model.version)
                .putString(WorkerKeys.KEY_MODEL_DIR, dir)
                .putString(WorkerKeys.KEY_MODEL_FILE_NAME, model.downloadFileName)
                .putLong(WorkerKeys.KEY_MODEL_TOTAL_BYTES, model.sizeInBytes)
                .apply { if (hfToken.isNotEmpty()) putString(WorkerKeys.KEY_ACCESS_TOKEN, hfToken) }
                .build()

        val request = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(workData)
            .addTag("download_${model.name}")
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()

        setDownloadStatus(model.name, DownloadStatus(status = DownloadStatusType.IN_PROGRESS, totalBytes = model.sizeInBytes))

        WorkManager.getInstance(context).enqueueUniqueWork(
            "download_${model.name}",
            ExistingWorkPolicy.KEEP,
            request,
        )

        // Observe progress
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(request.id)
            .observeForever { info ->
                if (info == null) return@observeForever

                when (info.state) {
                    WorkInfo.State.RUNNING -> {
                        val received = info.progress.getLong(WorkerKeys.KEY_RECEIVED_BYTES, 0)
                        val rate = info.progress.getLong(WorkerKeys.KEY_DOWNLOAD_RATE, 0)
                        val remaining = info.progress.getLong(WorkerKeys.KEY_REMAINING_MS, 0)
                        if (received > 0) {
                            setDownloadStatus(model.name, DownloadStatus(
                                status = DownloadStatusType.IN_PROGRESS,
                                totalBytes = model.sizeInBytes,
                                receivedBytes = received,
                                bytesPerSecond = rate,
                                remainingMs = remaining,
                            ))
                        }
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        setDownloadStatus(model.name, DownloadStatus(
                            status = DownloadStatusType.SUCCEEDED,
                            totalBytes = model.sizeInBytes,
                            receivedBytes = model.sizeInBytes,
                        ))
                    }
                    WorkInfo.State.FAILED -> {
                        val error = info.outputData.getString(WorkerKeys.KEY_ERROR_MESSAGE) ?: "Download failed"
                        setDownloadStatus(model.name, DownloadStatus(
                            status = DownloadStatusType.FAILED,
                            errorMessage = error,
                        ))
                    }
                    WorkInfo.State.CANCELLED -> {
                        setDownloadStatus(model.name, DownloadStatus(status = DownloadStatusType.NOT_DOWNLOADED))
                    }
                    else -> {}
                }
            }
        }
    }

    fun cancelDownload(model: Model) {
        WorkManager.getInstance(context).cancelUniqueWork("download_${model.name}")
        setDownloadStatus(model.name, DownloadStatus(status = DownloadStatusType.NOT_DOWNLOADED))
    }

    fun deleteModel(model: Model) {
        viewModelScope.launch(Dispatchers.IO) {
            val dir = File(context.getExternalFilesDir(null), "${model.normalizedName}/${model.version}")
            dir.deleteRecursively()
            setDownloadStatus(model.name, DownloadStatus(status = DownloadStatusType.NOT_DOWNLOADED))
        }
    }

    fun initializeModel(
        model: Model,
        taskId: String,
        onDone: () -> Unit,
        onError: (String) -> Unit,
    ) {
        setInitStatus(model.name, "initializing")
        viewModelScope.launch(Dispatchers.Default) {
            LlmChatModelHelper.initialize(
                context = context,
                model = model,
                supportImage = taskId == TaskIds.LLM_ASK_IMAGE || taskId == TaskIds.LLM_ASK_PDF,
                onDone = { error ->
                    if (error.isEmpty()) {
                        setInitStatus(model.name, "ready")
                        onDone()
                    } else {
                        setInitStatus(model.name, "error")
                        onError(error)
                    }
                },
            )
        }
    }

    fun cleanupModel(model: Model) {
        LlmChatModelHelper.cleanUp(model)
        setInitStatus(model.name, "not_init")
    }

    private fun setDownloadStatus(modelName: String, status: DownloadStatus) {
        _uiState.update {
            it.copy(downloadStatuses = it.downloadStatuses.toMutableMap().apply { put(modelName, status) })
        }
    }

    private fun setInitStatus(modelName: String, status: String) {
        _uiState.update {
            it.copy(initStatuses = it.initStatuses.toMutableMap().apply { put(modelName, status) })
        }
    }

    private fun getModelFile(model: Model): File {
        return File(context.getExternalFilesDir(null), "${model.normalizedName}/${model.version}/${model.downloadFileName}")
    }
}
