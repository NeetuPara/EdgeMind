package com.edgemind.app.data

import android.graphics.Bitmap

/** Accelerator types for model execution */
enum class Accelerator(val label: String) {
    CPU("CPU"),
    GPU("GPU"),
}

/** A single model with all its metadata and runtime state */
data class Model(
    val name: String,
    val url: String,
    val downloadFileName: String,
    val sizeInBytes: Long,
    val info: String = "",
    val version: String = "",
    val llmSupportImage: Boolean = false,
    val llmSupportAudio: Boolean = false,
    val llmMaxToken: Int = 1024,
    val minDeviceMemoryInGb: Int = 6,
    val defaultTopK: Int = 64,
    val defaultTopP: Float = 0.95f,
    val defaultTemperature: Float = 1.0f,
    val accelerators: List<Accelerator> = listOf(Accelerator.GPU, Accelerator.CPU),
    val taskTypes: List<String> = listOf(),
    val bestForTaskTypes: List<String> = listOf(),
    val learnMoreUrl: String = "",
) {
    val normalizedName: String get() = name.replace(Regex("[^a-zA-Z0-9]"), "_")
    val totalBytes: Long get() = sizeInBytes
    val displayName: String get() = name

    val formattedSize: String
        get() {
            val gb = sizeInBytes / (1024.0 * 1024.0 * 1024.0)
            return if (gb >= 1.0) "%.1f GB".format(gb)
            else "%.0f MB".format(sizeInBytes / (1024.0 * 1024.0))
        }

    /** Runtime state — set after model is loaded */
    var instance: Any? = null
    var initializing: Boolean = false
}

/** Task definition */
data class Task(
    val id: String,
    val label: String,
    val description: String,
    val icon: String,
    val models: MutableList<Model> = mutableListOf(),
)

/** Built-in task IDs */
object TaskIds {
    const val LLM_CHAT = "llm_chat"
    const val LLM_ASK_IMAGE = "llm_ask_image"
    const val LLM_ASK_PDF = "llm_ask_pdf"
}

/** Model download status tracking */
enum class DownloadStatusType {
    NOT_DOWNLOADED,
    IN_PROGRESS,
    PARTIALLY_DOWNLOADED,
    SUCCEEDED,
    FAILED,
    UNZIPPING,
}

data class DownloadStatus(
    val status: DownloadStatusType = DownloadStatusType.NOT_DOWNLOADED,
    val totalBytes: Long = 0,
    val receivedBytes: Long = 0,
    val bytesPerSecond: Long = 0,
    val remainingMs: Long = 0,
    val errorMessage: String = "",
) {
    val progressPercent: Float
        get() = if (totalBytes > 0) receivedBytes.toFloat() / totalBytes else 0f

    val formattedSpeed: String
        get() {
            val mbps = bytesPerSecond / (1024.0 * 1024.0)
            return "%.1f MB/s".format(mbps)
        }
}

/** Constants for WorkManager data keys */
object WorkerKeys {
    const val KEY_MODEL_NAME = "model_name"
    const val KEY_MODEL_URL = "model_url"
    const val KEY_MODEL_COMMIT_HASH = "model_commit_hash"
    const val KEY_MODEL_DIR = "model_dir"
    const val KEY_MODEL_FILE_NAME = "model_file_name"
    const val KEY_MODEL_TOTAL_BYTES = "model_total_bytes"
    const val KEY_ACCESS_TOKEN = "access_token"
    const val KEY_RECEIVED_BYTES = "received_bytes"
    const val KEY_DOWNLOAD_RATE = "download_rate"
    const val KEY_REMAINING_MS = "remaining_ms"
    const val KEY_ERROR_MESSAGE = "error_message"
}
