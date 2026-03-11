package com.edgemind.app.data;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ChatDao_Impl implements ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<ChatSession> __insertAdapterOfChatSession;

  private final EntityInsertAdapter<ChatMessage> __insertAdapterOfChatMessage;

  public ChatDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfChatSession = new EntityInsertAdapter<ChatSession>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `chat_sessions` (`id`,`title`,`modelName`,`taskId`,`createdAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ChatSession entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getModelName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getModelName());
        }
        if (entity.getTaskId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getTaskId());
        }
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__insertAdapterOfChatMessage = new EntityInsertAdapter<ChatMessage>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `chat_messages` (`id`,`sessionId`,`role`,`content`,`imagePath`,`timestamp`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ChatMessage entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getSessionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getSessionId());
        }
        if (entity.getRole() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRole());
        }
        if (entity.getContent() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getContent());
        }
        if (entity.getImagePath() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getImagePath());
        }
        statement.bindLong(6, entity.getTimestamp());
      }
    };
  }

  @Override
  public Object insertSession(final ChatSession session,
      final Continuation<? super Unit> $completion) {
    if (session == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfChatSession.insert(_connection, session);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object insertMessage(final ChatMessage message,
      final Continuation<? super Unit> $completion) {
    if (message == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfChatMessage.insert(_connection, message);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<ChatSession>> getAllSessions() {
    final String _sql = "SELECT * FROM chat_sessions ORDER BY createdAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"chat_sessions"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfModelName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "modelName");
        final int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<ChatSession> _result = new ArrayList<ChatSession>();
        while (_stmt.step()) {
          final ChatSession _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpModelName;
          if (_stmt.isNull(_columnIndexOfModelName)) {
            _tmpModelName = null;
          } else {
            _tmpModelName = _stmt.getText(_columnIndexOfModelName);
          }
          final String _tmpTaskId;
          if (_stmt.isNull(_columnIndexOfTaskId)) {
            _tmpTaskId = null;
          } else {
            _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
          }
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new ChatSession(_tmpId,_tmpTitle,_tmpModelName,_tmpTaskId,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ChatSession>> getSessionsForModel(final String modelName) {
    final String _sql = "SELECT * FROM chat_sessions WHERE modelName = ? ORDER BY createdAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"chat_sessions"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (modelName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, modelName);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfModelName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "modelName");
        final int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<ChatSession> _result = new ArrayList<ChatSession>();
        while (_stmt.step()) {
          final ChatSession _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpModelName;
          if (_stmt.isNull(_columnIndexOfModelName)) {
            _tmpModelName = null;
          } else {
            _tmpModelName = _stmt.getText(_columnIndexOfModelName);
          }
          final String _tmpTaskId;
          if (_stmt.isNull(_columnIndexOfTaskId)) {
            _tmpTaskId = null;
          } else {
            _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
          }
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new ChatSession(_tmpId,_tmpTitle,_tmpModelName,_tmpTaskId,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ChatMessage>> getMessagesForSession(final String sessionId) {
    final String _sql = "SELECT * FROM chat_messages WHERE sessionId = ? ORDER BY timestamp ASC";
    return FlowUtil.createFlow(__db, false, new String[] {"chat_messages"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, sessionId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfSessionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sessionId");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfImagePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imagePath");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<ChatMessage> _result = new ArrayList<ChatMessage>();
        while (_stmt.step()) {
          final ChatMessage _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpSessionId;
          if (_stmt.isNull(_columnIndexOfSessionId)) {
            _tmpSessionId = null;
          } else {
            _tmpSessionId = _stmt.getText(_columnIndexOfSessionId);
          }
          final String _tmpRole;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_columnIndexOfRole);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final String _tmpImagePath;
          if (_stmt.isNull(_columnIndexOfImagePath)) {
            _tmpImagePath = null;
          } else {
            _tmpImagePath = _stmt.getText(_columnIndexOfImagePath);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new ChatMessage(_tmpId,_tmpSessionId,_tmpRole,_tmpContent,_tmpImagePath,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getMessagesForSessionSync(final String sessionId,
      final Continuation<? super List<ChatMessage>> $completion) {
    final String _sql = "SELECT * FROM chat_messages WHERE sessionId = ? ORDER BY timestamp ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, sessionId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfSessionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sessionId");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfImagePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imagePath");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<ChatMessage> _result = new ArrayList<ChatMessage>();
        while (_stmt.step()) {
          final ChatMessage _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpSessionId;
          if (_stmt.isNull(_columnIndexOfSessionId)) {
            _tmpSessionId = null;
          } else {
            _tmpSessionId = _stmt.getText(_columnIndexOfSessionId);
          }
          final String _tmpRole;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_columnIndexOfRole);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final String _tmpImagePath;
          if (_stmt.isNull(_columnIndexOfImagePath)) {
            _tmpImagePath = null;
          } else {
            _tmpImagePath = _stmt.getText(_columnIndexOfImagePath);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new ChatMessage(_tmpId,_tmpSessionId,_tmpRole,_tmpContent,_tmpImagePath,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteSession(final String sessionId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM chat_sessions WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, sessionId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteMessagesForSession(final String sessionId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM chat_messages WHERE sessionId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, sessionId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
