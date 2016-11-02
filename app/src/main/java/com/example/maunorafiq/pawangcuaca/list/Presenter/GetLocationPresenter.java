package com.example.maunorafiq.pawangcuaca.list.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.list.GetLocationContract;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by maunorafiq on 11/2/16.
 */

public class GetLocationPresenter implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>,
        LocationListener,
        GetLocationContract.UserActionListener {

    private static final String TAG = "Get Location Interactor";
    private static final long UPDATE_INTERVAL_MILISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_MILISECONDS = UPDATE_INTERVAL_MILISECONDS /4;

    public static final int REQUEST_CHECK_SETTINGS = 102;
    public static final int PERMISSION_ACCESS_COURSE_LOCATION = 103;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mGpsRequestDialog;
    private Location mCurrentLocation;

    private Context mContext;
    private GetLocationContract.View mInterface;

    public GetLocationPresenter(Context mContext) {
        this.mContext = mContext;
        this.mInterface = (GetLocationContract.View) mContext;

        buildGoogleApiClient();
        createSpesificationLocationRequest();
        buildGpsSettingsRequest();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void createSpesificationLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_MILISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MILISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    private void buildGpsSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mGpsRequestDialog = builder.build();
    }

    public void requestAccessLocation () {
        if ( ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "requestAccessLocation: not granted");
            ActivityCompat.requestPermissions(
                    (Activity) mContext,
                    new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COURSE_LOCATION
            );
        } else {
            Log.d(TAG, "requestAccessLocation: granted");
            requestActivateGps();
        }
    }

    public void requestActivateGps() {
        Log.d(TAG, "requestActivateGps: ");
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mGpsRequestDialog
                );
        result.setResultCallback(this);
    }

    public void startLocationUpdate() {
        int isGranted = ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        if( isGranted == PackageManager.PERMISSION_GRANTED ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient,
                    mLocationRequest,
                    this
            ).setResultCallback(status -> {
                Log.d(TAG, "startLocationUpdate: true");
            });
        }
    }

    private void stopLocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient,
                this
        ).setResultCallback(status -> Log.d(TAG, "stopLocationUpdate: true"));
    }



    //-------------- GApi -------------//
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        int isGranted = ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        if( isGranted == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "onConnected: true");
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mCurrentLocation != null) {
                Log.d(TAG, "onConnected: true++");
                mInterface.showLocation(mCurrentLocation);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: ");
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                startLocationUpdate();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.d(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
                try {
                    status.startResolutionForResult((Activity) mContext, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    Log.d(TAG, "PendingIntent unable to execute request.");
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.d(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        Log.d(TAG, "onLocationChanged: " + mCurrentLocation.getLatitude() + " " + mCurrentLocation.getLongitude());
        mInterface.showLocation(mCurrentLocation);
    }
    //-------------- GApi -------------//



    //-------------- Contract -------------//
    @Override
    public void onStartGApi() {
        mGoogleApiClient.connect();
        requestAccessLocation();
    }

    @Override
    public void onResumeGApi() {
        if(mGoogleApiClient.isConnected()) startLocationUpdate();
    }

    @Override
    public void onPauseGApi() {
        if(mGoogleApiClient.isConnected()) stopLocationUpdate();
    }

    @Override
    public void onStopGApi() {
        mGoogleApiClient.disconnect();
    }
    //-------------- Contract -------------//
}
