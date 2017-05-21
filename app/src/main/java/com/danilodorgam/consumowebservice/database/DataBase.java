package com.danilodorgam.consumowebservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danilodorgam.consumowebservice.dao.CepDao;

/**
 * Created by danil on 20/05/2017.
 */

public class DataBase extends SQLiteOpenHelper{
    private static final String DB_NAME = "bancodedados.db";
    private static final int DB_VERSION =  1;


    public DataBase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CepDao.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
