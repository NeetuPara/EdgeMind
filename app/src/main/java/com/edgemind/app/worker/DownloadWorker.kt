package com.edgemind.app.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.edgemind.app.data.WorkerKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "DownloadWorker"
private const val CHANNEL_ID = "model_download"
private const val NOTIFICATION_ID = 1001
private const val TMP_EXT = ".edgemindtmp"

class DownloadWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val modelName = inputData.getString(WorkerKeys.KEY_MODEL_NAME) ?: return@withContext Result.failure()
        val fileUrl = inputData.getString(WorkerKeys.KEY_MODEL_URL) ?: return@withContext Result.failure()
        val modelDir = inputData.getString(WorkerKeys.KEY_MODEL_DIR) ?: return@withContext Result.failure()
        val fileName = inputData.getString(WorkerKeys.KEY_MODEL_FILE_NAME) ?: return@withContext Result.failure()
        val commitHash = inputData.getString(WorkerKeys.KEY_MODEL_COMMIT_HASH) ?: ""
        val totalBytes = inputData.getLong(WorkerKeys.KEY_MODEL_TOTAL_BYTES, 0L)
        val accessToken = inputData.getString(WorkerKeys.KEY_ACCESS_TOKEN)

        try {
            createNotificationChannel()
            setForeground(createForegroundInfo("Downloading $modelName...", 0))

            val externalDir = applicationContext.getExternalFilesDir(null) ?: return@withContext Result.failure()
            val targetDir = File(externalDir, "$modelDir/$commitHash")
            targetDir.mkdirs()

            val targetFile = File(targetDir, fileName)
            val tmpFile = File(targetDir, "$fileName$TMP_EXT")

            // Support resume
            var downloadedBytes = if (tmpFile.exists()) tmpFile.length() else 0L

            val connection = URL(fileUrl).openConnection() as HttpURLConnection
            if (!accessToken.isNullOrEmpty()) {
                Log.d(TAG, "Using HuggingFace access token")
                connection.setRequestProperty("Authorization", "Bearer $accessToken")
            }
            if (downloadedBytes > 0) {
                connection.setRequestProperty("Range", "bytes=$downloadedBytes-")
            }
            connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode != 200 && responseCode != 206) {
                return@withContext Result.failure(
                    Data.Builder().putString(WorkerKeys.KEY_ERROR_MESSAGE, "HTTP $responseCode").build()
                )
            }

            val inputStream = connection.inputStream
            val outputStream = FileOutputStream(tmpFile, downloadedBytes > 0)
            val buffer = ByteArray(8192)
            var bytesRead: Int
            var lastUpdateTime = System.currentTimeMillis()
            var lastUpdateBytes = downloadedBytes

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                if (isStopped) {
                    inputStream.close()
                    outputStream.close()
                    return@withContext Result.failure()
                }

                outputStream.write(buffer, 0, bytesRead)
                downloadedBytes += bytesRead

                val now = System.currentTimeMillis()
                if (now - lastUpdateTime >= 200) {
                    val elapsed = (now - lastUpdateTime).coerceAtLeast(1)
                    val rate = ((downloadedBytes - lastUpdateBytes) * 1000) / elapsed
                    val remaining = if (rate > 0 && totalBytes > 0) {
                        ((totalBytes - downloadedBytes) * 1000) / rate
                    } else 0L

                    setProgress(
                        Data.Builder()
                            .putLong(WorkerKeys.KEY_RECEIVED_BYTES, downloadedBytes)
                            .putLong(WorkerKeys.KEY_DOWNLOAD_RATE, rate)
                            .putLong(WorkerKeys.KEY_REMAINING_MS, remaining)
                            .build()
                    )

                    val percent = if (totalBytes > 0) ((downloadedBytes * 100) / totalBytes).toInt() else 0
                    setForeground(createForegroundInfo("Downloading $modelName... $percent%", percent))

                    lastUpdateTime = now
                    lastUpdateBytes = downloadedBytes
                }
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

            // Rename temp file to final
            tmpFile.renameTo(targetFile)
            Log.d(TAG, "Download complete: ${targetFile.absolutePath}")

            Result.success()
        } catch (e: IOException) {
            Log.e(TAG, "Download failed: ${e.message}", e)
            Result.failure(
                Data.Builder().putString(WorkerKeys.KEY_ERROR_MESSAGE, e.message ?: "Download error").build()
            )
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID, "Model Downloads", NotificationManager.IMPORTANCE_LOW
        )
        val manager = applicationContext.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun createForegroundInfo(title: String, progress: Int): ForegroundInfo {
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setProgress(100, progress, progress == 0)
            .setOngoing(true)
            .build()
        return ForegroundInfo(
            NOTIFICATION_ID,
            notification,
            android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC,
        )
    }
}
