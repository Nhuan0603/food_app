package com.example.food_app.fragment.Register_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBN0AME = "Login.db";

    public DBHelper( Context context) {

        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MYDB) {
        MYDB.execSQL("create table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDB, int oldVersion, int newVersion) {
        MYDB.execSQL("drop Table if exists users");
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",  new String[] {username});
        if(cursor.getCount() > 0 )
            return true;
        else return false;
    }
    public boolean checlusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0)
            return true;
        else return false;

    }
    public boolean updatePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("password", password);
        long result = MyDB.update("users",contentValues, "username = ?", new String[]{username});
        if(result == -1)
            return false;
        else
            return true;
    }
}
