package com.example.allgodwallpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    int image;

    public DBHelper(Context context, int image) {
        super(context, DATABASE_NAME, null, version);
        this.image = image;
    }

    private static int version = 2;
    private static String DATABASE_NAME = "myDataBase.db";
    private static String TABLE_NAME = "myFav";
    private static String COL1 = "ID";
    private static String COL2 = "IMAGE";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE table " + TABLE_NAME + "(" + COL1 + " integer PRIMARY KEY AUTOINCREMENT," + COL2 + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

    public boolean insertdata(int img) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, img);

        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " = " + img, null);
        if (res.getCount() > 0) {
            return false;

        } else {
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            return true;
        }
    }

    public Cursor getalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public Cursor getalldataunique(int img) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " =" + img, null);
        return res;
    }

    public int delete(int img) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_NAME ,COL2 + " = " + img, null);
        return res;
    }

}
