package Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.listviewactivity.PhongBan;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDataSource {
    QLNV_DatabaseHandler dbHelper;
    SQLiteDatabase db;
    Context context;

    public PhongBanDataSource(Context context) {
        this.context = context;
        this.dbHelper = new QLNV_DatabaseHandler(context);
    }

    public PhongBanDataSource open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public List<PhongBan> danhsachPhong() {
        List<PhongBan> list = new ArrayList<PhongBan>();
        String sql = "select * from " + QLNV_DatabaseHandler.TBL_PHONG;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PhongBan ph = new PhongBan(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            list.add(ph);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @SuppressLint("Range")
    public PhongBan timPhongtheoMa(int maPH) {
        PhongBan temp = new PhongBan();
        String sql = "select * from " + QLNV_DatabaseHandler.TBL_PHONG + " where " + QLNV_DatabaseHandler.TBL_PHONG_MAPH + " = " + maPH;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            temp.setMaph(maPH);
            temp.setTenph(cursor.getString(cursor.getColumnIndex(QLNV_DatabaseHandler.TBL_PHONG_TENPH)));
            temp.setMota(cursor.getString(cursor.getColumnIndex(QLNV_DatabaseHandler.TBL_PHONG_MOTA)));
            cursor.moveToNext();
        }
        cursor.close();
        return temp;
    }

    public String themPhong(PhongBan ph) {
        ContentValues values = new ContentValues();
        values.put(QLNV_DatabaseHandler.TBL_PHONG_TENPH, ph.getTenph());
        values.put(QLNV_DatabaseHandler.TBL_PHONG_MOTA, ph.getMota());
        long ma = db.insert(QLNV_DatabaseHandler.TBL_PHONG, null, values);
        String tenph = timPhongtheoMa((int) ma).getTenph();
        return tenph;
    }

    public int soluongphong() {
        String sql = "select count(*) as soluong from " + QLNV_DatabaseHandler.TBL_PHONG;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int soluong = 0;
        while (!cursor.isAfterLast()) {
            soluong = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return soluong;
    }

    public int xoaPhongtheoMa(int maPH) {
        String whereClause = QLNV_DatabaseHandler.TBL_PHONG_MAPH + "=?";
        String[] whereArgs = new String[]{String.valueOf(maPH)};
        return (db.delete(QLNV_DatabaseHandler.TBL_PHONG, whereClause, whereArgs));
    }

    public void capnhatPhong(int maPH, String ten, String mota) {
        ContentValues values = new ContentValues();
        values.put(QLNV_DatabaseHandler.TBL_PHONG_TENPH, ten);
        values.put(QLNV_DatabaseHandler.TBL_PHONG_MOTA, mota);
        String whereClause = QLNV_DatabaseHandler.TBL_PHONG_MAPH + "=?";
        String[] whereArgs = new String[]{String.valueOf(maPH)};
        db.update(QLNV_DatabaseHandler.TBL_PHONG, values, whereClause, whereArgs);
    }
}
