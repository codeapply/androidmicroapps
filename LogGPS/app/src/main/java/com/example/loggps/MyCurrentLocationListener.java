package com.example.loggps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyCurrentLocationListener implements LocationListener {
    double x,y;

    @Override
    public void onLocationChanged(Location location) {
        x = location.getLatitude();
        y = location.getLongitude();

        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();

        //I make a log to see the results
        Log.e("MY CURRENT LOCATION", myLocation);

    }

    public double getLocationX() {
        return x;
    }

    public double getLocationY() {
        return y;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    public void onProviderEnabled(String s) {

    }

    public void onProviderDisabled(String s) {

    }
}