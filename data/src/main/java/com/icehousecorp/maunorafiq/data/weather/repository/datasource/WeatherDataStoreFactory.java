package com.icehousecorp.maunorafiq.data.weather.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class WeatherDataStoreFactory {

    @Inject
    public WeatherDataStoreFactory() {
    }

    public WeatherDataStore create () {
        WeatherDataStore weatherDataStore;

        weatherDataStore = new CloudWeatherDataStore();

        return weatherDataStore;
    }
}
