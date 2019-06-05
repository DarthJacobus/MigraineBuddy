package com.example.migranebuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "MigraineBuddy Database";
    public static final String TABLE_NAME = "User table";
    public static final int DATABASE_VERSION = 1;
    public static final String COL1 = "EMAIL";
    public static final String COL2 = "PASSWORD";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "PASSWORD";

    private static String email, password, firstName, lastName;



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE  userTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EMAIL TEXT, " +
                "PASSWORD TEXT, " +
                "FIRSTNAME TEXT, " +
                "LASTNAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String email, String password, String firstName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, email);
        contentValues.put(COL2, password);
        contentValues.put(COL3, firstName);
        contentValues.put(COL4, lastName);

        long res = db.insert("userTable", null, contentValues);
        db.close();
        return res;
    }

    public Boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from userTable where email=?", new String[] {email});
        if(cursor.getCount() > 0) {
            return false;
        }
        else {
            return true;
        }


    }

    public Boolean checkUser(String email, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and password =?", new String[] {email, password});
        cursor.close();
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }

    }










}
