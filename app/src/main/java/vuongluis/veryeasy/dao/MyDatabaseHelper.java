package vuongluis.veryeasy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vuongluis.veryeasy.bean.User;

/**
 * Created by vuongluis on 5/14/2016.
 */
@SuppressWarnings("all")
public class MyDatabaseHelper extends SQLiteOpenHelper{

    private final static String TAG = "SQLite";
    private final static String DATABASE_NAME = "quanlysanbong";
    private final static int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "users";
    private static final String COLUMN_ID_USER = "id_user";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_ADDRESS = "address";

    public MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID_USER + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT," + COLUMN_FULLNAME +" TEXT,"+ COLUMN_BIRTHDAY+ " TEXT,"+ COLUMN_ADDRESS +" TEXT"+")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpprade ... ");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }
    /**
     * Kiểm tra xem trong cơ sở dữ liệu đã có giá trị chưa nếu chưa có ta khởi tạo giá trị mặc định
     * - getCount() lấy ra tổng số bảng ghi
     * - check value = 0 => Insert một vài giá trị mặc định
     * - gọi insertValue => Chèn dữ liệu vào nhé.
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FULLNAME, user.getFullname());
        values.put(COLUMN_BIRTHDAY, user.getBirthday());
        values.put(COLUMN_ADDRESS, user.getAddress());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    /**
     * Lấy ra một đối tượng user cụ thể
     */
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { COLUMN_ID_USER,
                        COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_FULLNAME, COLUMN_BIRTHDAY,COLUMN_ADDRESS }, COLUMN_ID_USER + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        return user;
    }
    /**
     * Lấy ra danh sách các đối tượng user có trong hệ thống
     */
    public List<User> getListUser(){
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId_user(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setFullname(cursor.getString(3));
                user.setBirthday(cursor.getString(4));
                user.setAddress(cursor.getString(5));
                // Thêm vào danh sách.
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }
    /**
     * Cập nhật thông tin user
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_FULLNAME,user.getFullname());
        values.put(COLUMN_BIRTHDAY,user.getBirthday());
        values.put(COLUMN_ADDRESS,user.getAddress());

        return db.update(TABLE_USER, values, COLUMN_ID_USER + " = ?",
                new String[]{String.valueOf(user.getId_user())});
    }
    /**
     * Xóa thông tin user
     */
    public void deleteUser(User note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_ID_USER + " = ?",new String[]{String.valueOf(note.getId_user())});
        db.close();
    }
}
