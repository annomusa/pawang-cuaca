package com.example.maunorafiq.pawangcuaca.presentation.citylist;

import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.base.LoadDataView;

import java.util.List;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface CityListView extends LoadDataView {

    void renderCityList(List<CityModel> cityModelList);

    void viewCity(CityModel cityModel);

    void updateCity(WeatherModel weatherModel);

    void addNewCity(CityModel cityModel);
}
