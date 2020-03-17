package com.micro.root.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * @Description: 基类SQLite
 * @Author: ALin
 * @CreateDate: 19-5-13
 */
public abstract class BaseSQLite extends SQLiteOpenHelper {

    protected final Context context;

    public BaseSQLite(Context context, String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        tableCreate(sqLiteDatabase);
    }

    protected abstract void tableCreate(SQLiteDatabase sqLiteDatabase);

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        tableUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    protected abstract void tableUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion);

    protected abstract void tableClear(String table);

    protected long cacheInsert(String table, ContentValues values){
        return getWritableDatabase().replace(table, null, values);
    }

    protected int cacheDelete(String table, String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(table, whereClause, whereArgs);
    }

    protected int cacheUpdate(String table, ContentValues values, String whereClause, String[] whereArgs){
        return getWritableDatabase().update(table, values, whereClause, whereArgs);
    }

    protected Cursor cacheQuery(String table, String[] projection, String selection, String[] selectionArgs, String orderBy){
        return getReadableDatabase().query(table, projection, selection, selectionArgs, null, null, orderBy);
    }

    protected Cursor cacheQuery(String table, String[] projection, String selection, String[] selectionArgs, String orderBy, String limit){
        return getReadableDatabase().query(table, projection, selection, selectionArgs, null, null, orderBy, limit);
    }

    public final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public final int bulkInsert(Uri uri, ContentValues[] values) {
        return context.getContentResolver().bulkInsert(uri, values);
    }

    public final Uri insert(Uri url, ContentValues values) {
        return context.getContentResolver().insert(url, values);
    }

    public final int delete(Uri uri, String where, String[] selectionArgs) {
        return context.getContentResolver().delete(uri, where, selectionArgs);
    }

    public final int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return context.getContentResolver().update(uri, values, where, selectionArgs);
    }
}