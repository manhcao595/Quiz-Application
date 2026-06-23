package com.example.basic_mc.activity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.DatabaseHelper;
import com.example.basic_mc.model.BaiThi;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView lvHistory;
    private Button btnBack, btnDelete;

    private ArrayList<BaiThi> list;
    private ArrayAdapter<String> adapter;

    private int selectedPosition = -1;
    private int selectedMaBaiThi = -1;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDeleteAll);

        dbHelper = new DatabaseHelper(this);

        loadData();

        // ===== chọn item =====
        lvHistory.setOnItemClickListener((parent, view, position, id) -> {

            selectedPosition = position;
            selectedMaBaiThi = list.get(position).getMaBaiThi();

            Toast.makeText(this,
                    "Đã chọn bài thi ID: " + selectedMaBaiThi,
                    Toast.LENGTH_SHORT).show();
        });

        // ===== xóa 1 item =====
        btnDelete.setOnClickListener(v -> {

            if (selectedMaBaiThi == -1) {
                Toast.makeText(this, "Chưa chọn bài thi", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Xóa bài thi này?")
                    .setPositiveButton("Xóa", (dialog, which) -> deleteItem())
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        // ===== quay lại =====
        btnBack.setOnClickListener(v -> finish());
    }

    // ================= LOAD DATA =================
    private void loadData() {

        list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        var cursor = db.rawQuery(
                "SELECT * FROM BaiThi ORDER BY MaBaiThi DESC",
                null
        );

        while (cursor.moveToNext()) {

            BaiThi bt = new BaiThi();

            bt.setMaBaiThi(cursor.getInt(0));
            bt.setMaTK(cursor.getInt(1));
            bt.setMaDe(cursor.getInt(2));
            bt.setNgayThi(cursor.getString(3));
            bt.setSoCauDung(cursor.getInt(4));
            bt.setDiem(cursor.getDouble(5));

            list.add(bt);
        }

        cursor.close();

        ArrayList<String> show = new ArrayList<>();

        for (BaiThi b : list) {
            show.add(
                    "ID: " + b.getMaBaiThi()
                            + "\nĐiểm: " + b.getDiem()
                            + "\nNgày: " + b.getNgayThi()
            );
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                show
        );

        lvHistory.setAdapter(adapter);
    }

    // ================= DELETE =================
    private void deleteItem() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 1. xóa chi tiết
        db.delete("ChiTietBaiThi",
                "MaBaiThi=?",
                new String[]{String.valueOf(selectedMaBaiThi)});

        // 2. xóa bài thi
        db.delete("BaiThi",
                "MaBaiThi=?",
                new String[]{String.valueOf(selectedMaBaiThi)});

        Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();

        // reload
        selectedMaBaiThi = -1;
        loadData();
    }
}