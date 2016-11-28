package com.icehousecorp.maunorafiq.data.current.net;

import com.icehousecorp.maunorafiq.data.current.entity.response.CurrentWeatherResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by maunorafiq on 11/16/16.
 */

public interface CurrentWeatherApi {
    public static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String openWeatherApi = "90faa1669716319e787ca1ab5da48cbc";

    @GET("weather")
    Observable<CurrentWeatherResponse> getWeather(@QueryMap Map<String, String> parameter);
}
