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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lcom/edgemind/app/data/LlmModelInstance;", "", "engine", "Lcom/google/ai/edge/litertlm/Engine;", "conversation", "Lcom/google/ai/edge/litertlm/Conversation;", "backend", "Lcom/google/ai/edge/litertlm/Backend;", "visionEnabled", "", "(Lcom/google/ai/edge/litertlm/Engine;Lcom/google/ai/edge/litertlm/Conversation;Lcom/google/ai/edge/litertlm/Backend;Z)V", "getBackend", "()Lcom/google/ai/edge/litertlm/Backend;", "getConversation", "()Lcom/google/ai/edge/litertlm/Conversation;", "getEngine", "()Lcom/google/ai/edge/litertlm/Engine;", "getVisionEnabled", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class LlmModelInstance {
    @org.jetbrains.annotations.NotNull()
    private final com.google.ai.edge.litertlm.Engine engine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.ai.edge.litertlm.Conversation conversation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.ai.edge.litertlm.Backend backend = null;
    private final boolean visionEnabled = false;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Engine component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Conversation component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Backend component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.LlmModelInstance copy(@org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Engine engine, @org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Conversation conversation, @org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Backend backend, boolean visionEnabled) {
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
    
    public LlmModelInstance(@org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Engine engine, @org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Conversation conversation, @org.jetbrains.annotations.NotNull()
    com.google.ai.edge.litertlm.Backend backend, boolean visionEnabled) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Engine getEngine() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Conversation getConversation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.ai.edge.litertlm.Backend getBackend() {
        return null;
    }
    
    public final boolean getVisionEnabled() {
        return false;
    }
}