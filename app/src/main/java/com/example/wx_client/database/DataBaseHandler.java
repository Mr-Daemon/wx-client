package com.example.wx_client.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {
    private final static String DB_NAME = "/data/data/com.example.wx_client/database.sqlite";
    private static SQLiteDatabase db;

    static {
        db = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ChatRecord(username VARCHAR(15),friendname VARCHAR(15),message TEXT);");
    }

    public static List<String> fetchRecord(String username, String friendname) {
        List<String> result = new ArrayList<>();
        String[] args = {username, friendname};
        Cursor cursor = db.rawQuery("SELECT message FROM ChatRecord WHERE username=? AND friendname=?;", args);
        int index = cursor.getColumnIndex("message");
        while (cursor.moveToNext()) {
            result.add(cursor.getString(index));
        }
        return result;
    }

    public static void insertRecord(String username, String friendname, String msg) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("friendname", friendname);
        values.put("message", msg);
        db.insert("ChatRecord", null, values);
    }
}
