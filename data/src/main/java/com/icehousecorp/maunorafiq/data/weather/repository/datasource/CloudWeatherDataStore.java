package com.icehousecorp.maunorafiq.data.weather.repository.datasource;

import com.google.gson.GsonBuilder;
import com.icehousecorp.maunorafiq.data.weather.net.WeatherApi;
import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.icehousecorp.maunorafiq.data.Constant.API_BASE_URL;
import static com.icehousecorp.maunorafiq.data.Constant.openWeatherApi;

/**
 * Created by Raffi on 11/25/2016.
 */

public class CloudWeatherDataStore implements WeatherDataStore {

    private WeatherApi restApi;

    public CloudWeatherDataStore() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.restApi = retrofit.create(WeatherApi.class);
    }

    @Override
    public Observable<WeatherResponse> getWeatherEntity(String city) {
        Map<String, String> data = new HashMap<>();
        data.put("q", city);
        data.put("units", "metric");
        data.put("appid", openWeatherApi);
        return this.restApi.getWeather(data);
    }
}
