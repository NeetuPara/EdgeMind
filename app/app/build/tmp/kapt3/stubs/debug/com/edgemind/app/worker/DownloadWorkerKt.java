package com.edgemind.app.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.WorkerParameters;
import com.edgemind.app.data.WorkerKeys;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"CHANNEL_ID", "", "NOTIFICATION_ID", "", "TAG", "TMP_EXT", "app_debug"})
public final class DownloadWorkerKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "DownloadWorker";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "model_download";
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TMP_EXT = ".edgemindtmp";
}