package com.umng.sensormov;

public class DatoGps {
    public double latitud;
    public double longitud;
    public double altitud;

    DatoGps(){
        latitud=0;
        longitud=0;
        altitud=0;
    }

    void actualizar(double la, double lo, double al){
        latitud=la;
        longitud=lo;
        altitud=al;
    }
}
