package com.icehousecorp.maunorafiq.domain.gpslocation.interactor;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.gpslocation.repository.GpsLocationRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 12/20/16.
 */

public class GetGpsLocation extends UseCase {

    private final GpsLocationRepository gpsLocationRepository;

    private Context context;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    @Inject
    public GetGpsLocation(GpsLocationRepository gpsLocationRepository) {
        this.gpsLocationRepository = gpsLocationRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return gpsLocationRepository.gpsLocation(context, googleApiClient, locationRequest);
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public void setLocationRequest(LocationRequest locationRequest) {
        this.locationRequest = locationRequest;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
