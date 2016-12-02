package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import com.icehousecorp.maunorafiq.data.city.disk.RealmService;
import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.functions.Action1;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class DiskCityDataStore implements CityDataStore {

    private final RealmService realmService;

    public DiskCityDataStore(RealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public List<String> getCityEntities() {
        List<String> result = new ArrayList<>();
        realmService.get().subscribe(cityEntities -> {
            for (CityEntity cityEntity : cityEntities) {
                result.add(cityEntity.getCityName());
            }
        });

        return result;
    }
}
