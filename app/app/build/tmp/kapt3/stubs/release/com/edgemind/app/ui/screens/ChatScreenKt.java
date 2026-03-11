package com.edgemind.app.ui.screens;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.provider.OpenableColumns;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.edgemind.app.data.Model;
import com.edgemind.app.data.TaskIds;
import com.edgemind.app.ui.theme.*;
import com.edgemind.app.viewmodel.Attachment;
import com.edgemind.app.viewmodel.AttachmentType;
import com.edgemind.app.viewmodel.ChatUiState;
import com.edgemind.app.viewmodel.UiChatMessage;
import java.io.File;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0003\u001a\u00d2\u0001\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052<\u0010\u0013\u001a8\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00030\u0010\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u001bH\u0007\u001a\"\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010#\u001a&\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\b\u0010)\u001a\u00020\u0001H\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006*"}, d2 = {"AttachmentChip", "", "attachment", "Lcom/edgemind/app/viewmodel/Attachment;", "onRemove", "Lkotlin/Function0;", "ChatBubble", "message", "Lcom/edgemind/app/viewmodel/UiChatMessage;", "ChatScreen", "modelName", "", "taskId", "state", "Lcom/edgemind/app/viewmodel/ChatUiState;", "availableModels", "", "Lcom/edgemind/app/data/Model;", "onBack", "onSendMessage", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "text", "attachments", "onNewChat", "onLoadSession", "Lkotlin/Function1;", "onDeleteSession", "onStopGeneration", "onSwitchModel", "ModelBadge", "color", "Landroidx/compose/ui/graphics/Color;", "ModelBadge-4WTKRHQ", "(Ljava/lang/String;J)V", "ModelSwitchCard", "model", "isSelected", "", "onClick", "TypingIndicator", "app_release"})
public final class ChatScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ChatScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String modelName, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.viewmodel.ChatUiState state, @org.jetbrains.annotations.NotNull()
    java.util.List<com.edgemind.app.data.Model> availableModels, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.util.List<com.edgemind.app.viewmodel.Attachment>, kotlin.Unit> onSendMessage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNewChat, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onLoadSession, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDeleteSession, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStopGeneration, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.edgemind.app.data.Model, kotlin.Unit> onSwitchModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ModelSwitchCard(com.edgemind.app.data.Model model, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AttachmentChip(com.edgemind.app.viewmodel.Attachment attachment, kotlin.jvm.functions.Function0<kotlin.Unit> onRemove) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ChatBubble(com.edgemind.app.viewmodel.UiChatMessage message) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TypingIndicator() {
    }
}