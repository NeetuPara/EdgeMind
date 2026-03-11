package com.edgemind.app.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.edgemind.app.data.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\u0006\u0010\u001b\u001a\u00020\u000fJ\u001e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00112\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0013J\u0016\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0011J\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u0011J\u000e\u0010&\u001a\u00020\u000f2\u0006\u0010\'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u000fJ\u0006\u0010*\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006+"}, d2 = {"Lcom/edgemind/app/viewmodel/ChatViewModel;", "Landroidx/lifecycle/ViewModel;", "appContext", "Landroid/content/Context;", "chatDao", "Lcom/edgemind/app/data/ChatDao;", "(Landroid/content/Context;Lcom/edgemind/app/data/ChatDao;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/edgemind/app/viewmodel/ChatUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteSession", "", "sessionId", "", "extractPdfImages", "", "Landroid/graphics/Bitmap;", "context", "path", "loadSession", "loadSessionsForModel", "modelName", "readTextFile", "resetSession", "sendMessage", "text", "attachments", "Lcom/edgemind/app/viewmodel/Attachment;", "setActiveModel", "model", "Lcom/edgemind/app/data/Model;", "taskId", "setError", "error", "setModelLoading", "loading", "", "startNewChat", "stopGeneration", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ChatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    private final com.edgemind.app.data.ChatDao chatDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.edgemind.app.viewmodel.ChatUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.edgemind.app.viewmodel.ChatUiState> uiState = null;
    
    @javax.inject.Inject()
    public ChatViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.ChatDao chatDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.edgemind.app.viewmodel.ChatUiState> getUiState() {
        return null;
    }
    
    public final void setActiveModel(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId) {
    }
    
    public final void setModelLoading(boolean loading) {
    }
    
    public final void setError(@org.jetbrains.annotations.NotNull()
    java.lang.String error) {
    }
    
    private final void loadSessionsForModel(java.lang.String modelName) {
    }
    
    public final void loadSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
    }
    
    public final void startNewChat() {
    }
    
    public final void deleteSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
    }
    
    public final void sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.viewmodel.Attachment> attachments) {
    }
    
    /**
     * Renders a PDF file into a list of Bitmaps using Android's native PdfRenderer.
     * This allows Vision models to "see" the layout (tables, charts) rather than just reading raw text.
     */
    private final java.util.List<android.graphics.Bitmap> extractPdfImages(android.content.Context context, java.lang.String path) {
        return null;
    }
    
    private final java.lang.String readTextFile(java.lang.String path) {
        return null;
    }
    
    public final void stopGeneration() {
    }
    
    public final void resetSession() {
    }
}