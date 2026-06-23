package com.example.basic_mc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.StatisticsDAO;

public class StatisticsActivity extends AppCompatActivity {

    private TextView txtTaiKhoan;
    private TextView txtDeThi;
    private TextView txtCauHoi;
    private TextView txtLuotThi;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtDeThi = findViewById(R.id.txtDeThi);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        txtLuotThi = findViewById(R.id.txtLuotThi);

        StatisticsDAO dao =
                new StatisticsDAO(this);

        txtTaiKhoan.setText(
                String.valueOf(
                        dao.getTongTaiKhoan()
                )
        );

        txtDeThi.setText(
                String.valueOf(
                        dao.getTongDeThi()
                )
        );

        txtCauHoi.setText(
                String.valueOf(
                        dao.getTongCauHoi()
                )
        );

        txtLuotThi.setText(
                String.valueOf(
                        dao.getTongLuotThi()
                )
        );

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());
    }
}