package com.example.basic_mc.model;

public class CauHoi {

    private int maCH;
    private int maDe;
    private String noiDung;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;

    public CauHoi() {
    }

    public CauHoi(int maCH, int maDe, String noiDung,
                  String dapAnA, String dapAnB,
                  String dapAnC, String dapAnD,
                  String dapAnDung) {

        this.maCH = maCH;
        this.maDe = maDe;
        this.noiDung = noiDung;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
    }

    public int getMaCH() {
        return maCH;
    }

    public void setMaCH(int maCH) {
        this.maCH = maCH;
    }

    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
}
