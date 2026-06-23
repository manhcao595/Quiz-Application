package com.example.basic_mc.model;

public class BaiThi {

    private int maBaiThi;
    private int maTK;
    private int maDe;
    private String ngayThi;
    private int soCauDung;
    private double diem;

    public BaiThi() {
    }

    public BaiThi(int maBaiThi, int maTK,
                  int maDe, String ngayThi,
                  int soCauDung, double diem) {

        this.maBaiThi = maBaiThi;
        this.maTK = maTK;
        this.maDe = maDe;
        this.ngayThi = ngayThi;
        this.soCauDung = soCauDung;
        this.diem = diem;
    }

    public int getMaBaiThi() {
        return maBaiThi;
    }

    public void setMaBaiThi(int maBaiThi) {
        this.maBaiThi = maBaiThi;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getSoCauDung() {
        return soCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        this.soCauDung = soCauDung;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}