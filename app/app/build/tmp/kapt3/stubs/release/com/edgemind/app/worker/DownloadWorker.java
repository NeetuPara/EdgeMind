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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/edgemind/app/worker/DownloadWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "createForegroundInfo", "Landroidx/work/ForegroundInfo;", "title", "", "progress", "", "createNotificationChannel", "", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class DownloadWorker extends androidx.work.CoroutineWorker {
    
    public DownloadWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters params) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final androidx.work.ForegroundInfo createForegroundInfo(java.lang.String title, int progress) {
        return null;
    }
}