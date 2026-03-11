package com.edgemind.app.data;

import android.graphics.Bitmap;

/**
 * A single model with all its metadata and runtime state
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bC\b\u0087\b\u0018\u00002\u00020\u0001B\u00b9\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\u000eH\u00c6\u0003J\t\u0010E\u001a\u00020\u000eH\u00c6\u0003J\t\u0010F\u001a\u00020\u0012H\u00c6\u0003J\t\u0010G\u001a\u00020\u0012H\u00c6\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u00c6\u0003J\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u00c6\u0003J\u000f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0007H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u000bH\u00c6\u0003J\t\u0010R\u001a\u00020\u000bH\u00c6\u0003J\t\u0010S\u001a\u00020\u000eH\u00c6\u0003J\u00c5\u0001\u0010T\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00152\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010U\u001a\u00020\u000b2\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010W\u001a\u00020\u000eH\u00d6\u0001J\t\u0010X\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010#\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\'\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b(\u0010%R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u001a\u0010*\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010%R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010!R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010,R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010,R\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010%R\u0011\u0010:\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b;\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001cR\u0011\u0010?\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b@\u0010=R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010%R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010%\u00a8\u0006Y"}, d2 = {"Lcom/edgemind/app/data/Model;", "", "name", "", "url", "downloadFileName", "sizeInBytes", "", "info", "version", "llmSupportImage", "", "llmSupportAudio", "llmMaxToken", "", "minDeviceMemoryInGb", "defaultTopK", "defaultTopP", "", "defaultTemperature", "accelerators", "", "Lcom/edgemind/app/data/Accelerator;", "taskTypes", "bestForTaskTypes", "learnMoreUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZIIIFFLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getAccelerators", "()Ljava/util/List;", "getBestForTaskTypes", "getDefaultTemperature", "()F", "getDefaultTopK", "()I", "getDefaultTopP", "displayName", "getDisplayName", "()Ljava/lang/String;", "getDownloadFileName", "formattedSize", "getFormattedSize", "getInfo", "initializing", "getInitializing", "()Z", "setInitializing", "(Z)V", "instance", "getInstance", "()Ljava/lang/Object;", "setInstance", "(Ljava/lang/Object;)V", "getLearnMoreUrl", "getLlmMaxToken", "getLlmSupportAudio", "getLlmSupportImage", "getMinDeviceMemoryInGb", "getName", "normalizedName", "getNormalizedName", "getSizeInBytes", "()J", "getTaskTypes", "totalBytes", "getTotalBytes", "getUrl", "getVersion", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"})
public final class Model {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String url = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String downloadFileName = null;
    private final long sizeInBytes = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String info = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String version = null;
    private final boolean llmSupportImage = false;
    private final boolean llmSupportAudio = false;
    private final int llmMaxToken = 0;
    private final int minDeviceMemoryInGb = 0;
    private final int defaultTopK = 0;
    private final float defaultTopP = 0.0F;
    private final float defaultTemperature = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.edgemind.app.data.Accelerator> accelerators = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> taskTypes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> bestForTaskTypes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String learnMoreUrl = null;
    
    /**
     * Runtime state — set after model is loaded
     */
    @org.jetbrains.annotations.Nullable()
    private java.lang.Object instance;
    private boolean initializing = false;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final float component12() {
        return 0.0F;
    }
    
    public final float component13() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Accelerator> component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
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
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.edgemind.app.data.Model copy(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String downloadFileName, long sizeInBytes, @org.jetbrains.annotations.NotNull()
    java.lang.String info, @org.jetbrains.annotations.NotNull()
    java.lang.String version, boolean llmSupportImage, boolean llmSupportAudio, int llmMaxToken, int minDeviceMemoryInGb, int defaultTopK, float defaultTopP, float defaultTemperature, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.edgemind.app.data.Accelerator> accelerators, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> taskTypes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> bestForTaskTypes, @org.jetbrains.annotations.NotNull()
    java.lang.String learnMoreUrl) {
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
    
    public Model(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String downloadFileName, long sizeInBytes, @org.jetbrains.annotations.NotNull()
    java.lang.String info, @org.jetbrains.annotations.NotNull()
    java.lang.String version, boolean llmSupportImage, boolean llmSupportAudio, int llmMaxToken, int minDeviceMemoryInGb, int defaultTopK, float defaultTopP, float defaultTemperature, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.edgemind.app.data.Accelerator> accelerators, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> taskTypes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> bestForTaskTypes, @org.jetbrains.annotations.NotNull()
    java.lang.String learnMoreUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDownloadFileName() {
        return null;
    }
    
    public final long getSizeInBytes() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersion() {
        return null;
    }
    
    public final boolean getLlmSupportImage() {
        return false;
    }
    
    public final boolean getLlmSupportAudio() {
        return false;
    }
    
    public final int getLlmMaxToken() {
        return 0;
    }
    
    public final int getMinDeviceMemoryInGb() {
        return 0;
    }
    
    public final int getDefaultTopK() {
        return 0;
    }
    
    public final float getDefaultTopP() {
        return 0.0F;
    }
    
    public final float getDefaultTemperature() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.edgemind.app.data.Accelerator> getAccelerators() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTaskTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getBestForTaskTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLearnMoreUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNormalizedName() {
        return null;
    }
    
    public final long getTotalBytes() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedSize() {
        return null;
    }
    
    /**
     * Runtime state — set after model is loaded
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getInstance() {
        return null;
    }
    
    /**
     * Runtime state — set after model is loaded
     */
    public final void setInstance(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
    }
    
    public final boolean getInitializing() {
        return false;
    }
    
    public final void setInitializing(boolean p0) {
    }
}