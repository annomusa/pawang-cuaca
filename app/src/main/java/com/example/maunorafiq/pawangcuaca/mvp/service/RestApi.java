package com.example.maunorafiq.pawangcuaca.mvp.service;

import com.example.maunorafiq.pawangcuaca.model.openweather.current.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.OWeatherResponse2;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by maunorafiq on 10/28/16.
 */

public interface RestApi {
    @GET
    Observable<OWeatherResponse> getWeatherByUrl(@Url String url);

    @GET("weather")
    Observable<OWeatherResponse> getWeatherByCity(@Query("q") String city, @Query("units") String metric, @Query("appid") String oWeatherApi);

    @GET("weather")
    Observable<OWeatherResponse> getWeatherByCoordinates(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String metric, @Query("appid") String oWeatherApi);

    @GET("forecast")
    Observable<OWeatherResponse2> getWeatherForecast(@Query("q") String city, @Query("units") String metric, @Query("appid") String oWheatherApi);

//    http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=90faa1669716319e787ca1ab5da48cbc
//    http://api.openweathermap.org/data/2.5/weather?q=kebayoran%20baru&appid=90faa1669716319e787ca1ab5da48cbc
//    http://api.openweathermap.org/data/2.5/forecast?q=London&appid=90faa1669716319e787ca1ab5da48cbc&units=metric
}
