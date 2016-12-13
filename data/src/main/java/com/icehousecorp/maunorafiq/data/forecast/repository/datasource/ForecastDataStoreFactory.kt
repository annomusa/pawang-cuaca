package com.icehousecorp.maunorafiq.data.forecast.repository.datasource

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by maunorafiq on 11/29/16.
 */
@Singleton
class ForecastDataStoreFactory
@Inject
constructor() {

    fun create(): ForecastDataStore {
        val forecastDataStore: ForecastDataStore

        forecastDataStore = CloudForecastDataStore()

        return forecastDataStore
    }
}
