package com.edgemind.app.data;

import androidx.room.*;
import kotlinx.coroutines.flow.Flow;

/**
 * ======== Database ========
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/edgemind/app/data/ChatDatabase;", "Landroidx/room/RoomDatabase;", "()V", "chatDao", "Lcom/edgemind/app/data/ChatDao;", "app_debug"})
@androidx.room.Database(entities = {com.edgemind.app.data.ChatSession.class, com.edgemind.app.data.ChatMessage.class}, version = 1, exportSchema = false)
public abstract class ChatDatabase extends androidx.room.RoomDatabase {
    
    public ChatDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.edgemind.app.data.ChatDao chatDao();
}