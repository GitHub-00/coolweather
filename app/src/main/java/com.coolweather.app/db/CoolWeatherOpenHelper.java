package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by W on 2016/4/20.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_PROVINCE = "CREATE TABLE PROVINCE ("
            +"ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"PROVINCE_NAME TEXT, "
            +"PROVINCE_CODE TEXT)";

    public static final String CREATE_CITY="CREATE TABLE CITY ("
            +"ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"CITY_NAME TEXT, "
            +"CITY_CODE TEXT, "
            +"PROVINCE_ID INTEGER";

    public static final String CREATE_COUNTRY="CREATE TABLE COUNTRY ("
            +"ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"COUNTRY_NAME TEXT, "
            +"COUNTRY_CODE TEXT, "
            +"CITY_ID INTEGER";


    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
