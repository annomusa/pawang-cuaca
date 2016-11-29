package com.icehousecorp.maunorafiq.data.current.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class CurrentWeatherDataStoreFactory {

    @Inject
    public CurrentWeatherDataStoreFactory() {
    }

    public CurrentWeatherDataStore create () {
        CurrentWeatherDataStore currentWeatherDataStore;

        currentWeatherDataStore = new CloudCurrentWeatherDataStore();

        return currentWeatherDataStore;
    }
}
