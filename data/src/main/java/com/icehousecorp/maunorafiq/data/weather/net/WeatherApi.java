package com.icehousecorp.maunorafiq.data.weather.net;

import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by maunorafiq on 11/16/16.
 */

public interface WeatherApi {
    @GET("weather")
    Observable<WeatherResponse> getWeather(@QueryMap Map<String, String> parameter);
}
