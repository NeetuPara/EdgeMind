package com.edgemind.app.data;

import androidx.room.*;
import kotlinx.coroutines.flow.Flow;

/**
 * ======== DAO ========
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\t2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\u0010\u001a\u00020\u0005H\'J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/edgemind/app/data/ChatDao;", "", "deleteMessagesForSession", "", "sessionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSession", "getAllSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/edgemind/app/data/ChatSession;", "getMessagesForSession", "Lcom/edgemind/app/data/ChatMessage;", "getMessagesForSessionSync", "getSessionsForModel", "modelName", "insertMessage", "message", "(Lcom/edgemind/app/data/ChatMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSession", "session", "(Lcom/edgemind/app/data/ChatSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ChatDao {
    
    @androidx.room.Query(value = "SELECT * FROM chat_sessions ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.edgemind.app.data.ChatSession>> getAllSessions();
    
    @androidx.room.Query(value = "SELECT * FROM chat_sessions WHERE modelName = :modelName ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.edgemind.app.data.ChatSession>> getSessionsForModel(@org.jetbrains.annotations.NotNull()
    java.lang.String modelName);
    
    @androidx.room.Query(value = "SELECT * FROM chat_messages WHERE sessionId = :sessionId ORDER BY timestamp ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.edgemind.app.data.ChatMessage>> getMessagesForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId);
    
    @androidx.room.Query(value = "SELECT * FROM chat_messages WHERE sessionId = :sessionId ORDER BY timestamp ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMessagesForSessionSync(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.edgemind.app.data.ChatMessage>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSession(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.ChatSession session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMessage(@org.jetbrains.annotations.NotNull()
    com.edgemind.app.data.ChatMessage message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM chat_sessions WHERE id = :sessionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM chat_messages WHERE sessionId = :sessionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMessagesForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}