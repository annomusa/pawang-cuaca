package com.example.maunorafiq.pawangcuaca.location;

import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;

import rx.Observable;

/**
 * Created by maunorafiq on 10/28/16.
 */

public interface ListLocationInterface {
    void onCompleted();
    void onError(String message);
    void onResponse(OWeatherResponse response);
    Observable<OWeatherResponse> getWeather();
}
