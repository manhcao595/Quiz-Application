package com.example.basic_mc.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StatisticsDAO {

    private DatabaseHelper dbHelper;

    public StatisticsDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public int getTongTaiKhoan() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor c =
                db.rawQuery(
                        "SELECT COUNT(*) FROM TaiKhoan",
                        null);

        c.moveToFirst();

        int result = c.getInt(0);

        c.close();

        return result;
    }

    public int getTongDeThi() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor c =
                db.rawQuery(
                        "SELECT COUNT(*) FROM DeThi",
                        null);

        c.moveToFirst();

        int result = c.getInt(0);

        c.close();

        return result;
    }

    public int getTongCauHoi() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor c =
                db.rawQuery(
                        "SELECT COUNT(*) FROM CauHoi",
                        null);

        c.moveToFirst();

        int result = c.getInt(0);

        c.close();

        return result;
    }

    public int getTongLuotThi() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor c =
                db.rawQuery(
                        "SELECT COUNT(*) FROM BaiThi",
                        null);

        c.moveToFirst();

        int result = c.getInt(0);

        c.close();

        return result;
    }
}