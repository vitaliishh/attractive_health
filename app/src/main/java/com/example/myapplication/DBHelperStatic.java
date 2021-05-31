package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelperStatic extends SQLiteAssetHelper {


    public static final String TABLE_DIET = "diet";

    public static final String KEY_ID = "_id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_OPT1 = "opt1";
    public static final String KEY_OPT2 = "opt2";
    public static final String KEY_OPT3 = "opt3";
    public static final String KEY_OPT4 = "opt4";
    public static final String KEY_OPT5 = "opt5";
    public static final String KEY_OPT6 = "opt6";
    public static final String KEY_GENDER = "gender";


    public static final String TABLE_TRAINING = "training";

    public static final String KEY_ID_2 = "_id";
    public static final String KEY_TYPE_2 = "type";
    public static final String KEY_OPT1_2 = "opt1";
    public static final String KEY_OPT2_2 = "opt2";
    public static final String KEY_OPT3_2 = "opt3";
    public static final String KEY_OPT4_2 = "opt4";
    public static final String KEY_GENDER_2 = "gender";


    public static final String TABLE_FACTS = "facts";

    public static final String KEY_ID_3 = "_id";
    public static final String KEY_OPT1_3 = "opt1";


    private static final String DATABASE_NAME = "db2.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelperStatic(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
}
