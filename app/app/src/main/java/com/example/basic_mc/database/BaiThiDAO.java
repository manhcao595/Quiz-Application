package com.example.basic_mc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basic_mc.model.BaiThi;

import java.util.ArrayList;

public class BaiThiDAO {

    private DatabaseHelper dbHelper;

    public BaiThiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // ================= INSERT BÀI THI =================
    public long insert(BaiThi bt) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MaTK", bt.getMaTK());
        values.put("MaDe", bt.getMaDe());
        values.put("NgayThi", bt.getNgayThi());
        values.put("SoCauDung", bt.getSoCauDung());
        values.put("Diem", bt.getDiem());

        return db.insert("BaiThi", null, values);
    }

    // ================= LẤY TẤT CẢ BÀI THI THEO USER =================
    public ArrayList<BaiThi> getAllByUser(int maTK) {

        ArrayList<BaiThi> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM BaiThi WHERE MaTK=? ORDER BY MaBaiThi DESC",
                new String[]{String.valueOf(maTK)}
        );

        if (cursor.moveToFirst()) {
            do {
                BaiThi bt = new BaiThi();

                bt.setMaBaiThi(cursor.getInt(0));
                bt.setMaTK(cursor.getInt(1));
                bt.setMaDe(cursor.getInt(2));
                bt.setNgayThi(cursor.getString(3));
                bt.setSoCauDung(cursor.getInt(4));
                bt.setDiem(cursor.getDouble(5));

                list.add(bt);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    // ================= LẤY CHI TIẾT BÀI THI =================
    public ArrayList<String> getChiTietBaiThi(int maBaiThi) {

        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql =
                "SELECT CH.NoiDung, CH.DapAnA, CH.DapAnB, CH.DapAnC, CH.DapAnD, CH.DapAnDung, CT.DapAnChon " +
                        "FROM ChiTietBaiThi CT " +
                        "JOIN CauHoi CH ON CT.MaCH = CH.MaCH " +
                        "WHERE CT.MaBaiThi = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maBaiThi)});

        if (cursor.moveToFirst()) {
            do {

                String noiDung = cursor.getString(0);
                String a = cursor.getString(1);
                String b = cursor.getString(2);
                String c = cursor.getString(3);
                String d = cursor.getString(4);
                String correct = cursor.getString(5);
                String user = cursor.getString(6);

                boolean isCorrect =
                        user != null &&
                                user.equalsIgnoreCase(correct != null ? correct.trim() : "");

                String item =
                        "📌 " + noiDung + "\n\n" +
                                "A. " + a + "\n" +
                                "B. " + b + "\n" +
                                "C. " + c + "\n" +
                                "D. " + d + "\n\n" +
                                "✔ Đáp án đúng: " + correct + "\n" +
                                "✏ Bạn chọn: " + user + "\n" +
                                (isCorrect ? "✔ ĐÚNG" : "❌ SAI");

                list.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    // ================= XÓA BÀI THI =================
    public int delete(int maBaiThi) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // xóa chi tiết trước (quan trọng)
        db.delete("ChiTietBaiThi", "MaBaiThi=?",
                new String[]{String.valueOf(maBaiThi)});

        return db.delete("BaiThi", "MaBaiThi=?",
                new String[]{String.valueOf(maBaiThi)});
    }
}