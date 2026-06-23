package com.example.basic_mc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.DeThiDAO;
import com.example.basic_mc.model.DeThi;

import java.util.ArrayList;

public class ExamListActivity extends AppCompatActivity {

    private ListView lvExam;

    private Button btnBack;

    private DeThiDAO deThiDAO;

    private ArrayList<DeThi> dsDeThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        lvExam = findViewById(R.id.lvExam);

        deThiDAO = new DeThiDAO(this);

        loadData();

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        lvExam.setOnItemClickListener((parent, view, position, id) -> {

            DeThi deThi = dsDeThi.get(position);

            Intent intent =
                    new Intent(
                            ExamListActivity.this,
                            QuizActivity.class
                    );

            intent.putExtra(
                    "MA_DE",
                    deThi.getMaDe()
            );

            intent.putExtra(
                    "TEN_DE",
                    deThi.getTenDe()
            );

            startActivity(intent);
        });
    }

    private void loadData() {

        dsDeThi = deThiDAO.getAll();

        ArrayList<String> list =
                new ArrayList<>();

        for (DeThi dt : dsDeThi) {

            list.add(
                    dt.getTenDe()
                            + "\n"
                            + dt.getMoTa()
                            + "\nThời gian: "
                            + dt.getThoiGian()
                            + " phút"
            );
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        list
                );

        lvExam.setAdapter(adapter);
    }
}