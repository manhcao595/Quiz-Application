package com.example.basic_mc.activity;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.CauHoiDAO;
import com.example.basic_mc.model.CauHoi;

import java.util.ArrayList;

public class QuestionManagementActivity extends AppCompatActivity {

    private int maDe;

    private EditText edtNoiDung, edtA, edtB, edtC, edtD, edtDapAnDung;

    private Button btnThem, btnSua, btnXoa, btnBack;

    private ListView lvCauHoi;

    private CauHoiDAO dao;
    private ArrayList<CauHoi> dsCauHoi;

    private int selectedId = -1;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_management);

        maDe = getIntent().getIntExtra("MA_DE", -1);

        initView();

        dao = new CauHoiDAO(this);

        loadData();

        btnThem.setOnClickListener(v -> them());
        btnSua.setOnClickListener(v -> sua());
        btnXoa.setOnClickListener(v -> xoa());

        btnBack.setOnClickListener(v -> finish());

        lvCauHoi.setOnItemClickListener((parent, view, position, id) -> {

            CauHoi ch = dsCauHoi.get(position);

            selectedId = ch.getMaCH();
            selectedPosition = position;

            edtNoiDung.setText(ch.getNoiDung());
            edtA.setText(ch.getDapAnA());
            edtB.setText(ch.getDapAnB());
            edtC.setText(ch.getDapAnC());
            edtD.setText(ch.getDapAnD());
            edtDapAnDung.setText(ch.getDapAnDung());

            ((ArrayAdapter) lvCauHoi.getAdapter()).notifyDataSetChanged();
        });
    }

    private void initView() {

        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        edtD = findViewById(R.id.edtD);
        edtDapAnDung = findViewById(R.id.edtDapAnDung);

        btnThem = findViewById(R.id.btnThemCauHoi);
        btnSua = findViewById(R.id.btnSuaCauHoi);
        btnXoa = findViewById(R.id.btnXoaCauHoi);
        btnBack = findViewById(R.id.btnBack);

        lvCauHoi = findViewById(R.id.lvCauHoi);
    }

    private void loadData() {

        dsCauHoi = dao.getByMaDe(maDe);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getListString()
        ) {
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {

                android.view.View view = super.getView(position, convertView, parent);

                if (position == selectedPosition) {
                    view.setBackgroundColor(0xFFFFCDD2); // 🔴 đỏ nhạt
                } else {
                    view.setBackgroundColor(0x00000000); // bình thường
                }

                return view;
            }
        };

        lvCauHoi.setAdapter(adapter);
    }

    private ArrayList<String> getListString() {

        ArrayList<String> list = new ArrayList<>();

        for (CauHoi ch : dsCauHoi) {
            list.add(ch.getNoiDung());
        }

        return list;
    }

    private void them() {

        CauHoi ch = new CauHoi();

        ch.setMaDe(maDe);
        ch.setNoiDung(edtNoiDung.getText().toString());
        ch.setDapAnA(edtA.getText().toString());
        ch.setDapAnB(edtB.getText().toString());
        ch.setDapAnC(edtC.getText().toString());
        ch.setDapAnD(edtD.getText().toString());
        ch.setDapAnDung(edtDapAnDung.getText().toString());

        dao.insert(ch);

        loadData();
        clear();
    }

    private void sua() {

        if (selectedId == -1) {
            Toast.makeText(this, "Chọn câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        }

        CauHoi ch = new CauHoi();
        ch.setMaCH(selectedId);
        ch.setMaDe(maDe);

        ch.setNoiDung(edtNoiDung.getText().toString());
        ch.setDapAnA(edtA.getText().toString());
        ch.setDapAnB(edtB.getText().toString());
        ch.setDapAnC(edtC.getText().toString());
        ch.setDapAnD(edtD.getText().toString());
        ch.setDapAnDung(edtDapAnDung.getText().toString());

        dao.update(ch);

        loadData();
        clear();
    }

    private void xoa() {

        if (selectedId == -1) {
            Toast.makeText(this, "Chọn câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        }

        dao.delete(selectedId);

        selectedId = -1;

        loadData();
        clear();
    }

    private void clear() {
        edtNoiDung.setText("");
        edtA.setText("");
        edtB.setText("");
        edtC.setText("");
        edtD.setText("");
        edtDapAnDung.setText("");
    }
}