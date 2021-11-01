package com.umng.sensormov;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ObjetosMagneticos extends Activity implements SensorEventListener {
    public SensorManager msm;
    public Sensor sensor;
    public DatoSensor datosSensor= new DatoSensor();
    public IdentificadorMagnetico im= new IdentificadorMagnetico();
    public ArrayList<DatoSensor> listaDatoSensor = new ArrayList<DatoSensor>();
    private boolean aprendiendo=false;
    private long tiempoaux;
    private boolean calibraraux=false;
    private boolean identificaraux=false;
    private float aux1=0.0f;
    private float aux2=0.0f;
    private float aux3=0.0f;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=msm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        msm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        tiempoaux=System.currentTimeMillis();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        datosSensor.actualizar(event.values[0], event.values[1], event.values[2]);
        if (calibraraux==true) {
            aux1 = datosSensor.x;
            aux2 = datosSensor.y;
            aux3 = datosSensor.z;
            Log.w("OBJM"," x "+ aux1+ " y "+ aux2+ " z "+ aux3);
            calibraraux=false;
        }
        if (aprendiendo==true) {
            if (listaDatoSensor.size() < 51) {//51
                if (System.currentTimeMillis() - tiempoaux >= 100 ) {//100
                    listaDatoSensor.add(new DatoSensor(event.values[0]-aux1, event.values[1]-aux2, event.values[2]-aux3));
                    tiempoaux = System.currentTimeMillis();
                    Log.w("OBJM", "TOMANDO DATOS "+(listaDatoSensor.size()-1)+": "+listaDatoSensor.get(listaDatoSensor.size()-1).x+" "+listaDatoSensor.get(listaDatoSensor.size()-1).y+" "+listaDatoSensor.get(listaDatoSensor.size()-1).z);
                }
            }
            else {
                Log.w("OBJM", "DATOS TOMADOS");
                calcular();
                Log.w("OBJM", " EN CALCULADO");
                aprendiendo = false;
            }
        }
        if (identificaraux==true) {
            if (listaDatoSensor.size() < 30) {
                if (System.currentTimeMillis() - tiempoaux >= 50) {
                    listaDatoSensor.add(new DatoSensor(event.values[0] - aux1, event.values[1] - aux2, event.values[2] - aux3));
                    tiempoaux = System.currentTimeMillis();
                }
            } else {
                calcular();
                Log.w("OBJM", " ID CALCULADO");
                identificaraux = false;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void enseñar(){
        aprendiendo=true;
        listaDatoSensor =  new ArrayList<DatoSensor>();
        Log.w("OBJM","enseñar");
    }
    public void identificar(){
        identificaraux=true;
        listaDatoSensor =  new ArrayList<DatoSensor>();
        Log.w("OBJM","identificar");
    }
    public void calibrar(){
        calibraraux=true;
        Log.w("OBJM","calibrar");
    }
    private  void calcular(){
        //Log.w("OBJM", "CALIBRANDO FUNCION");
        float auxx=0;
        float auxy=0;
        float auxz=0;
        float promediox=0;
        float promedioy=0;
        float promedioz=0;
        for (int i = 0; i < listaDatoSensor.size(); i++){
            auxx=auxx+listaDatoSensor.get(i).x;
            auxy=auxy+listaDatoSensor.get(i).y;
            auxz=auxz+listaDatoSensor.get(i).z;
          //  Log.w("OBJM", "CALCULANDO PROMEDIO "+(i)+": "+listaDatoSensor.get(i).x+" "+listaDatoSensor.get(i).y+" "+listaDatoSensor.get(i).z);
        }
        promediox=auxx/listaDatoSensor.size();
        promedioy=auxy/listaDatoSensor.size();
        promedioz=auxz/listaDatoSensor.size();

        auxx=0;
        auxy=0;
        auxz=0;
        for ( int i = 0 ; i < listaDatoSensor.size(); i++){
            auxx=auxx+(listaDatoSensor.get(i).x-promediox)*(listaDatoSensor.get(i).x-promediox);
            auxy=auxy+(listaDatoSensor.get(i).y-promedioy)*(listaDatoSensor.get(i).y-promedioy);
            auxz=auxz+(listaDatoSensor.get(i).z-promedioz)*(listaDatoSensor.get(i).z-promedioz);
        }
        float varianzax=auxx/(listaDatoSensor.size()-1);
        float varianzay=auxy/(listaDatoSensor.size()-1);
        float varianzaz=auxz/(listaDatoSensor.size()-1);

        float desviaciónEstanderx= (float)Math.pow(varianzax,1/2);
        float desviaciónEstandery= (float)Math.pow(varianzay,1/2);
        float desviaciónEstanderz= (float)Math.pow(varianzaz,1/2);

        float desviacionpromediox= (desviaciónEstanderx*100/Math.abs(promediox));
        float desviacionpromedioy= (desviaciónEstandery*100/Math.abs(promedioy));
        float desviacionpromedioz= (desviaciónEstanderz*100/Math.abs(promedioz));

        im.actualizar(promediox,promedioy,promedioz,desviacionpromediox,desviacionpromedioy,desviacionpromedioz);
        //Log.w("OBJM","x "+promediox+" y "+promedioy+" z "+promedioz +" vx "+varianzax+ " vy "+varianzay + "vz "+varianzaz);
      //  Log.w("OBJM","calculado");
      //  Log.w("OBJM","x "+im.x+" y "+im.y+" z "+im.z +" vx "+im.vx+ " vy "+im.vy + "vz "+im.vz);
        Log.w("OBJM","x "+im.x+" y "+im.y+" z "+im.z +" dx "+desviacionpromediox+ " dy "+desviacionpromedioy + "dz "+desviacionpromedioz);
    }
    public boolean obtenerAprendiendo(){
        return aprendiendo;
    }

   // public IdentificadorMagnetico enseñar2(){
      /*  tomadordatos
        calcular
        return im;*/
}





