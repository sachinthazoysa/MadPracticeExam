package com.example.madmodelpaper2020.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.madmodelpaper2020.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLUMN_NAME_USERNAME + " Text," +
                        UserProfile.Users.COLUMN_NAME_BD + " Text," +
                        UserProfile.Users.COLUMN_NAME_GENDER + " Text," +
                        UserProfile.Users.COLUMN_NAME_PASSWORD + " Text)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addInfo(String userName,String password, String birthday, String gender){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME, userName);
        values.put(UserProfile.Users.COLUMN_NAME_BD, birthday);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER, gender);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD, password);

        long newRowId= db.insert(UserProfile.Users.TABLE_NAME, null, values);

        if(newRowId == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean updateInfo(String userName, String password, String brithday, String gender){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD,password);
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UserProfile.Users.COLUMN_NAME_BD,brithday);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER,gender);

        String selection = UserProfile.Users._ID + " LIKE ?";
        String[] selectionArgs = { userName };

        int count= db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return true;
    }

    public List readAllInfo(){
        SQLiteDatabase db= getReadableDatabase();

        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_BD,
                UserProfile.Users.COLUMN_NAME_GENDER,
        };
        String sortOrder = UserProfile.Users.COLUMN_NAME_USERNAME + " DESC";
        Cursor cursor =db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames= new ArrayList<>();
        List passwords= new ArrayList<>();
        List birthdays= new ArrayList<>();
        List genders= new ArrayList<>();




        while (cursor.moveToNext()){
            String username= cursor.getString(cursor.getColumnIndexOrThrow((UserProfile.Users.COLUMN_NAME_USERNAME)));
            String password= cursor.getString(cursor.getColumnIndexOrThrow((UserProfile.Users.COLUMN_NAME_PASSWORD)));
            String birthday= cursor.getString(cursor.getColumnIndexOrThrow((UserProfile.Users.COLUMN_NAME_BD)));
            String gender= cursor.getString(cursor.getColumnIndexOrThrow((UserProfile.Users.COLUMN_NAME_GENDER)));
            userNames.add(username);
            passwords.add(password);
            birthdays.add(birthday);
            genders.add(gender);

        }
        cursor.close();
        return userNames;
    }

    public boolean readInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        boolean value = false;
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_BD,
                UserProfile.Users.COLUMN_NAME_GENDER,
        };

        String selection = UserProfile.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs= {userName};

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String username= cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String password= cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
            String birthday= cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_BD));
            String gender= cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));

            value =  userName.equals(username);
        }
        cursor.close();
        return value;
    }

    public int deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = UserProfile.Users.COLUMN_NAME_USERNAME + " Like ?";
        String[] selectionArgs = { userName };
        return db.delete(UserProfile.Users.TABLE_NAME,selection,selectionArgs);
    }

}
