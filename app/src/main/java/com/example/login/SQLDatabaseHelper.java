package com.example.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLDatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName = "FinLisDataAccountDatabase";
    private static final int DatabaseVersion = 1;

    public SQLDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, String TableName){
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //SQL語法
        String SQLTable = "CREATE TABLE IF NOT EXISTS Users(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account text not null," +
                "password text not null," +
                "labgroup text not null" +
                ")";
        sqLiteDatabase.execSQL(SQLTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int i1){
        final String SQL = "DROP TABLE Users";
        sqLiteDatabase.execSQL(SQL);
    }
}
