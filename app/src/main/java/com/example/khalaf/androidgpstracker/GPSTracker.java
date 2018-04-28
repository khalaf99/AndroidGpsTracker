package com.example.khalaf.androidgpstracker;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/**
 * Created by KHALAF on 11/14/2017.
 */

public class GPSTracker extends Service implements LocationListener {

    private final Context context;
    private boolean isGPSenable = false;
    private boolean isNETworkenable = false;
    private boolean canGetlocation = false;

    Location location;
    protected LocationManager LocationManager;

    public GPSTracker(Context context) {
        this.context = context;
    }

    // method of get location
    public Location Getlocation() {
        try {
            LocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSenable = LocationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
            isNETworkenable = LocationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);

            // check permissions to user access fine found el mawk33 el da2eeee2 wel coarse access da bygeeeb el el mawk3 el t2reeby
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isGPSenable) {
                    if (location == null) {
                        LocationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 10000, 10, this);
                        if (LocationManager != null) {
                            location = LocationManager.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                // if location cannot found in gps then we will getit from network
                if (location == null) {
                    if (isGPSenable) {

                        LocationManager.requestLocationUpdates(android.location.LocationManager.NETWORK_PROVIDER, 10000, 10, this);
                        if (LocationManager != null) {
                            location = LocationManager.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER);
                        }
                    }
                }
            }

        } catch (Exception ex) {

        }

        return location;
    }

    // these are the default methods if we implement locationlistener //
    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
