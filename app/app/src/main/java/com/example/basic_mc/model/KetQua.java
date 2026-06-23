package com.example.basic_mc.model;

public class KetQua {

    private int maKetQua;
    private int maBaiThi;
    private int tongSoCau;
    private int soCauDung;
    private int soCauSai;
    private double diem;

    public KetQua() {
    }

    public KetQua(int maKetQua,
                  int maBaiThi,
                  int tongSoCau,
                  int soCauDung,
                  int soCauSai,
                  double diem) {

        this.maKetQua = maKetQua;
        this.maBaiThi = maBaiThi;
        this.tongSoCau = tongSoCau;
        this.soCauDung = soCauDung;
        this.soCauSai = soCauSai;
        this.diem = diem;
    }

    public int getMaKetQua() {
        return maKetQua;
    }

    public void setMaKetQua(int maKetQua) {
        this.maKetQua = maKetQua;
    }

    public int getMaBaiThi() {
        return maBaiThi;
    }

    public void setMaBaiThi(int maBaiThi) {
        this.maBaiThi = maBaiThi;
    }

    public int getTongSoCau() {
        return tongSoCau;
    }

    public void setTongSoCau(int tongSoCau) {
        this.tongSoCau = tongSoCau;
    }

    public int getSoCauDung() {
        return soCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        this.soCauDung = soCauDung;
    }

    public int getSoCauSai() {
        return soCauSai;
    }

    public void setSoCauSai(int soCauSai) {
        this.soCauSai = soCauSai;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}
