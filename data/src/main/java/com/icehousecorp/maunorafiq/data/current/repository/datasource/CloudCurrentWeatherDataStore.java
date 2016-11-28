package com.icehousecorp.maunorafiq.data.current.repository.datasource;

import com.icehousecorp.maunorafiq.data.current.net.CurrentWeatherApi;
import com.icehousecorp.maunorafiq.data.current.entity.response.CurrentWeatherResponse;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */

public class CloudCurrentWeatherDataStore implements CurrentWeatherDataStore {

    private CurrentWeatherApi restApi;

    public CloudCurrentWeatherDataStore(CurrentWeatherApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<CurrentWeatherResponse> currentWeatherEntity(String city) {
        Map<String, String> data = new HashMap<>();
        data.put("q", city);
        data.put("units", "metric");
        data.put("appid", CurrentWeatherApi.openWeatherApi);
        return restApi.getWeather(data);
    }
}
