package com.lik.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class RegisterDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Register.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbl_registers";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_CURRENT_PASS = "Current_Pass";
    private static final String COLUMN_NEW_PASS = "New_Pass";
    private static final String COLUMN_CONFIRM_NEW_PASS = "Confirm_New_Pass";

    RegisterDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +
                        TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_CURRENT_PASS + " TEXT, " +
                        COLUMN_NEW_PASS + " TEXT, " +
                        COLUMN_CONFIRM_NEW_PASS + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String Name, String Email, String Current_Pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, Name);
        cv.put(COLUMN_EMAIL, Email);
        cv.put(COLUMN_CURRENT_PASS, Current_Pass);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String Id, String Name, String Email, String Current_Pass, String New_Pass, String Confirm_New_Pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, Name);
        cv.put(COLUMN_EMAIL, Email);
        cv.put(COLUMN_CURRENT_PASS, Current_Pass);
        cv.put(COLUMN_NEW_PASS, New_Pass);
        cv.put(COLUMN_CONFIRM_NEW_PASS, Confirm_New_Pass);

        long result = db.update(TABLE_NAME, cv, "Id=?", new String[]{Id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "Id=?", new String[]{Id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
