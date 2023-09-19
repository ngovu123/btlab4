package com.example.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemMoiNhanVien extends Activity implements View.OnClickListener {
    Button btnSubmit;
    EditText txtid, txtname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themmoinhanvien);

        txtid = (EditText) findViewById(R.id.txtempid);
        txtname = (EditText) findViewById(R.id.txtName);
        btnSubmit = (Button) findViewById(R.id.btnBack);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent  = getIntent();
        Bundle bundle = new Bundle();

        int id = Integer.parseInt(txtid.getText().toString());
        String ten = txtname.getText().toString();
        NhanVien nv = new NhanVien(id,ten);
        bundle.putSerializable("nv",nv);
        intent.putExtras(bundle);
        setResult(MainActivity.SAVE_NEW_EMPLOYEE,intent);
        finish();
    }

}
