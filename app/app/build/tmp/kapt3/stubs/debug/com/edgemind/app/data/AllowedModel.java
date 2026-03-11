package com.edgemind.app.data;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u00a2\u0006\u0002\u0010\u0014J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u0010!J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u000bH\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u009a\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u000f2\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020\u0012H\u00d6\u0001J\u0006\u0010:\u001a\u00020;J\t\u0010<\u001a\u00020\u0003H\u00d6\u0001R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001dR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0016\u00a8\u0006="}, d2 = {"Lcom/edgemind/app/data/AllowedModel;", "", "name", "", "modelId", "modelFile", "description", "sizeInBytes", "", "commitHash", "defaultConfig", "Lcom/edgemind/app/data/DefaultConfig;", "taskTypes", "", "llmSupportImage", "", "llmSupportAudio", "minDeviceMemoryInGb", "", "bestForTaskTypes", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lcom/edgemind/app/data/DefaultConfig;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;)V", "getBestForTaskTypes", "()Ljava/util/List;", "getCommitHash", "()Ljava/lang/String;", "getDefaultConfig", "()Lcom/edgemind/app/data/DefaultConfig;", "getDescription", "getLlmSupportAudio", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLlmSupportImage", "getMinDeviceMemoryInGb", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getModelFile", "getModelId", "getName", "getSizeInBytes", "()J", "getTaskTypes", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lcom/edgemind/app/data/DefaultConfig;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;)Lcom/edgemind/app/data/AllowedModel;", "equals", "other", "hashCode", "toModel", "Lcom/edgemind/app/data/Model;", "toString", "app_debug"})
public final class AllowedModel {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String modelId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String modelFile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final long sizeInBytes = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String commitHash = null;
    @org.jetbrains.annotations.NotNull()
    private final com.edgemind.app.data.DefaultConfig defaultConfig = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> taskTypes = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean llmSupportImage = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean llmSupportAudio = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer minDeviceMemoryInGb = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.String> bestForTaskTypes = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.DefaultConfig component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.AllowedModel copy(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String modelId, @org.jetbrains.annotations.NotNull()
    java.lang.String modelFile, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long sizeInBytes, @org.jetbrains.annotations.NotNull()
    java.lang.String commitHash, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.DefaultConfig defaultConfig, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> taskTypes, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean llmSupportImage, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean llmSupportAudio, @org.jetbrains.annotations.Nullable()
    java.lang.Integer minDeviceMemoryInGb, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> bestForTaskTypes) {
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
    
    public AllowedModel(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String modelId, @org.jetbrains.annotations.NotNull()
    java.lang.String modelFile, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long sizeInBytes, @org.jetbrains.annotations.NotNull()
    java.lang.String commitHash, @org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.DefaultConfig defaultConfig, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> taskTypes, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean llmSupportImage, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean llmSupportAudio, @org.jetbrains.annotations.Nullable()
    java.lang.Integer minDeviceMemoryInGb, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> bestForTaskTypes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModelId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModelFile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final long getSizeInBytes() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCommitHash() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.DefaultConfig getDefaultConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTaskTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getLlmSupportImage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getLlmSupportAudio() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMinDeviceMemoryInGb() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getBestForTaskTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.Model toModel() {
        return null;
    }
}