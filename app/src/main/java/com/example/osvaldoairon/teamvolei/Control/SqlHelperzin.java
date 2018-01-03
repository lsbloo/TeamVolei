package com.example.osvaldoairon.teamvolei.Control;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by osvaldoairon on 26/12/2017.
 */

public class SqlHelperzin extends SQLiteOpenHelper {

    public static final String NOME_TABELA =  "tabela_jogadores";
    public static final String COLUNA_ID = "_id";
    public static final String JOGADOR_FICHA = "J";
    public static final int VERSAO_BANCO = 101;
    public static final String COLUNA_NOME_JOGADOR = "Nomes";


    public SqlHelperzin(Context ctx){

        super(ctx,JOGADOR_FICHA,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE IF NOT EXISTS " +NOME_TABELA+ "("+COLUNA_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUNA_NOME_JOGADOR +
                "TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
