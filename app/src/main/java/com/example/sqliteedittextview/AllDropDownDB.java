package com.example.sqliteedittextview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AllDropDownDB {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static AllDropDownDB instance;

    private AllDropDownDB(Context context) {
        this.openHelper = new MyDatabase(context);
    }

    public static AllDropDownDB getInstance(Context context) {
        if (instance == null) {
            instance = new AllDropDownDB(context);
        }
        return instance;
    }

    public void open() {
        this.sqLiteDatabase = openHelper.getWritableDatabase();
    }

    public void close() {
        if (sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

    public boolean insertIntoDB(String rollno,String marks){
        ContentValues values = new ContentValues();
        values.put("ROLLNO",rollno);
        values.put("MARKS",marks);
        long checkers = sqLiteDatabase.insert("SomeDb",null,values);
        if(checkers == -1){
            return false;
        } else {
            return true;
        }
    }
}
