package com.edgemind.app.data;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ChatDatabase_Impl extends ChatDatabase {
  private volatile ChatDao _chatDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(1, "df27f0359cbd2f9b9a837c95cb893d60", "426405d28ef71bd6670b66b30c30160f") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `chat_sessions` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `modelName` TEXT NOT NULL, `taskId` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `chat_messages` (`id` TEXT NOT NULL, `sessionId` TEXT NOT NULL, `role` TEXT NOT NULL, `content` TEXT NOT NULL, `imagePath` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`sessionId`) REFERENCES `chat_sessions`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_chat_messages_sessionId` ON `chat_messages` (`sessionId`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'df27f0359cbd2f9b9a837c95cb893d60')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `chat_sessions`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `chat_messages`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsChatSessions = new HashMap<String, TableInfo.Column>(5);
        _columnsChatSessions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatSessions.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatSessions.put("modelName", new TableInfo.Column("modelName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatSessions.put("taskId", new TableInfo.Column("taskId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatSessions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysChatSessions = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesChatSessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChatSessions = new TableInfo("chat_sessions", _columnsChatSessions, _foreignKeysChatSessions, _indicesChatSessions);
        final TableInfo _existingChatSessions = TableInfo.read(connection, "chat_sessions");
        if (!_infoChatSessions.equals(_existingChatSessions)) {
          return new RoomOpenDelegate.ValidationResult(false, "chat_sessions(com.edgemind.app.data.ChatSession).\n"
                  + " Expected:\n" + _infoChatSessions + "\n"
                  + " Found:\n" + _existingChatSessions);
        }
        final Map<String, TableInfo.Column> _columnsChatMessages = new HashMap<String, TableInfo.Column>(6);
        _columnsChatMessages.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("imagePath", new TableInfo.Column("imagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysChatMessages = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysChatMessages.add(new TableInfo.ForeignKey("chat_sessions", "CASCADE", "NO ACTION", Arrays.asList("sessionId"), Arrays.asList("id")));
        final Set<TableInfo.Index> _indicesChatMessages = new HashSet<TableInfo.Index>(1);
        _indicesChatMessages.add(new TableInfo.Index("index_chat_messages_sessionId", false, Arrays.asList("sessionId"), Arrays.asList("ASC")));
        final TableInfo _infoChatMessages = new TableInfo("chat_messages", _columnsChatMessages, _foreignKeysChatMessages, _indicesChatMessages);
        final TableInfo _existingChatMessages = TableInfo.read(connection, "chat_messages");
        if (!_infoChatMessages.equals(_existingChatMessages)) {
          return new RoomOpenDelegate.ValidationResult(false, "chat_messages(com.edgemind.app.data.ChatMessage).\n"
                  + " Expected:\n" + _infoChatMessages + "\n"
                  + " Found:\n" + _existingChatMessages);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "chat_sessions", "chat_messages");
  }

  @Override
  public void clearAllTables() {
    super.performClear(true, "chat_sessions", "chat_messages");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ChatDao.class, ChatDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ChatDao chatDao() {
    if (_chatDao != null) {
      return _chatDao;
    } else {
      synchronized(this) {
        if(_chatDao == null) {
          _chatDao = new ChatDao_Impl(this);
        }
        return _chatDao;
      }
    }
  }
}
