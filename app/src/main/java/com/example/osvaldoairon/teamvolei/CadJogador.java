package com.example.osvaldoairon.teamvolei;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import android.graphics.Matrix;


import com.example.osvaldoairon.teamvolei.Control.JogadorRepositorioID;
import com.example.osvaldoairon.teamvolei.Model.JogadorAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.osvaldoairon.teamvolei.Model.Jogador;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;



public class CadJogador extends AppCompatActivity implements Serializable{

    private static EditText nomeJogador;
    private static EditText idadeJogador;
    private static EditText alturaJogador;

    private ArrayList<Jogador> list_jogador;
    private ArrayList<String> list_jogador_string;


    private CheckBox funcaoJogador_ponteiro;
    private CheckBox funcaoJogador_meio;
    private CheckBox funcaoJogador_libero;
    private CheckBox funcaoJogador_levantador;
    private CheckBox funcaoJogador_oposto;

    private CheckBox jogadorUmaEstrela;
    private CheckBox jogadorDuasEstrela;
    private CheckBox jogadorTresEstrela;
    private CheckBox jogadorQuatroEstrela;
    private CheckBox jogadorCincoEstrela;

    private static String jogadorEstrelaTipo;
    private String funcaoJogadorTipo;


    private Button btn_foto;
    private Button btn_salvar;
    private ArrayList<String> id_s;


    public static final int IMAGEM_INTERNA = 12;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public  static ListView list;


    int cont =0;
    int cont_star = 0;
    static int aux;


    public FirebaseDatabase fireBaseDatabase;
    public DatabaseReference databaseReference;


    private ChildEventListener childEventListener;

    private JogadorRepositorioID id_jogador;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_jogador);

        verificarPermissao(this);


        list_jogador = new ArrayList<Jogador>();
        list_jogador_string = new ArrayList<String>();

        id_jogador = new JogadorRepositorioID(this);

        iniciarFirebase();
        eventoData();


        btn_foto = (Button)findViewById(R.id.pegar_ft);
        btn_salvar = (Button)findViewById(R.id.btn_salvar);

        nomeJogador = (EditText)findViewById(R.id.edt_nome);
        idadeJogador=(EditText)findViewById(R.id.edt_idade);
        alturaJogador = (EditText)findViewById(R.id.edt_altura);

        funcaoJogador_ponteiro=(CheckBox)findViewById(R.id.id_ponteiro);
        funcaoJogador_meio=(CheckBox)findViewById(R.id.id_meio);
        funcaoJogador_libero=(CheckBox)findViewById(R.id.id_libero);
        funcaoJogador_levantador=(CheckBox)findViewById(R.id.id_levantador);
        funcaoJogador_oposto=(CheckBox)findViewById(R.id.id_oposto);


        jogadorUmaEstrela = (CheckBox)findViewById(R.id.id_1);
        jogadorDuasEstrela = (CheckBox)findViewById(R.id.id_2);
        jogadorTresEstrela = (CheckBox)findViewById(R.id.id_3);
        jogadorQuatroEstrela = (CheckBox)findViewById(R.id.id_4);
        jogadorCincoEstrela = (CheckBox)findViewById(R.id.id_5);

        funcaoJogador_ponteiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcaoJogador_ponteiro.isChecked()){
                    funcaoJogadorTipo="Ponteiro";
                    cont += 1;
                    verificaTroll();
                    }
                }


        });
        funcaoJogador_meio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcaoJogador_meio.isChecked()){
                    funcaoJogadorTipo="Meio de rede";
                    cont += 1;
                    verificaTroll();
                }
            }


        });
        funcaoJogador_libero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcaoJogador_libero.isChecked()){
                    funcaoJogadorTipo="Libero";
                    cont += 1;
                    verificaTroll();
                }
            }


        });
        funcaoJogador_levantador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcaoJogador_levantador.isChecked()){
                    funcaoJogadorTipo="Levantador";
                    cont += 1;
                    verificaTroll();
                }
            }


        });
        funcaoJogador_oposto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcaoJogador_ponteiro.isChecked()){
                    funcaoJogadorTipo="Oposto";
                    cont += 1;
                    verificaTroll();
                }
            }


        });
        jogadorUmaEstrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jogadorUmaEstrela.isChecked()){
                    jogadorEstrelaTipo = "Uma Estrela";
                    cont_star += 1;
                    verificaTrollStar();
                }

            }
        });

        jogadorDuasEstrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jogadorDuasEstrela.isChecked()){
                    jogadorEstrelaTipo = "Duas Estrelas";
                    cont_star += 1;
                    verificaTrollStar();
                }

            }
        });

        jogadorTresEstrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jogadorTresEstrela.isChecked()){
                    jogadorEstrelaTipo = "Tres Estrelas";
                    cont_star += 1;
                    verificaTrollStar();
                }

            }
        });

        jogadorQuatroEstrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jogadorQuatroEstrela.isChecked()){
                    jogadorEstrelaTipo = "Quatro Estrelas";
                    cont_star += 1;
                    verificaTrollStar();
                }

            }
        });

        jogadorCincoEstrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jogadorCincoEstrela.isChecked()){
                    jogadorEstrelaTipo = "Cinco Estrelas";
                    cont_star += 1;
                    verificaTrollStar();
                }

            }
        });
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pegarImg(v);

            }
        });


        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Salvar dados na firebase
                 */


                Random random = new Random();

                long idJogador = random.nextInt(200);


                int idadeJogadorInt = Integer.parseInt(idadeJogador.getText().toString());
                Jogador jogador = new Jogador();


                jogador.setNome(nomeJogador.getText().toString());
                jogador.setIdade(idadeJogadorInt);
                jogador.setEstrelas(jogadorEstrelaTipo);
                jogador.setFuncao(funcaoJogadorTipo);
                jogador.setAltura(alturaJogador.getText().toString());
                jogador.setId(UUID.randomUUID().toString());

          //    id_jogador.Inserir(jogador);




                databaseReference.child("Jogadores").child(jogador.getId()).setValue(jogador);

                Toast.makeText(CadJogador.this, "Salvo", Toast.LENGTH_SHORT).show();



                Intent at = new Intent(CadJogador.this,MainActivity.class);

                //at.putStringArrayListExtra("array", list_jogador);
                at.putExtra("array",list_jogador);
                startActivity(at);

            }
        });


    }


    public void eventoData(){
        databaseReference.child("Jogadores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_jogador.clear();



                for(DataSnapshot obj:dataSnapshot.getChildren()){

                    Jogador jogador = (Jogador) obj.getValue(Jogador.class);
                    list_jogador.add(jogador);
                }

                setList_jogador(list_jogador);

                //Toast.makeText(CadJogador.this, "TAMNNHO ? "+list_jogador.size(), Toast.LENGTH_SHORT).show();


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public ArrayList pegaList(){
        return list_jogador;
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        fireBaseDatabase = fireBaseDatabase.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = fireBaseDatabase.getReference();


    }

    public void pegarImg(View view){

        Intent at = new Intent(Intent.ACTION_GET_CONTENT);
        at.setType("image/");
        startActivityForResult(at,IMAGEM_INTERNA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        if(requestCode == IMAGEM_INTERNA){
            if(resultCode == RESULT_OK){
                Uri imagemselect = intent.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemselect,colunas,null,null,null);
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String imgpath = cursor.getString(indexColuna);

                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);

                ImageView view = (ImageView)findViewById(R.id.foto_id);



                view.setImageBitmap(resizeImage(this, bitmap, 50, 50));


            }
        }
    }
    private static Bitmap resizeImage(Context context, Bitmap bmpOriginal,
                                      float newWidth, float newWeight) {
        Bitmap novoBmp = null;

        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();

        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;

        float novoH = newWeight * densityFactor;

        //Calcula escala em percentagem do tamanho original para o novo tamanho
        float scalaW = novoW / w;
        float scalaH = novoH / h;

        // Criando uma matrix para manipulação da imagem BitMap
        Matrix matrix = new Matrix();

        // Definindo a proporção da escala para o matrix
        matrix.postScale(scalaW, scalaH);

        //criando o novo BitMap com o novo tamanho
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);

        return novoBmp;
    }

    private static String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE ,Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void verificarPermissao(Activity activity){

        int permissao = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissao != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.menu_remove){
            //Toast.makeText(this, "LAST", Toast.LENGTH_SHORT).show();

            new AlertDialog.Builder(this).setTitle("Deletar Jogadores").
                    setMessage("Tem certeza que deseja deletar todos os jogadores? ").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(CadJogador.this, "Removidos !", Toast.LENGTH_SHORT).show();
                    databaseReference.child("Jogadores").removeValue();
                }
            }).setNegativeButton("Nao", null).show();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_del,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void verificaTroll(){
        if(cont == 2){
            Toast.makeText(this, "Selecione somente 1 Box", Toast.LENGTH_SHORT).show();
            cont = 0;
        }
    }

    public void verificaTrollStar(){

        if(cont_star ==2){
            Toast.makeText(this, "Selecione somente 1 Box Estrela", Toast.LENGTH_SHORT).show();
            cont_star = 0;
        }
    }


    public void setList_jogador(ArrayList<Jogador> jogador){
        this.list_jogador = jogador;
    }
    public ArrayList<Jogador> getList_jogador(){
        return list_jogador;
    }


}
