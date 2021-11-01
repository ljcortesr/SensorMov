package com.umng.sensormov;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


import java.util.ArrayList;

public class DatosPantalla extends Activity {
    final public int TOTAL_PUNTEROS=10;
    public PuntoTactil[] puntosTactiles=new PuntoTactil[TOTAL_PUNTEROS];
    public int touchCount;

    public DatosPantalla(){
        super();
        touchCount=0;
        for(int t=0;t<TOTAL_PUNTEROS;t++)
            puntosTactiles[t]=new PuntoTactil();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
           Log.w("DatosPantalla", "OnCreate Warning: "+e.getMessage());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        try{
            super.onTouchEvent(event);
        }
        catch (Exception e){
           Log.w("DatosPantalla", "OnTouchEvent Warning: "+e.getMessage());

        }
        //super.onTouchEvent(event);
        Log.e("Multitacto", "Accion "+ event.getActionMasked()+": "+event.getActionIndex());
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_UP://Último punto en soltar la pantalla
            case MotionEvent.ACTION_CANCEL://Gesto cancelado
                touchCount=0;
                for(int t=0;t<TOTAL_PUNTEROS;t++)
                    puntosTactiles[t].id=-1;
                break;
            case MotionEvent.ACTION_DOWN://Primer punto en tocar la pantalla
                touchCount=1;
                puntosTactiles[0]=new PuntoTactil(event.getX(),event.getY(),event.getPointerId(event.getActionIndex()), event.getSize());
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //Next Down
                touchCount=event.getPointerCount();
                for(int t=0;t<TOTAL_PUNTEROS;t++)
                    if(puntosTactiles[t].id==-1) {
                        puntosTactiles[t] = new PuntoTactil(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()), event.getPointerId(event.getActionIndex()),event.getSize(event.getActionIndex()));
                        return true;
                    }
                break;
            case MotionEvent.ACTION_POINTER_UP: //Siguientes puntos en soltar la pantalla
                for(int t=0;t<TOTAL_PUNTEROS;t++)
                    if (puntosTactiles[t].id == event.getPointerId(event.getActionIndex())) {
                        puntosTactiles[t].id = -1;
                        touchCount--;
                        return true;
                    }

                break;
            case MotionEvent.ACTION_MOVE: //Movimiento del punto principal que provoca la actualización de todos los puntos
                for(int t=0;t<TOTAL_PUNTEROS;t++)
                    if(puntosTactiles[t].id!=-1) {
                        int index = event.findPointerIndex(puntosTactiles[t].id);
                        puntosTactiles[t].actualizar(event.getX(index), event.getY(index), event.getSize(index));
                    }
                break;
        }
        return true;
    }

}