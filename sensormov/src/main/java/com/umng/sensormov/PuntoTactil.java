package com.umng.sensormov;

public class PuntoTactil {
    public float x;
    public float y;
    public int id;
    public float tam;
    PuntoTactil(){
        id=-1;
    }
    PuntoTactil(float xI, float yI, float t){
        x=xI;
        y=yI;
        id=-1;
        tam=t;
    }
    PuntoTactil(float xI, float yI, int i, float t){
        x=xI;
        y=yI;
        id=i;
        tam=t;
    }

    void actualizar (float xM, float yM, float tM){
        x=xM;
        y=yM;
        tam=tM;
    }
}
