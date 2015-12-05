package com.example.sarthakmeh.todo_android.Utils;

/**
 * Used SQLite Helper class for DB operations
 */
import java.util.Date;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "/ToDo.sqlite";
    private HashMap hp;
    public DBHelper(Context context)

    {
        super(context, Environment.getExternalStorageDirectory().getPath() + DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table to_do " +
                        "(id integer primary key autoincrement," +
                        "task text,time text,location text,status text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS to_do");
        onCreate(db);
    }

    public boolean insertData(String task, String time, String location,String status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("time", time);
        contentValues.put("location", location);
        contentValues.put("status",status);
        db.insert("to_do", null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from to_do", null);
        return res;
    }
//
//    public int numberOfRows() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, "user_location");
//        return numRows;
//    }
}