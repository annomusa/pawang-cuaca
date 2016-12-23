package com.icehousecorp.maunorafiq.data.weather.repository.datasource;

import android.util.Log;

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

    private final String TAG = this.getClass().getSimpleName();

    private WeatherApi restApi;

    public CloudWeatherDataStore(Retrofit retrofit) {
        this.restApi = retrofit.create(WeatherApi.class);
    }

    @Override
    public Observable<WeatherResponse> getWeatherEntity(String city) {
        Map<String, String> request = new HashMap<>();
        request.put("q", city);
        request.put("units", "metric");
        request.put("appid", openWeatherApi);
        return this.restApi.getWeather(request);
    }

    @Override
    public Observable<WeatherResponse> getWeatherEntity(double lat, double lon) {
        Map<String, String> request = new HashMap<>();
        request.put("lat", String.valueOf(lat));
        request.put("lon", String.valueOf(lon));
        request.put("units", "metric");
        request.put("appid", openWeatherApi);
        return this.restApi.getWeather(request);
    }
}
