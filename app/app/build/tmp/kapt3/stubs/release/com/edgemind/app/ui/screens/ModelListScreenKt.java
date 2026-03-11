package com.edgemind.app.ui.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import com.edgemind.app.data.DownloadStatusType;
import com.edgemind.app.data.Model;
import com.edgemind.app.data.TaskIds;
import com.edgemind.app.viewmodel.ModelManagerUiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001aZ\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a~\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u00a8\u0006\u0015"}, d2 = {"Badge", "", "text", "", "ModelCard", "model", "Lcom/edgemind/app/data/Model;", "taskId", "downloadStatus", "Lcom/edgemind/app/data/DownloadStatus;", "onDownload", "Lkotlin/Function0;", "onCancel", "onChat", "onDelete", "ModelListScreen", "taskLabel", "state", "Lcom/edgemind/app/viewmodel/ModelManagerUiState;", "onBack", "Lkotlin/Function1;", "app_release"})
public final class ModelListScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ModelListScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String taskId, @org.jetbrains.annotations.NotNull()
    java.lang.String taskLabel, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.viewmodel.ModelManagerUiState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.edgemind.app.data.Model, kotlin.Unit> onDownload, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.edgemind.app.data.Model, kotlin.Unit> onCancel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.edgemind.app.data.Model, kotlin.Unit> onChat, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.edgemind.app.data.Model, kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ModelCard(com.edgemind.app.data.Model model, java.lang.String taskId, com.edgemind.app.data.DownloadStatus downloadStatus, kotlin.jvm.functions.Function0<kotlin.Unit> onDownload, kotlin.jvm.functions.Function0<kotlin.Unit> onCancel, kotlin.jvm.functions.Function0<kotlin.Unit> onChat, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Badge(java.lang.String text) {
    }
}