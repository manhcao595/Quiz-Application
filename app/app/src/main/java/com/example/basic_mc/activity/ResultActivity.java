package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;

public class ResultActivity extends AppCompatActivity {

    private TextView txtScore, txtRank, txtCorrect, txtWrong, txtTotalQuestion, txtTime;
    private Button btnViewDetail, btnBackHome;

    private long maBaiThi;
    private double diem;
    private int correct;
    private int total;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        Intent intent = getIntent();

        // ================= FIX QUAN TRỌNG =================
        maBaiThi = intent.getLongExtra("MA_BAITHI", -1);
        diem = intent.getDoubleExtra("DIEM", 0);
        correct = intent.getIntExtra("SO_DUNG", 0);
        total = intent.getIntExtra("TONG", 0);
        time = intent.getStringExtra("TIME");

        int wrong = total - correct;

        // ================= SET UI =================
        txtScore.setText(String.format("%.1f", diem));
        txtCorrect.setText(String.valueOf(correct));
        txtWrong.setText(String.valueOf(wrong));
        txtTotalQuestion.setText(String.valueOf(total));
        txtTime.setText(time != null ? time : "00:00");

        txtRank.setText(getRank(diem));

        // ================= XEM CHI TIẾT =================
        btnViewDetail.setOnClickListener(v -> {

            Intent detailIntent =
                    new Intent(ResultActivity.this, ResultDetailActivity.class);

            detailIntent.putExtra("MA_BAITHI", maBaiThi);

            startActivity(detailIntent);
        });

        btnBackHome.setOnClickListener(v -> finish());
    }

    private void initView() {
        txtScore = findViewById(R.id.txtScore);
        txtRank = findViewById(R.id.txtRank);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);
        txtTotalQuestion = findViewById(R.id.txtTotalQuestion);
        txtTime = findViewById(R.id.txtTime);

        btnViewDetail = findViewById(R.id.btnViewDetail);
        btnBackHome = findViewById(R.id.btnBackHome);
    }

    private String getRank(double score) {
        if (score >= 8) return "Giỏi";
        else if (score >= 6.5) return "Khá";
        else if (score >= 5) return "Trung bình";
        else return "Yếu";
    }
}