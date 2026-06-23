package com.example.basic_mc.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.BaiThiDAO;

import java.util.ArrayList;

public class ResultDetailActivity extends AppCompatActivity {

    private ListView lvResultDetail;
    private Button btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        lvResultDetail = findViewById(R.id.lvResultDetail);
        btnBackHome = findViewById(R.id.btnBackHome);

        // ================= FIX LONG =================
        long maBaiThi = getIntent().getLongExtra("MA_BAITHI", -1);

        if (maBaiThi == -1) {
            Toast.makeText(this,
                    "Không tìm thấy bài thi",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // ================= LOAD DATA =================
        BaiThiDAO dao = new BaiThiDAO(this);
        ArrayList<String> data = dao.getChiTietBaiThi((int) maBaiThi);

        if (data == null || data.isEmpty()) {
            Toast.makeText(this,
                    "Không có dữ liệu chi tiết bài làm",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        data
                );

        lvResultDetail.setAdapter(adapter);

        btnBackHome.setOnClickListener(v -> finish());
    }
}