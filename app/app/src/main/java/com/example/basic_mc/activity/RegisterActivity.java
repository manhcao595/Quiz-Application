package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.TaiKhoanDAO;
import com.example.basic_mc.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText edtFullName;
    private TextInputEditText edtUser;
    private TextInputEditText edtPass;
    private TextInputEditText edtConfirm;

    private Button btnRegister;
    private TextView txtLogin;

    private TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFullName = findViewById(R.id.edtFullName);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        edtConfirm = findViewById(R.id.edtConfirm);

        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        taiKhoanDAO = new TaiKhoanDAO(this);

        btnRegister.setOnClickListener(v -> dangKy());

        txtLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void dangKy() {

        String hoTen = edtFullName.getText().toString().trim();
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String confirm = edtConfirm.getText().toString().trim();

        if (hoTen.isEmpty() ||
                user.isEmpty() ||
                pass.isEmpty() ||
                confirm.isEmpty()) {

            Toast.makeText(
                    this,
                    "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (!pass.equals(confirm)) {

            Toast.makeText(
                    this,
                    "Mật khẩu xác nhận không khớp",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (taiKhoanDAO.isUsernameExists(user)) {

            Toast.makeText(
                    this,
                    "Tên đăng nhập đã tồn tại",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        TaiKhoan tk = new TaiKhoan();

        tk.setHoTen(hoTen);
        tk.setTenDangNhap(user);
        tk.setMatKhau(pass);
        tk.setVaiTro("USER");

        long result = taiKhoanDAO.insert(tk);

        if (result > 0) {

            Toast.makeText(
                    this,
                    "Đăng ký thành công",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Đăng ký thất bại",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}