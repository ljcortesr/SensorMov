package com.umng.sensormov;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class Gestos extends Activity implements SensorEventListener {
        public SensorManager msm;
        public Sensor sensor;
        public float yawZ;
        public float rollY;
        public float pitchX;
        private boolean calibrar=false;
        private float aux1=0.0f;
        private float aux2=0.0f;
        private float aux3=0.0f;
    public DatoSensor datosSensor= new DatoSensor();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=msm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        msm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        datosSensor.actualizar(event.values[0],event.values[1],event.values[2]);
        if (calibrar==true) {
            aux1 = datosSensor.x;
            aux2 = datosSensor.y;
            aux3 = datosSensor.z;

            calibrar();
        }
            pitchX = datosSensor.x - aux1;
            rollY = datosSensor.y - aux2;
            yawZ = datosSensor.z - aux3;
            if (pitchX < -1) {
                pitchX = pitchX + 2.1f;
            }
            if (rollY < -1) {
                rollY = rollY + 2.1f;
            }
            if (yawZ < -1) {
                yawZ = yawZ + 2.1f;
            }
            pitchX=grados(pitchX);
            rollY=grados(rollY);
            yawZ=grados(yawZ);
    }
    private float grados(float f){
        float q = f*180;
        if(q<0){
            q=q+361;
        }
        return q;
    }
    public void calibrar() {
        calibrar = !calibrar;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        msm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        msm.unregisterListener(this);
    }
}
