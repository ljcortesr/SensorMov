package com.umng.sensormov;

import android.util.Log;

public class DatoSensor {
    public float x;
    public float y;
    public float z;

    DatoSensor(){
        x=0;
        y=0;
        z=0;
    }
    DatoSensor(float X,float Y,float Z){
        x=X;
        y=Y;
        z=Z;
    }
    void actualizar(float X, float Y, float Z){
        x=X;
        y=Y;
        z=Z;

    }
}
