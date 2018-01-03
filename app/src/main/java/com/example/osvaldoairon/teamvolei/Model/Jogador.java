package com.example.osvaldoairon.teamvolei.Model;

import android.content.Context;

import com.example.osvaldoairon.teamvolei.CadJogador;
import com.example.osvaldoairon.teamvolei.ListJogadores;

import java.io.Serializable;

/**
 * Created by osvaldoairon on 22/12/2017.
 */
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Jogador implements Serializable {

    private transient Context context = null;

    private String nome;
    private int idade;
    private String altura;
    private String estrelas;
    private String funcao;
    private String id;

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.context = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura){
        this.altura=altura;
    }

    public int getIdade(){
        return idade;
    }
    public void setIdade(int idade){
        this.idade=idade;
    }

    public String getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(String estrelas){
        this.estrelas=estrelas;
    }

    public String getFuncao(){
        return funcao;
    }
    public void setFuncao(String funcao){
        this.funcao=funcao;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public Jogador(){}


}