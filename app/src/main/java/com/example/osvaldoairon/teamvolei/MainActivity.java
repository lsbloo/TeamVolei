package com.example.osvaldoairon.teamvolei;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import com.example.osvaldoairon.teamvolei.Model.Jogador;
import com.example.osvaldoairon.teamvolei.CadJogador;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private Button cad;
    private Button list_jog;
    private ArrayList<String> lista_array;

    DatabaseReference dat;

    CadJogador cad1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if(id == R.id.menu_cad){

            Intent at = new Intent(MainActivity.this
                    ,CadJogador.class);

            startActivity(at);

        }
        if(id == R.id.menu_list){

            Intent at = new Intent(MainActivity.this,ListJogadores.class);
            at.putStringArrayListExtra("array", lista_array);
            startActivity(at);

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cad = (Button)findViewById(R.id.cad);
        //list_jog = (Button)findViewById(R.id.list_btn);

        lista_array = new ArrayList<String>();

        Intent at = getIntent();

        lista_array = (ArrayList<String>) at.getSerializableExtra("array");


       // if(lista_array != null){
          //  Toast.makeText(this, "" +lista_array.size(), Toast.LENGTH_SHORT).show();
        // }






    }


}
