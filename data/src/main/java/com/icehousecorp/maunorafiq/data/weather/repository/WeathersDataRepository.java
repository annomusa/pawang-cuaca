package com.icehousecorp.maunorafiq.data.weather.repository;

import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStore;
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStoreFactory;
import com.icehousecorp.maunorafiq.data.weather.entity.mapper.WeatherEntityDataMapper;
import com.icehousecorp.maunorafiq.data.weather.repository.datasource.WeatherDataStore;
import com.icehousecorp.maunorafiq.data.weather.repository.datasource.WeatherDataStoreFactory;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeathersRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class WeathersDataRepository implements WeathersRepository {

    private final WeatherDataStoreFactory weatherDataStoreFactory;
    private final WeatherEntityDataMapper weatherEntityDataMapper;
    private final CityDataStoreFactory cityDataStoreFactory;

    @Inject
    public WeathersDataRepository(WeatherDataStoreFactory weatherDataStoreFactory,
                                  CityDataStoreFactory cityDataStoreFactory,
                                  WeatherEntityDataMapper weatherEntityDataMapper) {
        this.weatherDataStoreFactory = weatherDataStoreFactory;
        this.cityDataStoreFactory = cityDataStoreFactory;
        this.weatherEntityDataMapper = weatherEntityDataMapper;
    }

    @Override
    public Observable<List<Weather>> listWeather() {
        final WeatherDataStore weatherDataStore = this.weatherDataStoreFactory.create();

        final CityDataStore cityDataStore = this.cityDataStoreFactory.create();
        List<String> cities = cityDataStore.getCityEntities();

        List<Weather> list;

        if (cities != null && !cities.isEmpty()) {
            list = new ArrayList<>();
            for (String city : cities) {
                weatherDataStore
                        .getWeatherEntity(city)
                        .map(weatherResponse -> weatherEntityDataMapper
                                .transform(weatherResponse, city))
                        .subscribe(list::add);
            }
        } else {
            list = Collections.emptyList();
        }
        return Observable.just(list);
    }
}
