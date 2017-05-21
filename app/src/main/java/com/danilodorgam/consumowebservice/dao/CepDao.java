package com.danilodorgam.consumowebservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danilodorgam.consumowebservice.database.DataBase;
import com.danilodorgam.consumowebservice.model.Cep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danil on 20/05/2017.
 */

public class CepDao {
    public static final String TB_NAME= "endereco";
    public static final String ID = "_id";
    public static final String CL_CEP="cep";
    public static final String CL_LOGRADOURO= "logradouro";
    public static final String CL_COMPLEMENTO="complemento";
    public static final String CL_BAIRRO = "bairro";
    public static final String CL_LOCALIDADE= "localidade";
    public static final String CL_UF= "uf";
    public static final String CL_UNIDADE= "unidade";
    public static final String CL_IBGE = "ibge";
    public static final String CL_GIA = "gia";
    public static final String SQL_CREATE = "CREATE TABLE "+TB_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                            CL_CEP + " VARCHAR(15) ,"+
                                            CL_LOGRADOURO + " VARCHAR(100) ,"+
                                            CL_COMPLEMENTO + " VARCHAR(100) ," +
                                            CL_BAIRRO + " VARCHAR(100) ," +
                                            CL_LOCALIDADE + " VARCHAR(100) ," +
                                            CL_UF + " VARCHAR(3) ," +
                                            CL_UNIDADE + " VARCHAR(100) ," +
                                            CL_IBGE + " VARCHAR(50) ," +
                                            CL_GIA + " VARCHAR(50) )";
    private DataBase mDataBase;
    private SQLiteDatabase mSQLiteDatabase;
    private ContentValues mContentValues;

    public CepDao(Context context){
        mDataBase = new DataBase(context);
    }
    public CepDao(){
        super();
    }

    public Boolean insertCep(Cep cep){
        Long resultado;
        mContentValues = new ContentValues();
        mContentValues.put(CL_CEP,cep.getCep());
        mContentValues.put(CL_LOGRADOURO,cep.getLogradouro());
        mContentValues.put(CL_COMPLEMENTO,cep.getComplemento());
        mContentValues.put(CL_BAIRRO,cep.getBairro());
        mContentValues.put(CL_LOCALIDADE,cep.getLocalidade());
        mContentValues.put(CL_UF,cep.getUf());
        mContentValues.put(CL_UNIDADE,cep.getUnidade());
        mContentValues.put(CL_IBGE,cep.getIbge());
        mContentValues.put(CL_GIA,cep.getGia());
        mSQLiteDatabase = mDataBase.getWritableDatabase();
        resultado = mSQLiteDatabase.insert(TB_NAME,null,mContentValues);
        mSQLiteDatabase.close();
        if(resultado == -1){
            return  true;
        }

        return  false;
    }

    public List<Cep> getCeps(int paginacao){
        Cursor cursor;
        List<Cep> list = new ArrayList<Cep>();
        mSQLiteDatabase = mDataBase.getReadableDatabase();
        cursor = mSQLiteDatabase.query(TB_NAME,null,null,null,null,null,null,""+paginacao+" ,10");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Cep tmp = new Cep();
            tmp.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            tmp.setCep(cursor.getString(cursor.getColumnIndex(CL_CEP)));
            tmp.setLogradouro(cursor.getString(cursor.getColumnIndex(CL_LOGRADOURO)));
            tmp.setComplemento(cursor.getString(cursor.getColumnIndex(CL_COMPLEMENTO)));
            tmp.setBairro(cursor.getString(cursor.getColumnIndex(CL_BAIRRO)));
            tmp.setLocalidade(cursor.getString(cursor.getColumnIndex(CL_LOCALIDADE)));
            tmp.setUf(cursor.getString(cursor.getColumnIndex(CL_UF)));
            tmp.setUnidade(cursor.getString(cursor.getColumnIndex(CL_UNIDADE)));
            tmp.setIbge(cursor.getString(cursor.getColumnIndex(CL_IBGE)));
            tmp.setGia(cursor.getString(cursor.getColumnIndex(CL_GIA)));
            list.add(tmp);
        }
        mSQLiteDatabase.close();

        return list;
    }
}
