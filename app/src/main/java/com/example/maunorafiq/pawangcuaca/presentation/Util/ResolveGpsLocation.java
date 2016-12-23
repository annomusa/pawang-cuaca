package com.example.maunorafiq.pawangcuaca.presentation.Util;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import static com.example.maunorafiq.pawangcuaca.presentation.citylist.CityListFragment.CHECK_SETTINGS_REQUEST_CODE;

/**
 * Created by maunorafiq on 12/20/16.
 */

public class ResolveGpsLocation {

    private final String TAG = this.getClass().getSimpleName();

    public interface GpsCallback {
        void callback(Location location);
    }

    private final Context context;
    private GpsCallback gpsCallback;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;

    private static final long UPDATE_INTERVAL_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_MILLISECONDS = UPDATE_INTERVAL_MILLISECONDS/4;

    public ResolveGpsLocation(Context context) {
        this.context = context;
        createGoogleApiClient();
        createLocationRequest();
        createGpsRequestDialog();
    }

    private void createGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new GoogleApiConnectedHandler())
                .addApi(LocationServices.API)
                .build();
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    private void createGpsRequestDialog() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();
    }

    public void fetchGpsLocation(GpsCallback gpsCallback) {
        if (this.gpsCallback == null) this.gpsCallback = gpsCallback;
        askGpsActivation();
    }

    private void askGpsActivation() {
        if (googleApiClient.isConnected()) {
            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(
                            googleApiClient,
                            locationSettingsRequest
                    );
            result.setResultCallback(new CheckSettingHandler());
        } else
            googleApiClient.connect();
    }

    private void startLocating() {
        boolean isGranted = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if ( isGranted ) {
            Log.d(TAG, "startLocating: ");
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient,
                    locationRequest,
                    location -> {
                        gpsCallback.callback(location);
                        Log.d(TAG, "startLocating: " + location.toString());
                    }
            );
        }
    }

    private final class GoogleApiConnectedHandler implements GoogleApiClient.ConnectionCallbacks {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            askGpsActivation();
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    }

    private final class CheckSettingHandler implements ResultCallback<LocationSettingsResult> {
        @Override
        public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
            final Status status = locationSettingsResult.getStatus();
            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes.SUCCESS:
                    startLocating();
                    break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    try {
                        status.startResolutionForResult(
                                (Activity) context,
                                CHECK_SETTINGS_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        Log.d(TAG, "onResult: exception on check setting");
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    Log.d(TAG, "onResult: user choose to never activate gps");
                    break;
            }
        }
    }
}
