package com.umng.tangibles;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.umng.sensormov.DatoSensor;
import com.umng.sensormov.DatosCrudos;
import com.umng.sensormov.DatosPantalla;
import com.umng.sensormov.GPS;
import com.umng.sensormov.Gestos;
import com.umng.sensormov.IdentificadorMagnetico;
import com.umng.sensormov.ObjetosMagneticos;
import com.umng.sensormov.SensorDatos;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.view.View.*;

public class MainActivity extends ObjetosMagneticos implements SensorEventListener {
    ImageView jim;
    ImageView tom;
    ImageView hercules;
    Button enseñarBoton;
    Button calibrarBoton;
    Button identificarBoton;
    Button agregarBoton;
    ArrayList<Float> listaNormas= new ArrayList<Float>();
    ArrayList<IdentificadorMagnetico> listaObjetos = new ArrayList<IdentificadorMagnetico>();
    float error = 0.2f;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sensorDatos.sensor=2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jim = findViewById(R.id.jimid);
        tom = findViewById(R.id.tomid);
        hercules = findViewById(R.id.herculesid);
        enseñarBoton= (Button)findViewById(R.id.enseñarid);
        calibrarBoton= (Button) findViewById(R.id.calibrarid);
        identificarBoton=(Button)findViewById(R.id.indentificarid);
        agregarBoton=(Button)findViewById(R.id.agredarid);

        enseñarBoton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentificadorMagnetico objetoMagnetico= new IdentificadorMagnetico();
                Log.w("MAIN Enseñar","entro");
                enseñar();
              //  while (obtenerAprendiendo()== true){}
                objetoMagnetico.actualizar(im.x,im.y,im.z,im.vx,im.vy,im.vy);
                listaObjetos.add(objetoMagnetico);
                Log.w("MAIN Enseñar","x "+listaObjetos.get(listaObjetos.size()-1).x+" y "+
                        listaObjetos.get(listaObjetos.size()-1).y+" z "+listaObjetos.get(listaObjetos.size()-1).z);


            }
        });
        calibrarBoton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calibrar();
            }
        });
        identificarBoton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularNormas();
                identificar();
                IdentificadorMagnetico objetoMagnetico= new IdentificadorMagnetico(im.x,im.y,im.z,im.vx,im.vy,im.vy);
                Log.w("MAIN IDENTIFICAR","x "+objetoMagnetico.x +" y "+objetoMagnetico.y+" z "+objetoMagnetico.z);
                evaluar(objetoMagnetico);
                listaNormas.clear();
                //Log.w("MAIN","Identificar");
            }
        });
    }
   @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
    }
    public void evaluar(IdentificadorMagnetico objetoIdentificar){
        float norma= (float)Math.pow((objetoIdentificar.x*objetoIdentificar.z)+(objetoIdentificar.z*objetoIdentificar.z)+(objetoIdentificar.z*objetoIdentificar.z),1/2);
        int objeto = -1;
        for ( int i = 0 ; i < listaNormas.size(); i++) {
             float limmax =(listaNormas.get(i)*error)+listaNormas.get(i);
             float limmin =listaNormas.get(i)-(listaNormas.get(i)*error);
                if(norma >= limmin && norma <= limmax){
                   objeto=i;
                }
        }
        Log.w("MAIN","OBJETO"+ objeto);
        if (objeto==0){
            jim.setVisibility(VISIBLE);
            tom.setVisibility(INVISIBLE);
            hercules.setVisibility(INVISIBLE);
        } else {
            if (objeto==1){
                jim.setVisibility(INVISIBLE);
                tom.setVisibility(VISIBLE);
                hercules.setVisibility(INVISIBLE);
            } else {
                if (objeto==2) {
                    jim.setVisibility(INVISIBLE);
                    tom.setVisibility(INVISIBLE);
                    hercules.setVisibility(VISIBLE);
                }
            }
        }
    }
    public void calcularNormas(){
        for ( int i = 0 ; i < listaObjetos.size(); i++) {
            float sumas =(listaObjetos.get(i).x*listaObjetos.get(i).x)+(listaObjetos.get(i).y*listaObjetos.get(i).y)
                    +(listaObjetos.get(i).z*listaObjetos.get(i).z);
            listaNormas.add((float)Math.pow(sumas,1/2));
        }
    }
}

