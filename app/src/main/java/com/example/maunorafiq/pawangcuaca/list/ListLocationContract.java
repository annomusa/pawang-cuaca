package com.example.maunorafiq.pawangcuaca.list;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.model.reamldb.RealmCity;
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

        void requestGps();

        void requestLocation();

        void getCurrentLocation();

        List<RealmCity> fetchCities();

        void addNewRealmCity(String id, String city);
    }

}
