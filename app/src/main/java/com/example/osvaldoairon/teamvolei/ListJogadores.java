package com.example.osvaldoairon.teamvolei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.osvaldoairon.teamvolei.Model.Jogador;
import com.example.osvaldoairon.teamvolei.Model.JogadorAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ListJogadores extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter adapter;
    private ArrayList<Jogador> list_jogador;
    private static JogadorAdapter n1;


    private CadJogador cad1;
    private Jogador play;

    DatabaseReference dat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jogadores);

        list_jogador = new ArrayList<Jogador>();


        Intent at = getIntent();

       list_jogador = (ArrayList<Jogador>) at.getSerializableExtra("array");


        if(list_jogador != null) {
            final JogadorAdapter n1 = new JogadorAdapter(ListJogadores.this, list_jogador);
            ListView list = new ListView(ListJogadores.this);
            setContentView(list);
            list.setAdapter(n1);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Jogador jogador = (Jogador) parent.getItemAtPosition(position);
                    list_jogador.remove(jogador);

                    n1.notifyDataSetChanged();
                }
            });
        }
        else{

               Toast.makeText(this, "Cadastre pelo menos 1"  , Toast.LENGTH_SHORT).show();
             }


    }

}
