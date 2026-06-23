package com.example.basic_mc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basic_mc.model.DeThi;

import java.util.ArrayList;

public class DeThiDAO {

    private DatabaseHelper dbHelper;

    public DeThiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public DeThi getById(int maDe) {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM DeThi WHERE MaDe=?",
                        new String[]{
                                String.valueOf(maDe)
                        }
                );

        DeThi deThi = null;

        if(cursor.moveToFirst()) {

            deThi = new DeThi();

            deThi.setMaDe(cursor.getInt(0));
            deThi.setTenDe(cursor.getString(1));
            deThi.setMoTa(cursor.getString(2));
            deThi.setThoiGian(cursor.getInt(3));
        }

        cursor.close();

        return deThi;
    }
    // Thêm đề thi
    public long insert(DeThi deThi) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("TenDe", deThi.getTenDe());
        values.put("MoTa", deThi.getMoTa());
        values.put("ThoiGian", deThi.getThoiGian());

        return db.insert("DeThi", null, values);
    }

    // Lấy danh sách đề thi
    public ArrayList<DeThi> getAll() {

        ArrayList<DeThi> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM DeThi",
                        null);

        if(cursor.moveToFirst()){

            do{

                DeThi dt = new DeThi();

                dt.setMaDe(cursor.getInt(0));
                dt.setTenDe(cursor.getString(1));
                dt.setMoTa(cursor.getString(2));
                dt.setThoiGian(cursor.getInt(3));

                list.add(dt);

            }while(cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    // Sửa đề thi
    public int update(DeThi deThi){

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("TenDe", deThi.getTenDe());
        values.put("MoTa", deThi.getMoTa());
        values.put("ThoiGian", deThi.getThoiGian());

        return db.update(
                "DeThi",
                values,
                "MaDe=?",
                new String[]{
                        String.valueOf(
                                deThi.getMaDe())
                });
    }

    // Xóa đề thi
    public int delete(int maDe){

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        return db.delete(
                "DeThi",
                "MaDe=?",
                new String[]{
                        String.valueOf(maDe)
                });
    }
}