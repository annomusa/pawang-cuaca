package com.example.maunorafiq.pawangcuaca.usecase;

import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/2/16.
 */
@CustomScope
public class GetWeather {
    private RestApi restApi;
    private String city;
    private int number;
    private double lat;
    private double lon;

    @Inject
    public GetWeather(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setCity(String city, int number) {
        this.number = number;
        this.city = city;
    }

    public void setCoordinates(double lat, double lon, int number) {
        this.lat = lat;
        this.lon = lon;
        this.number = number;
    }

    public Observable<CityWeather> execute() {
        if (city != null)
            return restApi.getWeatherByCity(city, Constant.oWeatherApi)
                .map(response -> new CityWeather(number, city, lat, lon, response));
        else
            return restApi.getWeatherByCoordinates(lat, lon, Constant.oWeatherApi)
                .map(response -> new CityWeather(number, city, lat, lon, response));

    }

    public static class CityWeather {
        int number;
        String city;
        double lat;
        double lon;
        OWeatherResponse oWeatherResponse;

        public CityWeather(int number, String city, double lat, double lon, OWeatherResponse oWeatherResponse) {
            this.number = number;
            this.city = city;
            this.lat = lat;
            this.lon = lon;
            this.oWeatherResponse = oWeatherResponse;
        }
    }
}
