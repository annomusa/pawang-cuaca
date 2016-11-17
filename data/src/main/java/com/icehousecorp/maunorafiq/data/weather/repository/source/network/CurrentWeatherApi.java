package com.icehousecorp.maunorafiq.data.weather.repository.source.network;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by maunorafiq on 11/16/16.
 */

public interface CurrentWeatherApi {
    String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Observable<Object> getWeather(@QueryMap Map<String, String> parameter);
}
