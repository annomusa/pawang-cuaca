package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 12/1/16.
 */
@Singleton
public class CityDataStoreFactory {

    @Inject
    public CityDataStoreFactory() {
    }

    public CityDataStore create() {
        CityDataStore cityDataStore;

        cityDataStore = new DiskCityDataStore();

        return cityDataStore;
    }
}
