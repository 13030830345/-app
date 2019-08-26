package com.example.a76773.myapplication.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public abstract class AbstractService {
    protected SQLiteDatabase db;
    public AbstractService(Context context) {
       DatabaseHelper database = new DatabaseHelper(context, "Register.db", null, 1);
        db = database.getWritableDatabase();
    }
}




