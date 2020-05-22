package com.example.madmodelpaper2020;

import android.provider.BaseColumns;

public final class UserProfile{

    private UserProfile(){

    }

    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_BD = "birthday";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_ID = "ID";




    }


}
