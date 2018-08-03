package com.example.ratech.sqliteapp;

import android.content.ContentValues;
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
    public void onCreate(SQLiteDatabase db) {
        // execute the query of creation of the table
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(String firstname, String lastname, int mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FIRSTNAME, firstname);
        contentValues.put(COL_LASTNAME, lastname);
        contentValues.put(COL_MARK, mark);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
