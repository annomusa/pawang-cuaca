package com.example.maunorafiq.pawangcuaca.list;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.model.reamldb.City;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;

import java.util.List;

/**
 * Created by maunorafiq on 10/31/16.
 */

public interface ListLocationContract {

    interface View {

        void showComplete();

        void showError(String message);

        void showResult(GetWeather.CityWeather result);

    }

    interface UserActionListener {

        void setView(View view);

        void setContext(Context context);

        void requestGps();

        void requestLocation();

        void getCurrentLocation();

        List<City> fetchCities();

        void fetchWeather(int number, String city, double lat, double lon);

        void addNewCity(String city);

        void addNewRealmCity(String id, String city);
    }

}
