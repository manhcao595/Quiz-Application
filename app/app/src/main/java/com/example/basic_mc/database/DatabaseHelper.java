package com.example.basic_mc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "QuizDB.db";
    public static final int DATABASE_VERSION = 4;

    // ===== TABLE NAME =====
    public static final String TABLE_TAIKHOAN = "TaiKhoan";
    public static final String TABLE_DETHI = "DeThi";
    public static final String TABLE_CAUHOI = "CauHoi";
    public static final String TABLE_BAITHI = "BaiThi";
    public static final String TABLE_CHITIETBAITHI = "ChiTietBaiThi";
    public static final String TABLE_KETQUA = "KetQua";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ===== BẬT FOREIGN KEY (QUAN TRỌNG) =====
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // ===== TAIKHOAN =====
        db.execSQL(
                "CREATE TABLE " + TABLE_TAIKHOAN + "(" +
                        "MaTK INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenDangNhap TEXT UNIQUE NOT NULL," +
                        "MatKhau TEXT NOT NULL," +
                        "HoTen TEXT NOT NULL," +
                        "VaiTro TEXT NOT NULL" +
                        ")"
        );

        // ===== DETHI =====
        db.execSQL(
                "CREATE TABLE " + TABLE_DETHI + "(" +
                        "MaDe INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenDe TEXT NOT NULL," +
                        "MoTa TEXT," +
                        "ThoiGian INTEGER NOT NULL" +
                        ")"
        );

        // ===== CAUHOI =====
        db.execSQL(
                "CREATE TABLE " + TABLE_CAUHOI + "(" +
                        "MaCH INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "MaDe INTEGER NOT NULL," +
                        "NoiDung TEXT NOT NULL," +
                        "DapAnA TEXT NOT NULL," +
                        "DapAnB TEXT NOT NULL," +
                        "DapAnC TEXT NOT NULL," +
                        "DapAnD TEXT NOT NULL," +
                        "DapAnDung TEXT NOT NULL," +
                        "FOREIGN KEY(MaDe) REFERENCES DeThi(MaDe) ON DELETE CASCADE" +
                        ")"
        );

        // ===== BAITHI =====
        db.execSQL(
                "CREATE TABLE " + TABLE_BAITHI + "(" +
                        "MaBaiThi INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "MaTK INTEGER NOT NULL," +
                        "MaDe INTEGER NOT NULL," +
                        "NgayThi TEXT NOT NULL," +
                        "SoCauDung INTEGER," +
                        "Diem REAL," +
                        "FOREIGN KEY(MaTK) REFERENCES TaiKhoan(MaTK)," +
                        "FOREIGN KEY(MaDe) REFERENCES DeThi(MaDe)" +
                        ")"
        );

        // ===== CHITIETBAITHI =====
        db.execSQL(
                "CREATE TABLE " + TABLE_CHITIETBAITHI + "(" +
                        "MaChiTiet INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "MaBaiThi INTEGER NOT NULL," +
                        "MaCH INTEGER NOT NULL," +
                        "DapAnChon TEXT," +
                        "FOREIGN KEY(MaBaiThi) REFERENCES BaiThi(MaBaiThi) ON DELETE CASCADE," +
                        "FOREIGN KEY(MaCH) REFERENCES CauHoi(MaCH)" +
                        ")"
        );

        // ===== KETQUA =====
        db.execSQL(
                "CREATE TABLE " + TABLE_KETQUA + "(" +
                        "MaKetQua INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "MaBaiThi INTEGER NOT NULL," +
                        "TongSoCau INTEGER," +
                        "SoCauDung INTEGER," +
                        "SoCauSai INTEGER," +
                        "Diem REAL," +
                        "FOREIGN KEY(MaBaiThi) REFERENCES BaiThi(MaBaiThi)" +
                        ")"
        );

        insertSampleData(db);
    }

    // ===== SAMPLE DATA (QUAN TRỌNG) =====
    private void insertSampleData(SQLiteDatabase db) {

        // ADMIN
        db.execSQL(
                "INSERT INTO TaiKhoan(TenDangNhap,MatKhau,HoTen,VaiTro) " +
                        "VALUES('admin','123456','Administrator','ADMIN')"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHITIETBAITHI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAITHI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETHI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAIKHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KETQUA);

        onCreate(db);
    }
}