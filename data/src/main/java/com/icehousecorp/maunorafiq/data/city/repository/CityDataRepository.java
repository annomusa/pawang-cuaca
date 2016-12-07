package com.icehousecorp.maunorafiq.data.city.repository;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;
import com.icehousecorp.maunorafiq.data.city.entity.mapper.CityEntityDataMapper;
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStore;
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStoreFactory;
import com.icehousecorp.maunorafiq.domain.city.City;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */
@Singleton
public class CityDataRepository  implements CityRepository{

    private final String TAG = this.getClass().getSimpleName();

    private final CityDataStoreFactory cityDataStoreFactory;
    private final CityEntityDataMapper cityEntityDataMapper;

    @Inject
    public CityDataRepository(CityDataStoreFactory cityDataStoreFactory,
                              CityEntityDataMapper cityEntityDataMapper) {
        this.cityDataStoreFactory = cityDataStoreFactory;
        this.cityEntityDataMapper = cityEntityDataMapper;
    }

    @Override
    public boolean putCity(String city) {
        final CityDataStore cityDataStore = this.cityDataStoreFactory.create();

        return cityDataStore.putCityEntity(city);
    }

    @Override
    public Observable<List<City>> getCity() {
        final CityDataStore cityDataStore = this.cityDataStoreFactory.create();

        List<CityEntity> listCityEntity = cityDataStore.getListCityEntity();

        List<City> result = new ArrayList<>();
        for (CityEntity cityEntity : listCityEntity) {
            result.add(cityEntityDataMapper.transform(cityEntity));
        }

        return Observable.just(result);
//        return Observable.from(listCityEntity)
//                .map(cityEntityDataMapper::transform)
//                .toList();
    }
}
