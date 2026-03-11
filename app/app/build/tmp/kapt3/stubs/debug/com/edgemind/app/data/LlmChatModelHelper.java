package com.edgemind.app.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.ai.edge.litertlm.Backend;
import com.google.ai.edge.litertlm.Content;
import com.google.ai.edge.litertlm.Contents;
import com.google.ai.edge.litertlm.Conversation;
import com.google.ai.edge.litertlm.ConversationConfig;
import com.google.ai.edge.litertlm.Engine;
import com.google.ai.edge.litertlm.EngineConfig;
import com.google.ai.edge.litertlm.Message;
import com.google.ai.edge.litertlm.MessageCallback;
import com.google.ai.edge.litertlm.SamplerConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Inference helper wrapping LiteRT LM SDK.
 * Design matches Edge Gallery's LlmChatModelHelper:
 * - No OpenCL file probing (unreliable on Exynos)
 * - Try configured backend(s) in order, fall back cleanly
 * - visionBackend is ALWAYS GPU (SDK requirement), never CPU
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006JC\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0099\u0001\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000e2:\u0010\u0016\u001a6\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00040\u0017j\u0002`\u001a2\u0010\u0010\u001b\u001a\f\u0012\u0004\u0012\u00020\u00040\u001cj\u0002`\u001d2#\b\u0002\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00040\r2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!\u00a8\u0006#"}, d2 = {"Lcom/edgemind/app/data/LlmChatModelHelper;", "", "()V", "cleanUp", "", "model", "Lcom/edgemind/app/data/Model;", "initialize", "context", "Landroid/content/Context;", "supportImage", "", "onDone", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "isGpuAvailable", "resetConversation", "runInference", "input", "resultListener", "Lkotlin/Function2;", "partialResult", "done", "Lcom/edgemind/app/data/ResultListener;", "cleanUpListener", "Lkotlin/Function0;", "Lcom/edgemind/app/data/CleanUpListener;", "onError", "message", "images", "", "Landroid/graphics/Bitmap;", "app_debug"})
public final class LlmChatModelHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.edgemind.app.data.LlmChatModelHelper INSTANCE = null;
    
    private LlmChatModelHelper() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model, boolean supportImage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDone) {
    }
    
    public final void runInference(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model, @org.jetbrains.annotations.NotNull()
    java.lang.String input, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Boolean, kotlin.Unit> resultListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> cleanUpListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> images) {
    }
    
    public final void resetConversation(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    public final void cleanUp(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
    }
    
    public final boolean isGpuAvailable(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.Model model) {
        return false;
    }
}