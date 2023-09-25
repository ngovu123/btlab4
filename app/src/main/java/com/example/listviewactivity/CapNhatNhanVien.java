package com.example.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CapNhatNhanVien extends Activity {
    private EditText edtTenNV;
    private EditText edtPhongBan;
    private Button btnCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatnhanvien);

        edtTenNV = findViewById(R.id.edtCapNhatTenNV);
        edtPhongBan = findViewById(R.id.edtCapNhatPhongBan);
        btnCapNhat = findViewById(R.id.btnCapNhat);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin cập nhật từ EditText
                String tenNV = edtTenNV.getText().toString();
                String phongBan = edtPhongBan.getText().toString();

                // Tạo intent để trả lại thông tin cập nhật cho MainActivity
                Intent intent = new Intent();
                intent.putExtra("tenNV", tenNV);
                intent.putExtra("phongBan", phongBan);

                // Đặt kết quả là RESULT_OK và gửi intent
                setResult(RESULT_OK, intent);

                // Đóng activity
                finish();
            }
        });
    }
}
