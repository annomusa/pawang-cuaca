package com.icehousecorp.maunorafiq.data.forecast.repository;

import com.icehousecorp.maunorafiq.data.forecast.entity.mapper.ForecastEntityDataMapper;
import com.icehousecorp.maunorafiq.data.forecast.repository.datasource.ForecastDataStore;
import com.icehousecorp.maunorafiq.data.forecast.repository.datasource.ForecastDataStoreFactory;
import com.icehousecorp.maunorafiq.domain.forecast.Forecast;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by maunorafiq on 11/29/16.
 */
@Singleton
public class ForecastDataRepository implements ForecastRepository{

    private final ForecastDataStoreFactory forecastDataStoreFactory;
    private final ForecastEntityDataMapper forecastEntityDataMapper;

    @Inject
    public ForecastDataRepository(ForecastDataStoreFactory forecastDataStoreFactory,
                                  ForecastEntityDataMapper forecastEntityDataMapper) {
        this.forecastDataStoreFactory = forecastDataStoreFactory;
        this.forecastEntityDataMapper = forecastEntityDataMapper;
    }

    @Override
    public Observable<Forecast> forecastWeather(String city) {
        final ForecastDataStore forecastDataStore = this.forecastDataStoreFactory.create();

        return forecastDataStore
                .getForecastEntity(city)
                .map(forecastResponse -> forecastEntityDataMapper.transform(forecastResponse, city));
    }
}
