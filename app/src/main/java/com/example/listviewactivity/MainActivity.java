package com.example.listviewactivity;

import android.app.Person;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_EMPLOYEE = 55;
    public static final int EDIT_EMPLOYEE = 56;
    public static final int SAVE_NEW_EMPLOYEE = 57;
    public static final int SAVE_EDIT_EMPLOYEE = 58;

    ArrayList <NhanVien> arrayList = new ArrayList<NhanVien>();
    ListView listView;
    int vitrichon = -1;
    ArrayAdapter<NhanVien> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvNhanVien);
        arrayList.add(new NhanVien(1, "Nguyễn Văn A"));
        arrayList.add(new NhanVien(2, "Nguyễn Kim Duy"));
        arrayList.add(new NhanVien(3, "Nguyễn Thị B"));

        adapter = new ArrayAdapter<NhanVien>(this,android.R.layout.simple_list_item_1, arrayList);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.mnulistviewcontext,menu);
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
                doDeleteEmplyee();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void doDeleteEmplyee() {

    }

    private void doEditEmployee() {
    }

    private void doNewEmployee() {
        Intent intent = new Intent(this, ThemMoiNhanVien.class);
        startActivityForResult(intent,MainActivity.NEW_EMPLOYEE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MainActivity.NEW_EMPLOYEE:
                if (resultCode == MainActivity.SAVE_NEW_EMPLOYEE)
                {
                    Bundle b = data.getExtras();
                    NhanVien p = (NhanVien) b.getSerializable("nv");
                    arrayList.add(p);
                    adapter.notifyDataSetChanged();
                }
        }

    }
}