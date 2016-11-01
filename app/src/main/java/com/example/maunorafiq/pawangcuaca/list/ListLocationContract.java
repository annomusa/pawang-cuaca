package com.example.maunorafiq.pawangcuaca.list;

import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;

import rx.Observable;

/**
 * Created by maunorafiq on 10/31/16.
 */

public interface ListLocationContract {

    interface View {
        void showComplete();
        void showError(String message);
        void showResult(OWeatherResponse result);
    }

    interface UserActionListener {
        void fetchWeatherByCity(String city);
        void fetchWeatherByCoordinates(double lat, double lon);
        void setView(View view);
    }

}
