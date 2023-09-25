package com.example.listviewactivity;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int manv;
    private String tennv;
    private String phongBan; // Thêm thuộc tính phòng ban

    public NhanVien(int manv, String tennv, String phongBan) {
        this.manv = manv;
        this.tennv = tennv;
        this.phongBan = phongBan;
    }

    public int getManv() {
        return manv;
    }

    public String getTennv() {
        return tennv;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    @Override
    public String toString() {
        return manv + "-" + tennv;
    }

}
