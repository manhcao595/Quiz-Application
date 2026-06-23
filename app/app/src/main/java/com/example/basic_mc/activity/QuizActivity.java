package com.example.basic_mc.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basic_mc.R;
import com.example.basic_mc.database.CauHoiDAO;
import com.example.basic_mc.database.DatabaseHelper;
import com.example.basic_mc.database.DeThiDAO;
import com.example.basic_mc.model.CauHoi;
import com.example.basic_mc.model.DeThi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private TextView txtTenDe, txtSoCau, txtNoiDung, txtTimer;
    private RadioGroup radioGroup;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnPrev, btnNext, btnSubmit;
    private GridView gridQuestion;

    private ArrayList<CauHoi> dsCauHoi;
    private String[] userAnswers;

    private int currentIndex = 0;
    private int maDe;
    private int maTK = 1;

    private long timeLeftInMillis;
    private CountDownTimer timer;
    private long startTime;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();

        db = new DatabaseHelper(this).getWritableDatabase();

        maDe = getIntent().getIntExtra("MA_DE", -1);
        String tenDe = getIntent().getStringExtra("TEN_DE");

        txtTenDe.setText(tenDe);

        dsCauHoi = new CauHoiDAO(this).getByMaDe(maDe);

        if (dsCauHoi == null || dsCauHoi.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        userAnswers = new String[dsCauHoi.size()];

        DeThi deThi = new DeThiDAO(this).getById(maDe);
        int phut = (deThi != null) ? deThi.getThoiGian() : 10;

        timeLeftInMillis = phut * 60 * 1000;
        startTime = System.currentTimeMillis();

        startTimer();
        setupGrid();
        showQuestion();

        btnNext.setOnClickListener(v -> {
            saveAnswer();
            if (currentIndex < dsCauHoi.size() - 1) {
                currentIndex++;
                showQuestion();
            }
        });

        btnPrev.setOnClickListener(v -> {
            saveAnswer();
            if (currentIndex > 0) {
                currentIndex--;
                showQuestion();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            saveAnswer();
            submitExam();
        });

        gridQuestion.setOnItemClickListener((p, v, pos, id) -> {
            saveAnswer();
            currentIndex = pos;
            showQuestion();
        });
    }

    // ================= TIMER =================
    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            public void onTick(long ms) {
                timeLeftInMillis = ms;
                txtTimer.setText(String.format("⏳ %02d:%02d",
                        ms / 60000, (ms / 1000) % 60));
            }

            public void onFinish() {
                submitExam();
            }
        }.start();
    }

    // ================= VIEW =================
    private void initView() {
        txtTenDe = findViewById(R.id.txtTenDe);
        txtSoCau = findViewById(R.id.txtSoCau);
        txtNoiDung = findViewById(R.id.txtNoiDung);
        txtTimer = findViewById(R.id.txtTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);

        gridQuestion = findViewById(R.id.gridQuestion);
    }

    // ================= SHOW QUESTION =================
    private void showQuestion() {

        CauHoi ch = dsCauHoi.get(currentIndex);

        txtSoCau.setText((currentIndex + 1) + "/" + dsCauHoi.size());
        txtNoiDung.setText(ch.getNoiDung());

        rbA.setText(ch.getDapAnA());
        rbB.setText(ch.getDapAnB());
        rbC.setText(ch.getDapAnC());
        rbD.setText(ch.getDapAnD());

        radioGroup.clearCheck();

        if (userAnswers[currentIndex] != null) {
            switch (userAnswers[currentIndex]) {
                case "A": rbA.setChecked(true); break;
                case "B": rbB.setChecked(true); break;
                case "C": rbC.setChecked(true); break;
                case "D": rbD.setChecked(true); break;
            }
        }
    }

    // ================= SAVE ANSWER =================
    private void saveAnswer() {
        int id = radioGroup.getCheckedRadioButtonId();

        if (id == -1) {
            userAnswers[currentIndex] = null;
            return;
        }

        if (id == R.id.rbA) userAnswers[currentIndex] = "A";
        else if (id == R.id.rbB) userAnswers[currentIndex] = "B";
        else if (id == R.id.rbC) userAnswers[currentIndex] = "C";
        else userAnswers[currentIndex] = "D";
    }

    // ================= SUBMIT =================
    private void submitExam() {

        int correct = 0;

        for (int i = 0; i < dsCauHoi.size(); i++) {
            String u = userAnswers[i];
            String c = dsCauHoi.get(i).getDapAnDung();

            if (u != null && c != null && u.equalsIgnoreCase(c.trim())) {
                correct++;
            }
        }

        double diem = correct * 10.0 / dsCauHoi.size();

        if (timer != null) timer.cancel();

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;

        String timeString = String.format("%02d:%02d",
                duration / 60, duration % 60);

        // ================= INSERT BAI THI =================
        ContentValues bt = new ContentValues();
        bt.put("MaTK", maTK);
        bt.put("MaDe", maDe);
        bt.put("NgayThi", new SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()).format(new Date()));
        bt.put("SoCauDung", correct);
        bt.put("Diem", diem);

        long maBaiThi = db.insert("BaiThi", null, bt);

        Log.d("QUIZ", "maBaiThi = " + maBaiThi);

        if (maBaiThi == -1) {
            Toast.makeText(this, "Lưu bài thi thất bại!", Toast.LENGTH_SHORT).show();
            return;
        }

        // ================= INSERT CHI TIẾT =================
        for (int i = 0; i < dsCauHoi.size(); i++) {

            ContentValues ct = new ContentValues();
            ct.put("MaBaiThi", maBaiThi);
            ct.put("MaCH", dsCauHoi.get(i).getMaCH());
            ct.put("DapAnChon", userAnswers[i]);

            long r = db.insert("ChiTietBaiThi", null, ct);

            if (r == -1) {
                Log.d("QUIZ", "Insert ChiTiet FAIL at i=" + i);
            }
        }

        // ================= GO RESULT =================
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("MA_BAITHI", maBaiThi);
        intent.putExtra("DIEM", diem);
        intent.putExtra("SO_DUNG", correct);
        intent.putExtra("TONG", dsCauHoi.size());
        intent.putExtra("TIME", timeString);

        startActivity(intent);
        finish();
    }

    private void setupGrid() {

        Integer[] arr = new Integer[dsCauHoi.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;

        gridQuestion.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        ));
    }
}