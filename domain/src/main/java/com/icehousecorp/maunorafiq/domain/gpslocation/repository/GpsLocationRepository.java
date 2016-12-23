package com.icehousecorp.maunorafiq.domain.gpslocation.repository;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import rx.Observable;

/**
 * Created by maunorafiq on 12/20/16.
 */

public interface GpsLocationRepository {
    Observable<Location> gpsLocation(Context context,
                                     GoogleApiClient googleApiClient,
                                     LocationRequest locationRequest);
}
