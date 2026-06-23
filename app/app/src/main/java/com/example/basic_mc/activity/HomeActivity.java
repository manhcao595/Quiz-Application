package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.basic_mc.R;

public class HomeActivity extends AppCompatActivity {

    private CardView cardStartExam;
    private CardView cardHistory;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardStartExam = findViewById(R.id.cardStartExam);
        cardHistory = findViewById(R.id.cardHistory);
        btnLogout = findViewById(R.id.btnLogout);

        // Bắt đầu thi
        cardStartExam.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            ExamListActivity.class
                    );

            startActivity(intent);
        });

        // Lịch sử thi
        cardHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            HistoryActivity.class
                    );

            startActivity(intent);
        });

        // Đăng xuất
        btnLogout.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            LoginActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();
        });
    }
}