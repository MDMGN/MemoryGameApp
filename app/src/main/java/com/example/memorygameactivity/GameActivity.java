package com.example.memorygameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    private static final int NUM_CASILLAS=16;

    private ImageButton btn00;
    private ImageButton btn01;
    private ImageButton btn02;
    private ImageButton btn03;
    private ImageButton btn04;
    private ImageButton btn05;
    private ImageButton btn06;
    private ImageButton btn07;
    private ImageButton btn08;
    private ImageButton btn09;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn13;
    private ImageButton btn14;
    private ImageButton btn15;
    TextView textPuntuacion;

    private ImageButton[] tablero;
    private int imagenes[];
    private int imagenFondo;
    private int puntuacion;
    private int aciertos;
    private ArrayList<Integer> arrayBarajado;
    private ImageButton imagenVista;
    private int seleccionUno;
    private int seleccionDos;
    private boolean tableroBloqueado;
    private final Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn00 = findViewById(R.id.btnimage00);
        btn01 = findViewById(R.id.btnimage01);
        btn02 = findViewById(R.id.btnimage02);
        btn03 = findViewById(R.id.btnimage03);
        btn04 = findViewById(R.id.btnimage04);
        btn05 = findViewById(R.id.btnimage05);
        btn06 = findViewById(R.id.btnimage06);
        btn07 = findViewById(R.id.btnimage07);
        btn08 = findViewById(R.id.btnimage08);
        btn09 = findViewById(R.id.btnimage09);
        btn10 = findViewById(R.id.btnimage10);
        btn11 = findViewById(R.id.btnimage11);
        btn12 = findViewById(R.id.btnimage12);
        btn13 = findViewById(R.id.btnimage13);
        btn14 = findViewById(R.id.btnimage14);
        btn15 = findViewById(R.id.btnimage15);
        textPuntuacion = findViewById(R.id.text_puntuacion);
        tablero = new ImageButton[]{
                btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, btn10, btn11, btn12, btn13, btn14, btn15,
        };
        imagenFondo = R.drawable.fondo;
        imagenes = new int[]{
                R.drawable.la0, R.drawable.la1, R.drawable.la2, R.drawable.la3, R.drawable.la4, R.drawable.la5, R.drawable.la6, R.drawable.la7
        };
        startGame();
    }
    public void startGame(){
        puntuacion=0;
        aciertos=0;
        textPuntuacion.setText(getString(R.string.textView_point)+" "+ Integer.toString(puntuacion));
        tableroBloqueado=false;
        imagenVista=null;
        seleccionUno=-1;
        seleccionDos=-1;

        arrayBarajado = barajarImagenes();
        //Muestra las imagenes del tablero.
        for(int i=0; i< NUM_CASILLAS;i++){
            tablero[i].setImageResource(imagenes[arrayBarajado.get(i)]);
        }
    //Oculta las imagenes del tablero.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i< NUM_CASILLAS;i++){
                    tablero[i].setImageResource(imagenFondo);
                }
            }
        }, 1000);

        //Aniadir Listeners a los ImageView.
        for(int i=0;i<NUM_CASILLAS;i++){
            tablero[i].setEnabled(true); //Habilitar el boton.
            final int j=i;
            tablero[i].setOnClickListener(new View.OnClickListener() {  //Escucha la llama al hacer click.
                @Override
                public void onClick(View v) {
                    if(tableroBloqueado== false){
                        comprobar(j,tablero[j]);
                    }
                }
            });
        }

    }
    public void comprobar(int position, final ImageButton imagenbtn){
        //Ninguna imagen seleccionada.
        if(imagenVista==null){
            imagenVista=imagenbtn;
            imagenVista.setImageResource(imagenes[arrayBarajado.get(position)]);
            imagenVista.setEnabled(false);
            seleccionUno=arrayBarajado.get(position);
        }else{
            tableroBloqueado=true;
            imagenbtn.setImageResource(imagenes[arrayBarajado.get(position)]);
            imagenbtn.setEnabled(false);
            seleccionDos=arrayBarajado.get(position);
            //Comprueba si es la misma imagen.
            if(seleccionUno==seleccionDos){
                imagenVista=null;
                tableroBloqueado=false;
                aciertos++;
                puntuacion++;
                textPuntuacion.setText(getString(R.string.textView_point)+" "+ Integer.toString(puntuacion));
                //Comprueba si todas las imagenes están destapadas.
                if(aciertos==imagenes.length){
                    Toast.makeText(this,"Has ganado.",Toast.LENGTH_SHORT).show();
                }
            }else{
                //No son la misma imagen.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagenVista.setImageResource(imagenFondo);
                        imagenVista.setEnabled(true);
                        imagenbtn.setImageResource(imagenFondo);
                        imagenbtn.setEnabled(true);
                        tableroBloqueado=false;
                        imagenVista=null;
                        if(puntuacion>0){
                            puntuacion--;
                        }
                        textPuntuacion.setText(getString(R.string.textView_point)+" "+ Integer.toString(puntuacion));
                    }
                },1000);
            }

        }
    }
    private ArrayList<Integer> barajarImagenes(){
        ArrayList<Integer> listaBarajada = new ArrayList<Integer>();
        for(int i=0; i<NUM_CASILLAS ; i++){
            listaBarajada.add(i%(NUM_CASILLAS/2));
        }
        Log.d("LISTA BARAJADA", Arrays.toString(listaBarajada.toArray()));
        Collections.shuffle(listaBarajada);
        Log.d("LISTA BARAJADA", Arrays.toString(listaBarajada.toArray()));
        return listaBarajada;
    }

    public void onClickRestart(View view) {
        startGame();
    }

    public void onClickExit(View view) {
        finish();
    }
}