package com.example.davomatdaftarchasi;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class DavomatDao_Impl implements DavomatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Davomat> __insertionAdapterOfDavomat;

  public DavomatDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDavomat = new EntityInsertionAdapter<Davomat>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `davomat` (`id`,`talabaId`,`sana`,`holat`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Davomat entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTalabaId());
        statement.bindString(3, entity.getSana());
        statement.bindString(4, entity.getHolat());
      }
    };
  }

  @Override
  public Object saqlash(final Davomat davomat, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDavomat.insert(davomat);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object talabaningDavomati(final int talabaId,
      final Continuation<? super List<Davomat>> $completion) {
    final String _sql = "SELECT * FROM davomat WHERE talabaId = ? ORDER BY sana DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, talabaId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Davomat>>() {
      @Override
      @NonNull
      public List<Davomat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTalabaId = CursorUtil.getColumnIndexOrThrow(_cursor, "talabaId");
          final int _cursorIndexOfSana = CursorUtil.getColumnIndexOrThrow(_cursor, "sana");
          final int _cursorIndexOfHolat = CursorUtil.getColumnIndexOrThrow(_cursor, "holat");
          final List<Davomat> _result = new ArrayList<Davomat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Davomat _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTalabaId;
            _tmpTalabaId = _cursor.getInt(_cursorIndexOfTalabaId);
            final String _tmpSana;
            _tmpSana = _cursor.getString(_cursorIndexOfSana);
            final String _tmpHolat;
            _tmpHolat = _cursor.getString(_cursorIndexOfHolat);
            _item = new Davomat(_tmpId,_tmpTalabaId,_tmpSana,_tmpHolat);
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
  public Object sanaBoyichaOlish(final String sana,
      final Continuation<? super List<Davomat>> $completion) {
    final String _sql = "SELECT * FROM davomat WHERE sana = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sana);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Davomat>>() {
      @Override
      @NonNull
      public List<Davomat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTalabaId = CursorUtil.getColumnIndexOrThrow(_cursor, "talabaId");
          final int _cursorIndexOfSana = CursorUtil.getColumnIndexOrThrow(_cursor, "sana");
          final int _cursorIndexOfHolat = CursorUtil.getColumnIndexOrThrow(_cursor, "holat");
          final List<Davomat> _result = new ArrayList<Davomat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Davomat _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTalabaId;
            _tmpTalabaId = _cursor.getInt(_cursorIndexOfTalabaId);
            final String _tmpSana;
            _tmpSana = _cursor.getString(_cursorIndexOfSana);
            final String _tmpHolat;
            _tmpHolat = _cursor.getString(_cursorIndexOfHolat);
            _item = new Davomat(_tmpId,_tmpTalabaId,_tmpSana,_tmpHolat);
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
  public Object bugungiDavomat(final int talabaId, final String sana,
      final Continuation<? super Davomat> $completion) {
    final String _sql = "SELECT * FROM davomat WHERE talabaId = ? AND sana = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, talabaId);
    _argIndex = 2;
    _statement.bindString(_argIndex, sana);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Davomat>() {
      @Override
      @Nullable
      public Davomat call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTalabaId = CursorUtil.getColumnIndexOrThrow(_cursor, "talabaId");
          final int _cursorIndexOfSana = CursorUtil.getColumnIndexOrThrow(_cursor, "sana");
          final int _cursorIndexOfHolat = CursorUtil.getColumnIndexOrThrow(_cursor, "holat");
          final Davomat _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTalabaId;
            _tmpTalabaId = _cursor.getInt(_cursorIndexOfTalabaId);
            final String _tmpSana;
            _tmpSana = _cursor.getString(_cursorIndexOfSana);
            final String _tmpHolat;
            _tmpHolat = _cursor.getString(_cursorIndexOfHolat);
            _result = new Davomat(_tmpId,_tmpTalabaId,_tmpSana,_tmpHolat);
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

  @Override
  public Object sanaoraliqDavomat(final String boshlanish, final String tugash,
      final Continuation<? super List<Davomat>> $completion) {
    final String _sql = "SELECT * FROM davomat WHERE sana BETWEEN ? AND ? ORDER BY sana ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, boshlanish);
    _argIndex = 2;
    _statement.bindString(_argIndex, tugash);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Davomat>>() {
      @Override
      @NonNull
      public List<Davomat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTalabaId = CursorUtil.getColumnIndexOrThrow(_cursor, "talabaId");
          final int _cursorIndexOfSana = CursorUtil.getColumnIndexOrThrow(_cursor, "sana");
          final int _cursorIndexOfHolat = CursorUtil.getColumnIndexOrThrow(_cursor, "holat");
          final List<Davomat> _result = new ArrayList<Davomat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Davomat _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpTalabaId;
            _tmpTalabaId = _cursor.getInt(_cursorIndexOfTalabaId);
            final String _tmpSana;
            _tmpSana = _cursor.getString(_cursorIndexOfSana);
            final String _tmpHolat;
            _tmpHolat = _cursor.getString(_cursorIndexOfHolat);
            _item = new Davomat(_tmpId,_tmpTalabaId,_tmpSana,_tmpHolat);
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
  public Object kelmaganKunlar(final int talabaId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "\n"
            + "        SELECT COUNT(*) FROM davomat \n"
            + "        WHERE talabaId = ? \n"
            + "        AND holat = 'kelmagan' \n"
            + "        AND sana >= date('now', '-30 days')\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, talabaId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object barchaSanalar(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT sana FROM davomat ORDER BY sana DESC";
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
  public Object oxirgiHolat(final int id, final Continuation<? super String> $completion) {
    final String _sql = "SELECT holat FROM davomat WHERE talabaId = ? ORDER BY sana DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      @Nullable
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
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

  @Override
  public Object holatBySana(final int talabaId, final String sana,
      final Continuation<? super String> $completion) {
    final String _sql = "SELECT holat FROM davomat WHERE talabaId = ? AND sana = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, talabaId);
    _argIndex = 2;
    _statement.bindString(_argIndex, sana);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      @Nullable
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
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
