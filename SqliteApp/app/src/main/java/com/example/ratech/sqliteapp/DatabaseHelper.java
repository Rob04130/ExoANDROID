package com.example.ratech.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "student.db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "student_table";

    public static final String COL_ID = "ID";
    public static final String COL_FIRSTNAME = "FIRSTNAME";
    public static final String COL_LASTNAME = "LASTNAME";
    public static final String COL_MARK = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
