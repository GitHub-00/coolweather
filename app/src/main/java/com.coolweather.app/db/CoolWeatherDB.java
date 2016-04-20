package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W on 2016/4/20.
 */
public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInsance(Context context){
        if (coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("PROVINCE_NAME",province.getProvinceName());
            values.put("PROVINCE_CODE",province.getProvinceCode());
            db.insert("PROVINCE",null,values);
        }
    }

    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("PROVINCE",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("PROVINCE_NAME")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("PROVINCE_CODE")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("CITY_NAME",city.getCityName());
            values.put("CITY_CODE",city.getCityCode());
            values.put("PROVINCE_ID",city.getProvinceId());
            db.insert("CITY",null,values);
        }
    }

    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("CITY",null,"PROVINCE_ID=?",new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("CITY_NAME")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("CITY_CODE")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("PROVINCE_ID")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void saveCountry(Country country){
        if (country != null){
            ContentValues values = new ContentValues();
            values.put("COUNTRY_NAME",country.getCountryName());
            values.put("COUNTRY_CODE",country.getCountryCode());
            values.put("CITY_ID",country.getCityId());
            db.insert("COUNTRY",null,values);
        }
    }

    public List<Country> loadCountries(int cityId){
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("CITY",null,"CITY_ID=?",new String[]{String.valueOf(cityId)},null,null,null);

        if (cursor.moveToFirst()){
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("COUNTRY_NAME")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("COUNTRY_CODE")));
                country.setCityId(cursor.getInt(cursor.getColumnIndex("CITY_ID")));
                list.add(country);
            }while (cursor.moveToNext());
        }

        return list;
    }
}
