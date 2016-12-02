package com.icehousecorp.maunorafiq.data.city.repository;

import com.icehousecorp.maunorafiq.data.city.disk.RealmService;
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStore;
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStoreFactory;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;
import com.icehousecorp.maunorafiq.domain.weathers.repository.CityRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */
@Singleton
public class CityDataRepository  implements CityRepository{

    private final RealmService realmService;

    @Inject
    public CityDataRepository(RealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public Observable<Weather> putCity(String city) {
        return realmService.put(city);
    }

}
