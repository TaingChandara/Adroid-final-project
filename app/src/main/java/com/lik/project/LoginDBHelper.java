package com.lik.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public LoginDBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table tbl_users(email VARCHAR primary key, password VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists tbl_users");
    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("tbl_users", null, contentValues);
        if(result==-1) return false;
        else return true;
    }
    public Boolean check_email(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from tbl_users where email = ?", new String[]{email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean check_email_password(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from tbl_users where email = ? and password = ?", new String[]{email,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
