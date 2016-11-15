package com.example.maunorafiq.pawangcuaca.detail;

import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.OWeatherResponse2;

/**
 * Created by maunorafiq on 11/10/16.
 */

public interface DetailContract {

    interface View {

        void showCompletion();

        void showError(String message);

        void showResult(OWeatherResponse2 results);
    }

    interface UserActionListener {

        void requestDetail(String city);
    }

}
