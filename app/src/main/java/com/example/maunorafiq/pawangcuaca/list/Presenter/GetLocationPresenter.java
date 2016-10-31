package com.example.maunorafiq.pawangcuaca.list.Presenter;

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

import com.example.maunorafiq.pawangcuaca.Manifest;
import com.example.maunorafiq.pawangcuaca.list.GetLocationContract;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by maunorafiq on 10/31/16.
 */

public class GetLocationPresenter implements GetLocationContract.UserActionListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private GetLocationContract.View mInterface;
    private Context ctx;
    public final static int REQUEST_CHECK_SETTINGS = 102;
    private final String TAG = "Get Location Presenter";

    public GetLocationPresenter(Context ctx, GetLocationContract.View mInterface) {
        this.mInterface = mInterface;
        this.ctx = ctx;
        mGoogleApiClient = new GoogleApiClient.Builder(ctx)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void connectGApi() {
        mGoogleApiClient.connect();
    }

    @Override
    public void disconnectGApi() {
        mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLoc();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void getLoc(){
        if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mInterface.showLastLocation(lastLocation);
        } else {
            mInterface.showAccessGpsNotGranted();
            fetchLocation();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void fetchLocation() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(locationSettingsResult -> {
            final Status status = locationSettingsResult.getStatus();
            final LocationSettingsStates states = locationSettingsResult.getLocationSettingsStates();
            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes.SUCCESS:
                    Log.d(TAG, "fetchLocation: success");
                    // All location settings are satisfied. The client can
                    // initialize location requests here.
//                    getLoc();
                    break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        Log.d(TAG, "fetchLocation: show dialog");
                        status.startResolutionForResult( (Activity) ctx, REQUEST_CHECK_SETTINGS);
                    }
                    catch (IntentSender.SendIntentException e) {
                        // Ignore the error.
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    // Location settings are not satisfied. However, we have no way
                    // to fix the settings so we won't show the dialog.
                    Log.d(TAG, "fetchLocation: unavailable");
                    break;
            }
        });
    }
}
