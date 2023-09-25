package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.listviewactivity.NhanVien;
import java.util.ArrayList;

public class NhanVienDataSource {
    private SQLiteDatabase database;
    private QLNV_DatabaseHandler dbHelper;

    public NhanVienDataSource(Context context) {
        dbHelper = new QLNV_DatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void themNhanVien(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put(QLNV_DatabaseHandler.TBL_NHANVIEN_TNV, nhanVien.getTennv());
        values.put(QLNV_DatabaseHandler.TBL_NHANVIEN_PH, nhanVien.getPhongBan());

        database.insert(QLNV_DatabaseHandler.TBL_NHANVIEN, null, values);
    }

    public ArrayList<NhanVien> danhSachNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        if (database != null) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + QLNV_DatabaseHandler.TBL_NHANVIEN, null);
            if (cursor != null && cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(QLNV_DatabaseHandler.TBL_NHANVIEN_MNV);
                int tenColumnIndex = cursor.getColumnIndex(QLNV_DatabaseHandler.TBL_NHANVIEN_TNV);
                int phongBanColumnIndex = cursor.getColumnIndex(QLNV_DatabaseHandler.TBL_NHANVIEN_PH);
                do {
                    int id = cursor.getInt(idColumnIndex);
                    String ten = cursor.getString(tenColumnIndex);
                    String phongBan = cursor.getString(phongBanColumnIndex);

                    NhanVien nv = new NhanVien(id, ten, phongBan);
                    list.add(nv);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public void xoaNhanVien(int manv) {
        database.delete(QLNV_DatabaseHandler.TBL_NHANVIEN, QLNV_DatabaseHandler.TBL_NHANVIEN_MNV + "=?", new String[]{String.valueOf(manv)});
    }
}
