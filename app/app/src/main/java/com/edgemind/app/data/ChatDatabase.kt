package com.edgemind.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/** ======== Entities ======== */

@Entity(tableName = "chat_sessions")
data class ChatSession(
    @PrimaryKey val id: String,
    val title: String,
    val modelName: String,
    val taskId: String,
    val createdAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "chat_messages",
    foreignKeys = [ForeignKey(
        entity = ChatSession::class,
        parentColumns = ["id"],
        childColumns = ["sessionId"],
        onDelete = ForeignKey.CASCADE,
    )],
    indices = [Index(value = ["sessionId"])]
)
data class ChatMessage(
    @PrimaryKey val id: String,
    val sessionId: String,
    val role: String,       // "user" or "assistant"
    val content: String,
    val imagePath: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
)

/** ======== DAO ======== */

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_sessions ORDER BY createdAt DESC")
    fun getAllSessions(): Flow<List<ChatSession>>

    @Query("SELECT * FROM chat_sessions WHERE modelName = :modelName ORDER BY createdAt DESC")
    fun getSessionsForModel(modelName: String): Flow<List<ChatSession>>

    @Query("SELECT * FROM chat_messages WHERE sessionId = :sessionId ORDER BY timestamp ASC")
    fun getMessagesForSession(sessionId: String): Flow<List<ChatMessage>>

    @Query("SELECT * FROM chat_messages WHERE sessionId = :sessionId ORDER BY timestamp ASC")
    suspend fun getMessagesForSessionSync(sessionId: String): List<ChatMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: ChatSession)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage)

    @Query("DELETE FROM chat_sessions WHERE id = :sessionId")
    suspend fun deleteSession(sessionId: String)

    @Query("DELETE FROM chat_messages WHERE sessionId = :sessionId")
    suspend fun deleteMessagesForSession(sessionId: String)
}

/** ======== Database ======== */

@Database(entities = [ChatSession::class, ChatMessage::class], version = 1, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
