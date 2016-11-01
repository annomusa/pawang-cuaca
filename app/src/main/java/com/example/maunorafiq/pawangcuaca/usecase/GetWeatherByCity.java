package com.example.maunorafiq.pawangcuaca.usecase;

import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/1/16.
 */
@CustomScope
public class GetWeatherByCity {

    private RestApi restApi;

    private String city;

    @Inject
    public GetWeatherByCity(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Observable<CityWeather> execute() {
        return restApi.getWeatherByCity(city, Constant.oWeatherApi)
                .map(response -> new CityWeather(city, response));
    }

    public static class CityWeather {
        String city;
        OWeatherResponse oWeatherResponse;

        public CityWeather(String city, OWeatherResponse oWeatherResponse) {
            this.city = city;
            this.oWeatherResponse = oWeatherResponse;
        }
    }
}