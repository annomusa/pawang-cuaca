package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import android.support.annotation.NonNull;

import com.icehousecorp.maunorafiq.data.city.disk.RealmService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 12/1/16.
 */
@Singleton
public class CityDataStoreFactory {

    private final RealmService realmService;

    @Inject
    public CityDataStoreFactory(RealmService realmService) {
        this.realmService = realmService;
    }

    public CityDataStore create() {
        CityDataStore cityDataStore;

        cityDataStore = new DiskCityDataStore(this.realmService);

        return cityDataStore;
    }
}
