package com.icehousecorp.maunorafiq.data.current.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.icehousecorp.maunorafiq.data.current.net.CurrentWeatherApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class CurrentWeatherDataStoreFactory {

    private final Context context;

    private final CurrentWeatherApi restApi;

    @Inject
    public CurrentWeatherDataStoreFactory(@NonNull Context context, CurrentWeatherApi restApi) {
        this.context = context;
        this.restApi = restApi;
    }

    public CurrentWeatherDataStore create (String city) {
        CurrentWeatherDataStore currentWeatherDataStore;

        currentWeatherDataStore = createCloudCurrentWeatherDataStore();

        return currentWeatherDataStore;
    }

    private CurrentWeatherDataStore createCloudCurrentWeatherDataStore() {
        return new CloudCurrentWeatherDataStore(restApi);
    }
}
