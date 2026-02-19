package com.example.davomatdaftarchasi;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TalabaDao_Impl implements TalabaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Talaba> __insertionAdapterOfTalaba;

  private final EntityDeletionOrUpdateAdapter<Talaba> __deletionAdapterOfTalaba;

  private final EntityDeletionOrUpdateAdapter<Talaba> __updateAdapterOfTalaba;

  public TalabaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTalaba = new EntityInsertionAdapter<Talaba>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `talabalar` (`id`,`ism`,`familiya`,`sinf`,`guruh`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Talaba entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getIsm());
        statement.bindString(3, entity.getFamiliya());
        statement.bindString(4, entity.getSinf());
        statement.bindString(5, entity.getGuruh());
      }
    };
    this.__deletionAdapterOfTalaba = new EntityDeletionOrUpdateAdapter<Talaba>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `talabalar` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Talaba entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTalaba = new EntityDeletionOrUpdateAdapter<Talaba>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `talabalar` SET `id` = ?,`ism` = ?,`familiya` = ?,`sinf` = ?,`guruh` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Talaba entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getIsm());
        statement.bindString(3, entity.getFamiliya());
        statement.bindString(4, entity.getSinf());
        statement.bindString(5, entity.getGuruh());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object qoshish(final Talaba talaba, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTalaba.insert(talaba);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object ochirish(final Talaba talaba, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTalaba.handle(talaba);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object tahrirlash(final Talaba talaba, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTalaba.handle(talaba);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object barchasiniOlish(final Continuation<? super List<Talaba>> $completion) {
    final String _sql = "SELECT * FROM talabalar ORDER BY familiya ASC, ism ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Talaba>>() {
      @Override
      @NonNull
      public List<Talaba> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIsm = CursorUtil.getColumnIndexOrThrow(_cursor, "ism");
          final int _cursorIndexOfFamiliya = CursorUtil.getColumnIndexOrThrow(_cursor, "familiya");
          final int _cursorIndexOfSinf = CursorUtil.getColumnIndexOrThrow(_cursor, "sinf");
          final int _cursorIndexOfGuruh = CursorUtil.getColumnIndexOrThrow(_cursor, "guruh");
          final List<Talaba> _result = new ArrayList<Talaba>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Talaba _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIsm;
            _tmpIsm = _cursor.getString(_cursorIndexOfIsm);
            final String _tmpFamiliya;
            _tmpFamiliya = _cursor.getString(_cursorIndexOfFamiliya);
            final String _tmpSinf;
            _tmpSinf = _cursor.getString(_cursorIndexOfSinf);
            final String _tmpGuruh;
            _tmpGuruh = _cursor.getString(_cursorIndexOfGuruh);
            _item = new Talaba(_tmpId,_tmpIsm,_tmpFamiliya,_tmpSinf,_tmpGuruh);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object qidirish(final String qidiruv,
      final Continuation<? super List<Talaba>> $completion) {
    final String _sql = "SELECT * FROM talabalar WHERE ism LIKE '%' || ? || '%' OR familiya LIKE '%' || ? || '%' ORDER BY familiya ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, qidiruv);
    _argIndex = 2;
    _statement.bindString(_argIndex, qidiruv);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Talaba>>() {
      @Override
      @NonNull
      public List<Talaba> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIsm = CursorUtil.getColumnIndexOrThrow(_cursor, "ism");
          final int _cursorIndexOfFamiliya = CursorUtil.getColumnIndexOrThrow(_cursor, "familiya");
          final int _cursorIndexOfSinf = CursorUtil.getColumnIndexOrThrow(_cursor, "sinf");
          final int _cursorIndexOfGuruh = CursorUtil.getColumnIndexOrThrow(_cursor, "guruh");
          final List<Talaba> _result = new ArrayList<Talaba>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Talaba _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIsm;
            _tmpIsm = _cursor.getString(_cursorIndexOfIsm);
            final String _tmpFamiliya;
            _tmpFamiliya = _cursor.getString(_cursorIndexOfFamiliya);
            final String _tmpSinf;
            _tmpSinf = _cursor.getString(_cursorIndexOfSinf);
            final String _tmpGuruh;
            _tmpGuruh = _cursor.getString(_cursorIndexOfGuruh);
            _item = new Talaba(_tmpId,_tmpIsm,_tmpFamiliya,_tmpSinf,_tmpGuruh);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object sinfBoyichaFilter(final String sinf,
      final Continuation<? super List<Talaba>> $completion) {
    final String _sql = "SELECT * FROM talabalar WHERE sinf = ? ORDER BY familiya ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sinf);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Talaba>>() {
      @Override
      @NonNull
      public List<Talaba> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIsm = CursorUtil.getColumnIndexOrThrow(_cursor, "ism");
          final int _cursorIndexOfFamiliya = CursorUtil.getColumnIndexOrThrow(_cursor, "familiya");
          final int _cursorIndexOfSinf = CursorUtil.getColumnIndexOrThrow(_cursor, "sinf");
          final int _cursorIndexOfGuruh = CursorUtil.getColumnIndexOrThrow(_cursor, "guruh");
          final List<Talaba> _result = new ArrayList<Talaba>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Talaba _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIsm;
            _tmpIsm = _cursor.getString(_cursorIndexOfIsm);
            final String _tmpFamiliya;
            _tmpFamiliya = _cursor.getString(_cursorIndexOfFamiliya);
            final String _tmpSinf;
            _tmpSinf = _cursor.getString(_cursorIndexOfSinf);
            final String _tmpGuruh;
            _tmpGuruh = _cursor.getString(_cursorIndexOfGuruh);
            _item = new Talaba(_tmpId,_tmpIsm,_tmpFamiliya,_tmpSinf,_tmpGuruh);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object barchaSinflar(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT sinf FROM talabalar ORDER BY sinf ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object idBoyichaOlish(final int id, final Continuation<? super Talaba> $completion) {
    final String _sql = "SELECT * FROM talabalar WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Talaba>() {
      @Override
      @Nullable
      public Talaba call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIsm = CursorUtil.getColumnIndexOrThrow(_cursor, "ism");
          final int _cursorIndexOfFamiliya = CursorUtil.getColumnIndexOrThrow(_cursor, "familiya");
          final int _cursorIndexOfSinf = CursorUtil.getColumnIndexOrThrow(_cursor, "sinf");
          final int _cursorIndexOfGuruh = CursorUtil.getColumnIndexOrThrow(_cursor, "guruh");
          final Talaba _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIsm;
            _tmpIsm = _cursor.getString(_cursorIndexOfIsm);
            final String _tmpFamiliya;
            _tmpFamiliya = _cursor.getString(_cursorIndexOfFamiliya);
            final String _tmpSinf;
            _tmpSinf = _cursor.getString(_cursorIndexOfSinf);
            final String _tmpGuruh;
            _tmpGuruh = _cursor.getString(_cursorIndexOfGuruh);
            _result = new Talaba(_tmpId,_tmpIsm,_tmpFamiliya,_tmpSinf,_tmpGuruh);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
