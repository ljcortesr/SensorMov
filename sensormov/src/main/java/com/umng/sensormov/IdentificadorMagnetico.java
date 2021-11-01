package com.umng.sensormov;

public class IdentificadorMagnetico {
    public float x;
    public float y;
    public float z;
    public float vx;
    public float vy;
    public float vz;

    public IdentificadorMagnetico(){
        x=0;
        y=0;
        z=0;
        vx=0;
        vy=0;
        vz=0;
    }
    public IdentificadorMagnetico(float X, float Y, float Z, float VX, float VY , float VZ){
        x=X;
        y=Y;
        z=Z;
        vx=VX;
        vy=VY;
        vz=VZ;
    }

    public void actualizar(float X, float Y, float Z, float VX, float VY , float VZ){
        x=X;
        y=Y;
        z=Z;
        vx=VX;
        vy=VY;
        vz=VZ;
    }


}
