package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.icehousecorp.maunorafiq.domain.city.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 12/5/16.
 */
@PerActivity
public class CityModelDataMapper {

    @Inject
    public CityModelDataMapper() {
    }

    public CityModel transform(City city) {
        CityModel cityModel = new CityModel(city.getCityName());
        cityModel.setOrdinal(city.getOrdinal());

        return cityModel;
    }

    public List<CityModel> transform(List<City> cities) {
        List<CityModel> result;
        if (cities.isEmpty()) {
            result = Collections.emptyList();
        } else {
            result = new ArrayList<>();
            for (City city : cities) {
                result.add(transform(city));
            }
        }

        return result;
    }
}
