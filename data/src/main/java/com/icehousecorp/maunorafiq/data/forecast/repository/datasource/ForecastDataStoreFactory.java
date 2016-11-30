package com.icehousecorp.maunorafiq.data.forecast.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 11/29/16.
 */
@Singleton
public class ForecastDataStoreFactory {

    @Inject
    public ForecastDataStoreFactory() {
    }

    public ForecastDataStore create() {
        ForecastDataStore forecastDataStore;

        forecastDataStore = new CloudForecastDataStore();

        return forecastDataStore;
    }
}
