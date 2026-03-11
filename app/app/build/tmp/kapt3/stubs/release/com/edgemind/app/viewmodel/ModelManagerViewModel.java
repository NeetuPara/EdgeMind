package com.edgemind.app.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.work.*;
import com.edgemind.app.data.*;
import com.edgemind.app.worker.DownloadWorker;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\u0006\u0010\u0017\u001a\u00020\u0018J8\u0010\u0019\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\r0\u001dJ\b\u0010\u001e\u001a\u00020\rH\u0002J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010#\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0018H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006$"}, d2 = {"Lcom/edgemind/app/viewmodel/ModelManagerViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/edgemind/app/viewmodel/ModelManagerUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "cancelDownload", "", "model", "Lcom/edgemind/app/data/Model;", "cleanupModel", "deleteModel", "downloadModel", "getModelFile", "Ljava/io/File;", "getModelsForTask", "", "taskId", "", "initializeModel", "onDone", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "loadModels", "setDownloadStatus", "modelName", "status", "Lcom/edgemind/app/data/DownloadStatus;", "setInitStatus", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ModelManagerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.edgemind.app.viewmodel.ModelManagerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.edgemind.app.viewmodel.ModelManagerUiState> uiState = null;
    
    @javax.inject.Inject()
    public ModelManagerViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.edgemind.app.viewmodel.ModelManagerUiState> getUiState() {
        return null;
    }
    
    private final void loadModels() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Model> getModelsForTask(@org.jetbrains.annotations.NotNull()
    java.lang.String taskId) {
        return null;
    }
    
    public final void downloadModel(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    public final void cancelDownload(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    public final void deleteModel(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    public final void initializeModel(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    public final void cleanupModel(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    private final void setDownloadStatus(java.lang.String modelName, com.edgemind.app.data.DownloadStatus status) {
    }
    
    private final void setInitStatus(java.lang.String modelName, java.lang.String status) {
    }
    
    private final java.io.File getModelFile(com.edgemind.app.data.Model model) {
        return null;
    }
}