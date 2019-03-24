package com.example.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseConnection extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lab3";
    public static final String TABLE_NAME = "students";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_AGE = "age";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    private static final String ITEMS_TABLE_CREATE_SQL =
            "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + " integer primary key, " + KEY_NAME + " Varchar, " + KEY_SURNAME + " Varchar, "
                    + KEY_AGE + " integer, " + KEY_EMAIL + " Varchar, " + KEY_PASSWORD + " Varchar)";

    DataBaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ITEMS_TABLE_CREATE_SQL);
        System.out.println(this.getDatabaseName());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }


}
