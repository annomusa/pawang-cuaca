package com.icehousecorp.maunorafiq.data.current.repository;

import com.icehousecorp.maunorafiq.data.current.entity.mapper.CurrentWeatherEntityDataMapper;
import com.icehousecorp.maunorafiq.data.current.repository.datasource.CurrentWeatherDataStore;
import com.icehousecorp.maunorafiq.data.current.repository.datasource.CurrentWeatherDataStoreFactory;
import com.icehousecorp.maunorafiq.domain.current.CurrentWeather;
import com.icehousecorp.maunorafiq.domain.current.repository.CurrentWeatherRepository;
import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class CurrentWeatherDataRepository implements CurrentWeatherRepository {

    private final CurrentWeatherDataStoreFactory currentWeatherDataStoreFactory;
    private final CurrentWeatherEntityDataMapper currentWeatherEntityDataMapper;

    @Inject
    public CurrentWeatherDataRepository(CurrentWeatherDataStoreFactory currentWeatherDataStoreFactory,
                                        CurrentWeatherEntityDataMapper currentWeatherEntityDataMapper) {
        this.currentWeatherDataStoreFactory = currentWeatherDataStoreFactory;
        this.currentWeatherEntityDataMapper = currentWeatherEntityDataMapper;
    }

    @Override
    public Observable<CurrentWeather> currentWeather(String city) {
        final CurrentWeatherDataStore currentWeatherDataStore = this.currentWeatherDataStoreFactory.create();
        return currentWeatherDataStore.currentWeatherEntity(city).map(this.currentWeatherEntityDataMapper::transform);
    }
}
