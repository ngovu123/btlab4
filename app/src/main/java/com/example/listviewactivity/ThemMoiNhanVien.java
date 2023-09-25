package com.example.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ThemMoiNhanVien extends Activity implements View.OnClickListener {
    Button btnSubmit;
    EditText txtid, txtname;
    Spinner spinnerDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themmoinhanvien);

        txtid = findViewById(R.id.txtempid);
        txtname = findViewById(R.id.txtName);
        btnSubmit = findViewById(R.id.btnBack);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.departments,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();

        int id = Integer.parseInt(txtid.getText().toString());
        String ten = txtname.getText().toString();
        String phongBan = spinnerDepartment.getSelectedItem().toString();

        NhanVien nv = new NhanVien(id, ten, phongBan);
        bundle.putSerializable("nv", nv);
        intent.putExtras(bundle);
        setResult(MainActivity.SAVE_NEW_EMPLOYEE, intent);
        finish();
    }
}
