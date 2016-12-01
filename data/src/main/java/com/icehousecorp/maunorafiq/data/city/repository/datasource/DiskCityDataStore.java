package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class DiskCityDataStore implements CityDataStore {

    private final Realm realm;

    public DiskCityDataStore() {
        this.realm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        realm.close();
    }

    @Override
    public List<String> getCityEntities() {
        List<String> list = new ArrayList<>();
        RealmResults<CityEntity> realmResults = realm.where(CityEntity.class).findAll().sort("ordinal", Sort.ASCENDING);

        for (CityEntity cityEntity : realmResults) {
            list.add(cityEntity.getCityName());
        }

        return list;
    }
}
