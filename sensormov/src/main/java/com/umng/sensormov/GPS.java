package com.umng.sensormov;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class GPS extends Activity implements LocationListener {
    public  DatoGps dgps = new DatoGps();
    public LocationManager lm;
    private Context contexto;
    public GPS(Context c){
        contexto = c;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
            Log.w("GPS", "onCreate: WARNINGS "+e.getMessage());
        }finally{
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
            Location location = lm.getLastKnownLocation(lm.NETWORK_PROVIDER);
            onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        dgps.actualizar(location.getLatitude(),location.getLongitude(),location.getAltitude());
        Log.w("GPS", "onLocationChanged: "+dgps.latitud);
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
}
