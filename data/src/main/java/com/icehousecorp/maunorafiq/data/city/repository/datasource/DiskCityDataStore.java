package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import android.util.Log;

import com.icehousecorp.maunorafiq.data.city.disk.RealmService;
import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.List;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class DiskCityDataStore implements CityDataStore {

    private final String TAG  = this.getClass().getSimpleName();

    private final RealmService realmService;

    public DiskCityDataStore(RealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public List<CityEntity> getListCityEntity() {
        return this.realmService.get();
    }

    @Override
    public boolean putCityEntity(String city) {
        return this.realmService.put(city);
    }
}
