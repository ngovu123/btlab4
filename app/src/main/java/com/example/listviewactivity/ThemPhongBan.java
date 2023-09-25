package com.example.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemPhongBan extends Activity {

    EditText edtTenPhong, edtMoTa;
    Button btnThemPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong_ban);

        edtTenPhong = findViewById(R.id.edtTenPhong);
        edtMoTa = findViewById(R.id.edtMoTa);
        btnThemPhong = findViewById(R.id.btnThemPhong);

        btnThemPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themPhongBan();
            }
        });
    }

    private void themPhongBan() {
        String tenPhong = edtTenPhong.getText().toString();
        String moTa = edtMoTa.getText().toString();

        // Thực hiện thêm phòng ban vào cơ sở dữ liệu ở đây

        // Gửi kết quả về MainActivity nếu cần
        Intent intent = new Intent();
        intent.putExtra("tenPhong", tenPhong);
        setResult(RESULT_OK, intent);
        finish();
    }
}
