package com.edgemind.app.data;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * JSON model for parsing model_allowlist.json
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJJ\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001J\t\u0010 \u001a\u00020\bH\u00d6\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u0011\u00a8\u0006!"}, d2 = {"Lcom/edgemind/app/data/DefaultConfig;", "", "topK", "", "topP", "", "temperature", "accelerators", "", "maxTokens", "(Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/Integer;)V", "getAccelerators", "()Ljava/lang/String;", "getMaxTokens", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTemperature", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getTopK", "getTopP", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/Integer;)Lcom/edgemind/app/data/DefaultConfig;", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class DefaultConfig {
    @com.google.gson.annotations.SerializedName(value = "topK")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer topK = null;
    @com.google.gson.annotations.SerializedName(value = "topP")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float topP = null;
    @com.google.gson.annotations.SerializedName(value = "temperature")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float temperature = null;
    @com.google.gson.annotations.SerializedName(value = "accelerators")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String accelerators = null;
    @com.google.gson.annotations.SerializedName(value = "maxTokens")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer maxTokens = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.DefaultConfig copy(@org.jetbrains.annotations.Nullable()
    java.lang.Integer topK, @org.jetbrains.annotations.Nullable()
    java.lang.Float topP, @org.jetbrains.annotations.Nullable()
    java.lang.Float temperature, @org.jetbrains.annotations.Nullable()
    java.lang.String accelerators, @org.jetbrains.annotations.Nullable()
    java.lang.Integer maxTokens) {
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
    
    public DefaultConfig(@org.jetbrains.annotations.Nullable()
    java.lang.Integer topK, @org.jetbrains.annotations.Nullable()
    java.lang.Float topP, @org.jetbrains.annotations.Nullable()
    java.lang.Float temperature, @org.jetbrains.annotations.Nullable()
    java.lang.String accelerators, @org.jetbrains.annotations.Nullable()
    java.lang.Integer maxTokens) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTopK() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getTopP() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getTemperature() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAccelerators() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMaxTokens() {
        return null;
    }
}