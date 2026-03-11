package com.edgemind.app.di

import android.content.Context
import androidx.room.Room
import com.edgemind.app.data.ChatDao
import com.edgemind.app.data.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "edgemind_chat.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideChatDao(database: ChatDatabase): ChatDao {
        return database.chatDao()
    }
}
