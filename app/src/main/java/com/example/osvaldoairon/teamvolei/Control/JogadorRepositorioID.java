package com.example.osvaldoairon.teamvolei.Control;

/**
 * Created by osvaldoairon on 26/12/2017.
 */

import java.util.ArrayList;

import android.content.Context;
import com.example.osvaldoairon.teamvolei.Model.Jogador;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import android.database.Cursor;

public class JogadorRepositorioID {

    private ArrayList<String> id;
    private SqlHelperzin helper2;

    private int indexColunaID;


    public JogadorRepositorioID(Context ctx){
        helper2 = new SqlHelperzin(ctx);
    }

    public void Inserir(Jogador jogador){

        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues cv = new ContentValues();


        cv.put(SqlHelperzin.COLUNA_NOME_JOGADOR, jogador.getNome());

        long id = db.insert(SqlHelperzin.NOME_TABELA,null,cv);
        //if(id != -1){
          //  jogador.setId(Long.parseLong(String.valueOf(id)));
        //}

        db.close();

    }



    public ArrayList listarID(){
        SQLiteDatabase db = helper2.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ SqlHelperzin.NOME_TABELA,null);

        indexColunaID = cursor.getColumnIndex("_id");
        int indexColunaNome = cursor.getColumnIndex("nome");


        cursor.moveToFirst();


        while(cursor.moveToNext()){

            id.add(cursor.getString(indexColunaID));
            id.add(cursor.getString(indexColunaNome));

        }



        return id;

    }
}
