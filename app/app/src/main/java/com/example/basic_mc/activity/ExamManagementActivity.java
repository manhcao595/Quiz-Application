package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.DeThiDAO;
import com.example.basic_mc.model.DeThi;

import java.util.ArrayList;

public class ExamManagementActivity extends AppCompatActivity {

    private EditText edtTenDe, edtMoTa, edtThoiGian;
    private Button btnThem, btnSua, btnXoa, btnQuanLyCauHoi, btnBack;
    private ListView lvDeThi;

    private DeThiDAO deThiDAO;
    private ArrayList<DeThi> dsDeThi;

    private int selectedId = -1;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_management);

        initView();

        deThiDAO = new DeThiDAO(this);

        loadData();

        btnThem.setOnClickListener(v -> themDeThi());
        btnSua.setOnClickListener(v -> suaDeThi());
        btnXoa.setOnClickListener(v -> xoaDeThi());

        btnQuanLyCauHoi.setOnClickListener(v -> moCauHoi());

        btnBack.setOnClickListener(v -> finish());

        lvDeThi.setOnItemClickListener((parent, view, position, id) -> {

            DeThi dt = dsDeThi.get(position);

            selectedId = dt.getMaDe();
            selectedPosition = position;

            edtTenDe.setText(dt.getTenDe());
            edtMoTa.setText(dt.getMoTa());
            edtThoiGian.setText(String.valueOf(dt.getThoiGian()));

            ((ArrayAdapter) lvDeThi.getAdapter()).notifyDataSetChanged();
        });
    }

    private void initView() {
        edtTenDe = findViewById(R.id.edtTenDe);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtThoiGian = findViewById(R.id.edtThoiGian);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnQuanLyCauHoi = findViewById(R.id.btnQuanLyCauHoi);
        btnBack = findViewById(R.id.btnBack);

        lvDeThi = findViewById(R.id.lvDeThi);
    }

    private void loadData() {

        dsDeThi = deThiDAO.getAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getListString()
        ) {
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {

                android.view.View view = super.getView(position, convertView, parent);

                if (position == selectedPosition) {
                    view.setBackgroundColor(0xFFBBDEFB); // xanh nhạt nổi bật
                } else {
                    view.setBackgroundColor(0x00000000); // bình thường
                }

                return view;
            }
        };

        lvDeThi.setAdapter(adapter);
    }
    private ArrayList<String> getListString() {

        ArrayList<String> list = new ArrayList<>();

        for (DeThi dt : dsDeThi) {
            list.add(dt.getTenDe() + " (" + dt.getThoiGian() + " phút)");
        }

        return list;
    }
    private void themDeThi() {

        String ten = edtTenDe.getText().toString().trim();
        String moTa = edtMoTa.getText().toString().trim();
        String tg = edtThoiGian.getText().toString().trim();

        if (ten.isEmpty() || moTa.isEmpty() || tg.isEmpty()) {
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        DeThi dt = new DeThi();
        dt.setTenDe(ten);
        dt.setMoTa(moTa);
        dt.setThoiGian(Integer.parseInt(tg));

        deThiDAO.insert(dt);

        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();

        loadData();
        clearForm();
    }

    private void suaDeThi() {

        if (selectedId == -1) {
            Toast.makeText(this, "Chọn đề thi", Toast.LENGTH_SHORT).show();
            return;
        }

        DeThi dt = new DeThi();
        dt.setMaDe(selectedId);
        dt.setTenDe(edtTenDe.getText().toString());
        dt.setMoTa(edtMoTa.getText().toString());
        dt.setThoiGian(Integer.parseInt(edtThoiGian.getText().toString()));

        deThiDAO.update(dt);

        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

        loadData();
    }

    private void xoaDeThi() {

        if (selectedId == -1) {
            Toast.makeText(this, "Chọn đề thi", Toast.LENGTH_SHORT).show();
            return;
        }

        deThiDAO.delete(selectedId);

        selectedId = -1;

        loadData();
        clearForm();
    }

    private void moCauHoi() {

        if (selectedId == -1) {
            Toast.makeText(this, "Chọn đề thi", Toast.LENGTH_SHORT).show();
            return;
        }

        DeThi dt = null;

        for (DeThi item : dsDeThi) {
            if (item.getMaDe() == selectedId) {
                dt = item;
                break;
            }
        }

        if (dt == null) return;

        Intent intent = new Intent(this, QuestionManagementActivity.class);
        intent.putExtra("MA_DE", dt.getMaDe());
        intent.putExtra("TEN_DE", dt.getTenDe());
        startActivity(intent);
    }

    private void clearForm() {
        edtTenDe.setText("");
        edtMoTa.setText("");
        edtThoiGian.setText("");
    }
}