package com.icehousecorp.maunorafiq.data.current.repository.datasource;

import com.google.gson.GsonBuilder;
import com.icehousecorp.maunorafiq.data.current.net.CurrentWeatherApi;
import com.icehousecorp.maunorafiq.data.current.entity.response.CurrentWeatherResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */

public class CloudCurrentWeatherDataStore implements CurrentWeatherDataStore {

    private CurrentWeatherApi restApi;

    public CloudCurrentWeatherDataStore() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CurrentWeatherApi.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.restApi = retrofit.create(CurrentWeatherApi.class);
    }

    @Override
    public Observable<CurrentWeatherResponse> currentWeatherEntity(String city) {
        Map<String, String> data = new HashMap<>();
        data.put("q", city);
        data.put("units", "metric");
        data.put("appid", CurrentWeatherApi.openWeatherApi);
        return this.restApi.getWeather(data);
    }
}
