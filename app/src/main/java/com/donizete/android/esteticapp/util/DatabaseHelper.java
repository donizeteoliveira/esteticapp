package com.donizete.android.esteticapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Donizete on 15/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "Produto.db";
    private static int VERSAO =1;

    public DatabaseHelper(Context context) {super(context, BANCO_DADOS, null, VERSAO);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE produto (_id INTEGER PRIMARY KEY, txtnome TEXT, txtcategoria TEXT, txtfornecedor TEXT, vlCliente REAL, vlprofissional REAL,  imgproduto BLOB);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
