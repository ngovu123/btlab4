package com.example.listviewactivity;

public class PhongBan {
    private int maph;
    private String tenph;
    private String mota;

    public PhongBan() {
    }

    public PhongBan(int maph, String tenph, String mota) {
        this.maph = maph;
        this.tenph = tenph;
        this.mota = mota;
    }

    public int getMaph() {
        return maph;
    }

    public void setMaph(int maph) {
        this.maph = maph;
    }

    public String getTenph() {
        return tenph;
    }

    public void setTenph(String tenph) {
        this.tenph = tenph;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return tenph;
    }
}
