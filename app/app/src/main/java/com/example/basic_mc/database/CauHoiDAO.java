package com.example.basic_mc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.basic_mc.model.CauHoi;

import java.util.ArrayList;

public class CauHoiDAO {

    private DatabaseHelper dbHelper;

    public CauHoiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // ================= INSERT =================
    public long insert(CauHoi ch) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MaDe", ch.getMaDe());
        values.put("NoiDung", ch.getNoiDung());
        values.put("DapAnA", ch.getDapAnA());
        values.put("DapAnB", ch.getDapAnB());
        values.put("DapAnC", ch.getDapAnC());
        values.put("DapAnD", ch.getDapAnD());
        values.put("DapAnDung", ch.getDapAnDung());

        long result = db.insert("CauHoi", null, values);

        Log.d("CAUHOI_DAO", "Insert result = " + result);

        db.close();
        return result;
    }

    // ================= GET ALL =================
    public ArrayList<CauHoi> getAll() {

        ArrayList<CauHoi> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM CauHoi", null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
                CauHoi ch = new CauHoi();

                ch.setMaCH(cursor.getInt(cursor.getColumnIndexOrThrow("MaCH")));
                ch.setMaDe(cursor.getInt(cursor.getColumnIndexOrThrow("MaDe")));
                ch.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("NoiDung")));
                ch.setDapAnA(cursor.getString(cursor.getColumnIndexOrThrow("DapAnA")));
                ch.setDapAnB(cursor.getString(cursor.getColumnIndexOrThrow("DapAnB")));
                ch.setDapAnC(cursor.getString(cursor.getColumnIndexOrThrow("DapAnC")));
                ch.setDapAnD(cursor.getString(cursor.getColumnIndexOrThrow("DapAnD")));
                ch.setDapAnDung(cursor.getString(cursor.getColumnIndexOrThrow("DapAnDung")));

                list.add(ch);

            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        return list;
    }

    // ================= GET BY MADE =================
    public ArrayList<CauHoi> getByMaDe(int maDe) {

        ArrayList<CauHoi> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM CauHoi WHERE MaDe=?",
                new String[]{String.valueOf(maDe)}
        );

        Log.d("CAUHOI_DAO", "Query MaDe = " + maDe);

        if (cursor != null && cursor.moveToFirst()) {

            do {
                CauHoi ch = new CauHoi();

                ch.setMaCH(cursor.getInt(cursor.getColumnIndexOrThrow("MaCH")));
                ch.setMaDe(cursor.getInt(cursor.getColumnIndexOrThrow("MaDe")));
                ch.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("NoiDung")));
                ch.setDapAnA(cursor.getString(cursor.getColumnIndexOrThrow("DapAnA")));
                ch.setDapAnB(cursor.getString(cursor.getColumnIndexOrThrow("DapAnB")));
                ch.setDapAnC(cursor.getString(cursor.getColumnIndexOrThrow("DapAnC")));
                ch.setDapAnD(cursor.getString(cursor.getColumnIndexOrThrow("DapAnD")));
                ch.setDapAnDung(cursor.getString(cursor.getColumnIndexOrThrow("DapAnDung")));

                list.add(ch);

            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        Log.d("CAUHOI_DAO", "Result size = " + list.size());

        return list;
    }

    // ================= UPDATE =================
    public int update(CauHoi ch) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MaDe", ch.getMaDe());
        values.put("NoiDung", ch.getNoiDung());
        values.put("DapAnA", ch.getDapAnA());
        values.put("DapAnB", ch.getDapAnB());
        values.put("DapAnC", ch.getDapAnC());
        values.put("DapAnD", ch.getDapAnD());
        values.put("DapAnDung", ch.getDapAnDung());

        int result = db.update(
                "CauHoi",
                values,
                "MaCH=?",
                new String[]{String.valueOf(ch.getMaCH())}
        );

        db.close();
        return result;
    }

    // ================= DELETE =================
    public int delete(int maCH) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int result = db.delete(
                "CauHoi",
                "MaCH=?",
                new String[]{String.valueOf(maCH)}
        );

        db.close();
        return result;
    }
}