package com.example.maunorafiq.pawangcuaca.mvp.usecase;

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

import com.example.maunorafiq.pawangcuaca.mvp.Constant;
import com.example.maunorafiq.pawangcuaca.mvp.usecase.contract.GetLocationContract;
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

public class GetLocation implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>,
        LocationListener,
        GetLocationContract.RequestListener {

    private static final String TAG = "Get Location Interactor";

    private static final long UPDATE_INTERVAL_MILISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_MILISECONDS = UPDATE_INTERVAL_MILISECONDS /4;



    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mGpsRequestDialog;
    private Location mCurrentLocation;

    private Context mContext;
    private GetLocationContract.Presenter mPresenter;

    public GetLocation(Context mContext) {
        this.mContext = mContext;

        buildGoogleApiClient();
        createSpecificationLocationRequest();
        buildGpsSettingsRequest();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void createSpecificationLocationRequest() {
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

    private void stopLocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient,
                this
        ).setResultCallback(status -> {
//            Log.d(TAG, "stopLocationUpdate: true");
        });
    }



    //-------------- GApi -------------//
    // @see #onStartGApi()
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestPermission();
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
                try {
                    status.startResolutionForResult((Activity) mContext, Constant.REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    // TODO : Handle if intent (Pending Result) got an exception
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // TODO : Handle if user choose Never in Settings GPS
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
//        Log.d(TAG, "onLocationChanged: " + mCurrentLocation.getLatitude() + " " + mCurrentLocation.getLongitude());
        mPresenter.retrieveCurrentLocation(location);
    }
    //-------------- GApi -------------//


    //-------------- Contract -------------//
    @Override
    public void setPresenter(GetLocationContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onStartGApi() {
        mGoogleApiClient.connect();
    }

    @Override
    public void stopGetCurrentLocation() {
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdate();
        }
    }

    @Override
    public void onStopGApi() {
        mGoogleApiClient.disconnect();
    }

    public void requestPermission() {
        if ( ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions (
                    (Activity) mContext,
                    new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    Constant.PERMISSION_ACCESS_COURSE_LOCATION );
        } else {
            requestGps();
        }
    }

    @Override
    public void requestGps() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mGpsRequestDialog
                );
        result.setResultCallback(this);
    }

    @Override
    public void startLocationUpdate() {
        boolean isGranted = ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if ( isGranted ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient,
                    mLocationRequest,
                    this
            ).setResultCallback(status -> {
//                Log.d(TAG, "startLocationUpdate: true");
            });
        }
    }
    //-------------- Contract -------------//
}
