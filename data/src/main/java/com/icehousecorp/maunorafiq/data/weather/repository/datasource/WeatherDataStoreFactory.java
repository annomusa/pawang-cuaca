package com.icehousecorp.maunorafiq.data.weather.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class WeatherDataStoreFactory {

    private Retrofit retrofit;

    @Inject
    public WeatherDataStoreFactory(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public WeatherDataStore create () {
        WeatherDataStore weatherDataStore;

        weatherDataStore = new CloudWeatherDataStore(retrofit);

        return weatherDataStore;
    }
}
