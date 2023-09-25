package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.listviewactivity.NhanVien;
import java.util.ArrayList;

public class QLNV_DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QLNV.sqlite";
    private static final int DATABASE_VERSION = 2;

    // Tên bảng và các cột
    public static final String TBL_NHANVIEN = "tblNhanVien";
    public static final String TBL_NHANVIEN_MNV = "MANV";
    public static final String TBL_NHANVIEN_TNV = "TENNV";
    public static final String TBL_NHANVIEN_PH = "PHONG";

    public static final String TBL_PHONG = "tblPhong";
    public static final String TBL_PHONG_MAPH = "MAPH";
    public static final String TBL_PHONG_TENPH = "TENPH";
    public static final String TBL_PHONG_MOTA = "MOTA";

    public QLNV_DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng và thêm dữ liệu mẫu ở đây
        String createNhanVienTable = "CREATE TABLE " + TBL_NHANVIEN + " (" +
                TBL_NHANVIEN_MNV + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TBL_NHANVIEN_TNV + " TEXT NOT NULL, " +
                TBL_NHANVIEN_PH + " TEXT)";
        db.execSQL(createNhanVienTable);

        String createPhongTable = "CREATE TABLE " + TBL_PHONG + " (" +
                TBL_PHONG_MAPH + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TBL_PHONG_TENPH + " TEXT NOT NULL, " +
                TBL_PHONG_MOTA + " TEXT)";
        db.execSQL(createPhongTable);

        // Thêm dữ liệu mẫu
        String insertNhanVienData = "INSERT INTO " + TBL_NHANVIEN + " (" + TBL_NHANVIEN_TNV + ", " + TBL_NHANVIEN_PH + ") " +
                "VALUES ('Nguyen Van A', 'Phong Hanh chinh'), " +
                "('Nguyen Kim Duy', 'Phong Hanh chinh'), " +
                "('Tran Thi C', 'Phong Ban Hang')";
        db.execSQL(insertNhanVienData);

        String insertPhongData = "INSERT INTO " + TBL_PHONG + " (" + TBL_PHONG_TENPH + ", " + TBL_PHONG_MOTA + ") " +
                "VALUES ('Phong Hanh chinh', 'Mô tả phòng hanh chinh'), " +
                "('Phong Ban Hang', 'Mô tả phòng ban hang')";
        db.execSQL(insertPhongData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa và tạo lại bảng khi cần nâng cấp cơ sở dữ liệu
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_PHONG);
        onCreate(db);
    }

    // Thêm nhân viên vào bảng nhân viên
    public void themNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TBL_NHANVIEN_TNV, nhanVien.getTennv());
        values.put(TBL_NHANVIEN_PH, nhanVien.getPhongBan());
        db.insert(TBL_NHANVIEN, null, values);
        db.close();
    }

    // Lấy danh sách nhân viên từ bảng nhân viên
    public ArrayList<NhanVien> danhSachNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NHANVIEN, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(TBL_NHANVIEN_MNV);
                int tenIndex = cursor.getColumnIndex(TBL_NHANVIEN_TNV);
                int phongBanIndex = cursor.getColumnIndex(TBL_NHANVIEN_PH);

                if (idIndex >= 0 && tenIndex >= 0 && phongBanIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String ten = cursor.getString(tenIndex);
                    String phongBan = cursor.getString(phongBanIndex);

                    NhanVien nv = new NhanVien(id, ten, phongBan);
                    list.add(nv);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // Xóa nhân viên từ bảng nhân viên
    public void xoaNhanVien(int manv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_NHANVIEN, TBL_NHANVIEN_MNV + "=?", new String[]{String.valueOf(manv)});
        db.close();
    }
}
