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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tH\u00c6\u0003Jm\u0010#\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\'H\u00d6\u0001J\t\u0010(\u001a\u00020\tH\u00d6\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019\u00a8\u0006)"}, d2 = {"Lcom/edgemind/app/viewmodel/ChatUiState;", "", "messages", "", "Lcom/edgemind/app/viewmodel/UiChatMessage;", "isGenerating", "", "isPreparing", "currentSessionId", "", "sessions", "Lcom/edgemind/app/data/ChatSession;", "activeModel", "Lcom/edgemind/app/data/Model;", "activeTaskId", "error", "(Ljava/util/List;ZZLjava/lang/String;Ljava/util/List;Lcom/edgemind/app/data/Model;Ljava/lang/String;Ljava/lang/String;)V", "getActiveModel", "()Lcom/edgemind/app/data/Model;", "getActiveTaskId", "()Ljava/lang/String;", "getCurrentSessionId", "getError", "()Z", "getMessages", "()Ljava/util/List;", "getSessions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class ChatUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.edgemind.app.viewmodel.UiChatMessage> messages = null;
    private final boolean isGenerating = false;
    private final boolean isPreparing = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String currentSessionId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.edgemind.app.data.ChatSession> sessions = null;
    @org.jetbrains.annotations.Nullable()
    private final com.edgemind.app.data.Model activeModel = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String activeTaskId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.viewmodel.UiChatMessage> component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.ChatSession> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.edgemind.app.data.Model component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.viewmodel.ChatUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.viewmodel.UiChatMessage> messages, boolean isGenerating, boolean isPreparing, @org.jetbrains.annotations.Nullable()
    java.lang.String currentSessionId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.ChatSession> sessions, @org.jetbrains.annotations.Nullable()
    com.edgemind.app.data.Model activeModel, @org.jetbrains.annotations.Nullable()
    java.lang.String activeTaskId, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
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
    
    public ChatUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.viewmodel.UiChatMessage> messages, boolean isGenerating, boolean isPreparing, @org.jetbrains.annotations.Nullable()
    java.lang.String currentSessionId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.ChatSession> sessions, @org.jetbrains.annotations.Nullable()
    com.edgemind.app.data.Model activeModel, @org.jetbrains.annotations.Nullable()
    java.lang.String activeTaskId, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.viewmodel.UiChatMessage> getMessages() {
        return null;
    }
    
    public final boolean isGenerating() {
        return false;
    }
    
    public final boolean isPreparing() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentSessionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.ChatSession> getSessions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.edgemind.app.data.Model getActiveModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActiveTaskId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public ChatUiState() {
        super();
    }
}