package com.umng.sensormov;

import android.Manifest;
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

import androidx.annotation.RequiresApi;

public class DatosCrudos extends Activity implements SensorEventListener, LocationListener {
    public DatosPantalla datosPantalla=new DatosPantalla();
    public SensorDatos sensorDatos = new SensorDatos(this);
    public GPS gps = new GPS(this);
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrarSensor();
        registrarGPS();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void registrarGPS(){
        gps.lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = gps.lm.getLastKnownLocation(gps.lm.NETWORK_PROVIDER);
        onLocationChanged(location);
    }
    private void registrarSensor(){
        sensorDatos.msm = (SensorManager)getSystemService(SENSOR_SERVICE); //(SensorManager)getSystemService(SENSOR_SERVICE);
        switch (sensorDatos.sensor){
            case 0:
                sensorDatos.tipoSensor=sensorDatos.msm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                Log.w("SENSOR","acelerometro");
                break;
            case 1:
                sensorDatos.tipoSensor=sensorDatos.msm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                Log.w("SENSOR","giroscopio");
                break;
            case 2:
                sensorDatos.tipoSensor=sensorDatos.msm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                Log.w("SENSOR","brujula");
                break;
            case 3:
                sensorDatos.tipoSensor=sensorDatos.msm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
                Log.w("SENSOR","rotacion vector");
                break;
        }
        sensorDatos.msm.registerListener(this, sensorDatos.tipoSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.w("DatosCrudos", "OnSensorChange: ");
        sensorDatos.onSensorChanged(event);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    @Override
    public void onLocationChanged(Location location) {
        gps.onLocationChanged(location);
        Log.w("DatosCrudos", "onLocationChanged: "+gps.dgps.latitud);
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
    @Override
    public void onProviderEnabled(String s) {

    }
    @Override
    public void onProviderDisabled(String s) {

    }
   @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        datosPantalla.onTouchEvent(event);
        return true;
    }
}
