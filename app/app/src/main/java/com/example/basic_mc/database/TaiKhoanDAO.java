package com.example.basic_mc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basic_mc.model.TaiKhoan;

import java.util.ArrayList;

public class TaiKhoanDAO {

    private DatabaseHelper dbHelper;

    public TaiKhoanDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Thêm tài khoản
    public long insert(TaiKhoan tk) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("TenDangNhap", tk.getTenDangNhap());
        values.put("MatKhau", tk.getMatKhau());
        values.put("HoTen", tk.getHoTen());
        values.put("VaiTro", tk.getVaiTro());

        return db.insert("TaiKhoan", null, values);
    }

    // Lấy tất cả tài khoản
    public ArrayList<TaiKhoan> getAll() {

        ArrayList<TaiKhoan> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM TaiKhoan",
                null
        );

        if (cursor.moveToFirst()) {

            do {

                TaiKhoan tk = new TaiKhoan();

                tk.setMaTK(cursor.getInt(0));
                tk.setTenDangNhap(cursor.getString(1));
                tk.setMatKhau(cursor.getString(2));
                tk.setHoTen(cursor.getString(3));
                tk.setVaiTro(cursor.getString(4));

                list.add(tk);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    // Kiểm tra đăng nhập
    public TaiKhoan checkLogin(
            String tenDangNhap,
            String matKhau) {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM TaiKhoan WHERE TenDangNhap=? AND MatKhau=?",
                new String[]{
                        tenDangNhap,
                        matKhau
                }
        );

        TaiKhoan tk = null;

        if (cursor.moveToFirst()) {

            tk = new TaiKhoan();

            tk.setMaTK(cursor.getInt(0));
            tk.setTenDangNhap(cursor.getString(1));
            tk.setMatKhau(cursor.getString(2));
            tk.setHoTen(cursor.getString(3));
            tk.setVaiTro(cursor.getString(4));
        }

        cursor.close();

        return tk;
    }

    // Kiểm tra tên đăng nhập tồn tại
    public boolean isUsernameExists(
            String tenDangNhap) {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM TaiKhoan WHERE TenDangNhap=?",
                new String[]{tenDangNhap}
        );

        boolean exists =
                cursor.getCount() > 0;

        cursor.close();

        return exists;
    }

    // Xóa tài khoản
    public int delete(int maTK) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        return db.delete(
                "TaiKhoan",
                "MaTK=?",
                new String[]{
                        String.valueOf(maTK)
                }
        );
    }

    // Cập nhật tài khoản
    public int update(TaiKhoan tk) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("TenDangNhap",
                tk.getTenDangNhap());

        values.put("MatKhau",
                tk.getMatKhau());

        values.put("HoTen",
                tk.getHoTen());

        values.put("VaiTro",
                tk.getVaiTro());

        return db.update(
                "TaiKhoan",
                values,
                "MaTK=?",
                new String[]{
                        String.valueOf(
                                tk.getMaTK())
                }
        );
    }
}