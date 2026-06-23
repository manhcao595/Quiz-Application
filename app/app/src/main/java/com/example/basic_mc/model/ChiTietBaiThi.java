package com.example.basic_mc.model;

public class ChiTietBaiThi {

    private int maChiTiet;
    private int maBaiThi;
    private int maCH;
    private String dapAnChon;

    public ChiTietBaiThi() {
    }

    public ChiTietBaiThi(int maChiTiet,
                         int maBaiThi,
                         int maCH,
                         String dapAnChon) {

        this.maChiTiet = maChiTiet;
        this.maBaiThi = maBaiThi;
        this.maCH = maCH;
        this.dapAnChon = dapAnChon;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaBaiThi() {
        return maBaiThi;
    }

    public void setMaBaiThi(int maBaiThi) {
        this.maBaiThi = maBaiThi;
    }

    public int getMaCH() {
        return maCH;
    }

    public void setMaCH(int maCH) {
        this.maCH = maCH;
    }

    public String getDapAnChon() {
        return dapAnChon;
    }

    public void setDapAnChon(String dapAnChon) {
        this.dapAnChon = dapAnChon;
    }
}
