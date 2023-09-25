package com.example.listviewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetails extends Activity {
    private TextView tvTenNV;
    private TextView tvPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        tvTenNV = findViewById(R.id.tvTenNV);
        tvPhongBan = findViewById(R.id.tvPhongBan);

        // Nhận thông tin từ intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String tenNV = extras.getString("tenNV");
            String phongBan = extras.getString("phongBan");

            // Hiển thị thông tin lên TextView
            tvTenNV.setText("Tên nhân viên: " + tenNV);
            tvPhongBan.setText("Phòng ban: " + phongBan);
        }
    }
}
