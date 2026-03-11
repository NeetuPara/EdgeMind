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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u00c6\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\rH\u00c6\u0003J_\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\tH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/edgemind/app/viewmodel/ModelManagerUiState;", "", "allModels", "", "Lcom/edgemind/app/data/Model;", "tasks", "Lcom/edgemind/app/data/Task;", "downloadStatuses", "", "", "Lcom/edgemind/app/data/DownloadStatus;", "initStatuses", "loading", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Z)V", "getAllModels", "()Ljava/util/List;", "getDownloadStatuses", "()Ljava/util/Map;", "getInitStatuses", "getLoading", "()Z", "getTasks", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ModelManagerUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.edgemind.app.data.Model> allModels = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.edgemind.app.data.Task> tasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.edgemind.app.data.DownloadStatus> downloadStatuses = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> initStatuses = null;
    private final boolean loading = false;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Model> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Task> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.edgemind.app.data.DownloadStatus> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.viewmodel.ModelManagerUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.Model> allModels, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.Task> tasks, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.edgemind.app.data.DownloadStatus> downloadStatuses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> initStatuses, boolean loading) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public ModelManagerUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.Model> allModels, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.Task> tasks, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.edgemind.app.data.DownloadStatus> downloadStatuses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> initStatuses, boolean loading) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Model> getAllModels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Task> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.edgemind.app.data.DownloadStatus> getDownloadStatuses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getInitStatuses() {
        return null;
    }
    
    public final boolean getLoading() {
        return false;
    }
    
    public ModelManagerUiState() {
        super();
    }
}