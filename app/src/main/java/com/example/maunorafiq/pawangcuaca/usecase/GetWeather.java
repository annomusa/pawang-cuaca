package com.example.maunorafiq.pawangcuaca.usecase;

import android.util.Log;

import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/2/16.
 */

public class GetWeather {
    private static final String TAG = "Get Weather Class";

    private RestApi restApi;

    private String city;
    private int number;
    private double lat;
    private double lon;

    public GetWeather(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setRequest(int number, String city, double lat, double lon) {
        this.number = number;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    public void printRequest() {
        Log.d(TAG, "printRequest: " + number + " " + city + " " + lat + " " + lon);
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

        public OWeatherResponse getOWeatherResponse() {
            return oWeatherResponse;
        }

        public int getNumber() {
            return number;
        }

        public String getCity() {
            return city;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }
    }
}
