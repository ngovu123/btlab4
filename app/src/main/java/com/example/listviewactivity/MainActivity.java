package com.example.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import Database.NhanVienDataSource;

public class MainActivity extends AppCompatActivity {
    // Các hằng số
    public static final int NEW_EMPLOYEE = 55;
    public static final int EDIT_EMPLOYEE = 56;
    public static final int SAVE_NEW_EMPLOYEE = 57;
    public static final int SAVE_EDIT_EMPLOYEE = 58;

    // Các biến
    ArrayList<NhanVien> arrayList = new ArrayList<>();
    ListView listView;
    int vitrichon = -1;
    ArrayAdapter<NhanVien> adapter;
    NhanVienDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvNhanVien);
        dataSource = new NhanVienDataSource(this);
        dataSource.open(); // Mở cơ sở dữ liệu

        arrayList = dataSource.danhSachNhanVien();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vitrichon = position;
                return false;
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại danh sách nhân viên khi quay lại màn hình chính
        arrayList.clear();
        arrayList.addAll(dataSource.danhSachNhanVien());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close(); // Đóng cơ sở dữ liệu khi thoát ứng dụng
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.mnulistviewcontext, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuNew:
                doNewEmployee();
                break;
            case R.id.mnuUpdate:
                doEditEmployee();
                break;
            case R.id.mnuDel:
                doDeleteEmployee();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void doDeleteEmployee() {
        if (vitrichon != -1) {
            int manv = arrayList.get(vitrichon).getManv();
            dataSource.xoaNhanVien(manv);
            arrayList.remove(vitrichon);
            adapter.notifyDataSetChanged();
            vitrichon = -1;
        }
    }

    private void doEditEmployee() {
        // Thêm mã để chỉnh sửa nhân viên tại đây
        // Gọi Intent để chuyển đến activity CapNhatNhanVien
        Intent intent = new Intent(this, CapNhatNhanVien.class);
        startActivityForResult(intent, EDIT_EMPLOYEE);
    }

    private void doNewEmployee() {
        Intent intent = new Intent(this, ThemMoiNhanVien.class);
        startActivityForResult(intent, NEW_EMPLOYEE);
    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NEW_EMPLOYEE:
                if (resultCode == SAVE_NEW_EMPLOYEE && data != null) {
                    Bundle b = data.getExtras();
                    NhanVien p = (NhanVien) b.getSerializable("nv");
                    if (p != null) {
                        dataSource.themNhanVien(p);
                        arrayList.add(p);
                        adapter.notifyDataSetChanged(); // Cập nhật ListView
                    }
                }
                break;
            case EDIT_EMPLOYEE:
                // Xử lý sau khi chỉnh sửa nhân viên
                break;
        }
    }
}
