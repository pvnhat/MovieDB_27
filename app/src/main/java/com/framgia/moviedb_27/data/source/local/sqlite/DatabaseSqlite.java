package com.framgia.moviedb_27.data.source.local.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSqlite extends SQLiteOpenHelper {

    private static final String DATABASE_LOCAL_MOVIE = "DATABASE_LOCAL_MOVIE.sqlite";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mSQLiteDatabase;

    public DatabaseSqlite(Context context) {
        super(context, DATABASE_LOCAL_MOVIE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    public void writeData(String sql) {
        mSQLiteDatabase = getWritableDatabase();
        mSQLiteDatabase.execSQL(sql);
    }

    public Cursor readData(String sql) {
        mSQLiteDatabase = getReadableDatabase();
        return mSQLiteDatabase.rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
