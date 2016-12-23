package com.icehousecorp.maunorafiq.data.gpslocation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.icehousecorp.maunorafiq.domain.gpslocation.repository.GpsLocationRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maunorafiq on 12/20/16.
 */
@Singleton
public class GpsLocationDataRepository implements GpsLocationRepository {

    @Inject
    public GpsLocationDataRepository() {
    }

    @Override
    public Observable<Location> gpsLocation(Context context,
                                            GoogleApiClient googleApiClient,
                                            LocationRequest locationRequest) {
        return Observable.create(subscriber ->  {
            int accessCoarseLocation = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION);
            boolean isGranted = accessCoarseLocation == PackageManager.PERMISSION_GRANTED;
            if (isGranted) {
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        googleApiClient,
                        locationRequest,
                        location -> {
                            if (location == null) {
                                if (!subscriber.isUnsubscribed())
                                    subscriber.onError(new Exception());
                            } else {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onNext(location);
                                    subscriber.onCompleted();
                                }
                            }
                        }
                );
            }
        });
    }
}
