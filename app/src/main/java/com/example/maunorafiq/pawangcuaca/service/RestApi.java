package com.example.maunorafiq.pawangcuaca.service;

import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by maunorafiq on 10/28/16.
 */

public interface RestApi {
    @GET
    Observable<OWeatherResponse> getWeatherByUrl(@Url String url);

}
