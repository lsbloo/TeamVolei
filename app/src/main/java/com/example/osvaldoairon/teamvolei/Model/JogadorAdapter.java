package com.example.osvaldoairon.teamvolei.Model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osvaldoairon.teamvolei.R;

import java.util.ArrayList;

/**
 * Created by osvaldoairon on 27/12/2017.
 */

public class JogadorAdapter extends BaseAdapter {

    Context ctx;

    ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

    public JogadorAdapter(Context ctx , ArrayList<Jogador> jogadores) {
        this.ctx=ctx;
        this.jogadores=jogadores;
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Jogador jogador = jogadores.get(position);

        View linha = LayoutInflater.from(ctx).inflate(R.layout.activity_list_jogadores,parent,false);

        ImageView imgJogador = (ImageView)linha.findViewById(R.id.imgJogador);

        TextView nomeJogador = (TextView)linha.findViewById(R.id.nomeJogador);
        TextView idadeJogador = (TextView)linha.findViewById(R.id.idadeJogador);
        TextView funcaoJogador = (TextView)linha.findViewById(R.id.estrelaJogador);
        TextView estrelaJogador = (TextView)linha.findViewById(R.id.funcaoJogador);
        TextView alturaJogador = (TextView)linha.findViewById(R.id.alturaJogador);


        Resources res = ctx.getResources();
        // Fotos do jogadores; implementar no String.value
        //TypedArray fotosJogador = res.obtainTypedArray(R.array.fotos);

        nomeJogador.setText(jogador.getNome());
        idadeJogador.setText("Idade: " + String.valueOf(jogador.getIdade()));
        funcaoJogador.setText(jogador.getFuncao());
        estrelaJogador.setText(jogador.getEstrelas());
        alturaJogador.setText("Altura: " +jogador.getAltura());


        return linha;
    }
}
