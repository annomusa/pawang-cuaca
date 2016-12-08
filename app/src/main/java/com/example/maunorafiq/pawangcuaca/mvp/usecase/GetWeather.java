package com.example.maunorafiq.pawangcuaca.mvp.usecase;

import android.util.Log;

import com.example.maunorafiq.pawangcuaca.mvp.Constant;
import com.example.maunorafiq.pawangcuaca.model.openweather.current.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.mvp.service.RestApi;

import rx.Observable;

/**
 * Created by maunorafiq on 11/2/16.
 */

public class GetWeather {
    private static final String TAG = "Get Weather Class";

    private RestApi restApi;

    private String city;
    private int ordinal;
    private String id;
    private double lat;
    private double lon;

    public GetWeather(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setRequest(int ordinal, String id, String city, double lat, double lon) {
        this.id = id;
        this.ordinal = ordinal;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    public void printRequest() {
        Log.d(TAG, "printRequest: " + id + " " + city + " " + lat + " " + lon);
    }

    public Observable<CityWeather> execute() {
        if (city != null)
            return restApi.getWeatherByCity(city, "metric", Constant.oWeatherApi)
                .map(response -> new CityWeather(ordinal, id, city, lat, lon, response));
        else
            return restApi.getWeatherByCoordinates(lat, lon, "metric",Constant.oWeatherApi)
                .map(response -> new CityWeather(ordinal, id, city, lat, lon, response));
    }

    public static class CityWeather {
        String id;
        int ordinal;
        String city;
        double lat;
        double lon;
        OWeatherResponse oWeatherResponse;

        CityWeather(int ordinal, String id, String city, double lat, double lon, OWeatherResponse oWeatherResponse) {
            this.id = id;
            this.ordinal = ordinal;
            this.city = city;
            this.lat = lat;
            this.lon = lon;
            this.oWeatherResponse = oWeatherResponse;
        }

        public OWeatherResponse getOWeatherResponse() {
            return oWeatherResponse;
        }

        public String getId() { return id; }

        public String getCity() { return city; }

        public double getLat() { return lat; }

        public double getLon() { return lon; }

        public int getOrdinal() { return ordinal; }
    }
}
