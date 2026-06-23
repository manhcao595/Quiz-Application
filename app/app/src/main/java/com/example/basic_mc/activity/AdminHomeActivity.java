package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;

public class AdminHomeActivity extends AppCompatActivity {

    private Button btnExamManagement;
    private Button btnStatistics;
    private Button btnLogoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btnExamManagement =
                findViewById(R.id.btnExamManagement);

        btnStatistics =
                findViewById(R.id.btnStatistics);

        btnLogoutAdmin =
                findViewById(R.id.btnLogoutAdmin);


        // Quản lý đề thi
        btnExamManagement.setOnClickListener(v -> {
            startActivity(
                    new Intent(
                            AdminHomeActivity.this,
                            ExamManagementActivity.class
                    )
            );
        });

        // Thống kê
        btnStatistics.setOnClickListener(v -> {
            startActivity(
                    new Intent(
                            AdminHomeActivity.this,
                            StatisticsActivity.class
                    )
            );
        });

        // Đăng xuất
        btnLogoutAdmin.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            AdminHomeActivity.this,
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