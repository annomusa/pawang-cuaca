package com.example.maunorafiq.pawangcuaca.presentation.view;

import com.example.maunorafiq.pawangcuaca.presentation.model.ListWeatherModel;

import java.util.Collection;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface CurrentWeatherListView extends LoadDataView {

    void renderWeatherList(Collection<ListWeatherModel> listWeatherModels);

    void viewWeather(ListWeatherModel listWeatherModel);
}
