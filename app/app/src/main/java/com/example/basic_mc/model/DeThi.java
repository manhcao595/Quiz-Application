package com.example.basic_mc.model;

public class DeThi {

    private int maDe;
    private String tenDe;
    private String moTa;
    private int thoiGian;

    public DeThi() {
    }

    public DeThi(int maDe, String tenDe,
                 String moTa, int thoiGian) {
        this.maDe = maDe;
        this.tenDe = tenDe;
        this.moTa = moTa;
        this.thoiGian = thoiGian;
    }

    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public String getTenDe() {
        return tenDe;
    }

    public void setTenDe(String tenDe) {
        this.tenDe = tenDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }
}