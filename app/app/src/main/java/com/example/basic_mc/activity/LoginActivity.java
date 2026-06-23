package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basic_mc.database.TaiKhoanDAO;
import com.example.basic_mc.model.TaiKhoan;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUsername;
    private TextInputEditText edtPassword;

    private Button btnLogin;
    private TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ View
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        // Chuyển sang màn hình đăng ký
        txtRegister.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );
            startActivity(intent);
        });

        // Tạm thời để trống xử lý đăng nhập
        btnLogin.setOnClickListener(v -> {

            String tenDangNhap =
                    edtUsername.getText().toString().trim();

            String matKhau =
                    edtPassword.getText().toString().trim();

            if (tenDangNhap.isEmpty()
                    || matKhau.isEmpty()) {

                Toast.makeText(
                        LoginActivity.this,
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            TaiKhoanDAO taiKhoanDAO =
                    new TaiKhoanDAO(this);

            TaiKhoan tk =
                    taiKhoanDAO.checkLogin(
                            tenDangNhap,
                            matKhau
                    );

            if (tk == null) {

                Toast.makeText(
                        LoginActivity.this,
                        "Sai tài khoản hoặc mật khẩu",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Toast.makeText(
                    LoginActivity.this,
                    "Đăng nhập thành công",
                    Toast.LENGTH_SHORT
            ).show();

            if ("ADMIN".equals(tk.getVaiTro())) {

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                AdminHomeActivity.class
                        );

                startActivity(intent);

            } else {

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                HomeActivity.class
                        );

                startActivity(intent);
            }

            finish();
        });
    }
}