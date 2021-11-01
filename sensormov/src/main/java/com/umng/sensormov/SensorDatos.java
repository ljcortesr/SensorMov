package com.umng.sensormov;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class SensorDatos extends Activity implements SensorEventListener {
    public SensorManager msm;
    public Sensor tipoSensor;
    public int sensor;
    public DatoSensor datosSensor= new DatoSensor();
    private Context contexto;
    public SensorDatos(Context c){
        contexto=c;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            msm = (SensorManager)getSystemService(SENSOR_SERVICE);
            switch (sensor){
                case 0:
                    tipoSensor=msm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    Log.w("SENSOR","acelerometro");
                    break;
                case 1:
                    tipoSensor=msm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    Log.w("SENSOR","giroscopio");
                    break;
                case 2:
                    tipoSensor=msm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                    Log.w("SENSOR","brujula");
                    break;
                case 3:
                    tipoSensor=msm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
                    Log.w("SENSOR","rotacion vector");
                    break;
            }
            msm.registerListener(this, tipoSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception e) {
            Log.w("SensorDatos", "OnCreate Warning: " + e.getMessage());
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Do something with this sensor value.
        datosSensor.actualizar(event.values[0],event.values[1],event.values[2]);
        //Log.e("Datos","X: "+datosSensor.x+" Y: "+datosSensor.y+" Z: "+datosSensor.z);
    }
    @Override
    protected void onResume() {
        super.onResume();
        msm.registerListener(this, tipoSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        msm.unregisterListener(this);
    }
}


