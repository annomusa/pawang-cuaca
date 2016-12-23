package com.icehousecorp.maunorafiq.data.forecast.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by maunorafiq on 11/29/16.
 */
@Singleton
public class ForecastDataStoreFactory {

    private Retrofit retrofit;

    @Inject
    public ForecastDataStoreFactory(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ForecastDataStore create() {
        ForecastDataStore forecastDataStore;

        forecastDataStore = new CloudForecastDataStore(retrofit);

        return forecastDataStore;
    }
}
