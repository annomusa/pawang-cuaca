package com.icehousecorp.maunorafiq.data.city.disk;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class RealmService {

    private final Realm realm;

    public RealmService (Realm realm) {
        this.realm = realm;
    }

    public void closeRealm() {
        realm.close();
    }

    public Observable<List<CityEntity>> getAllCities() {
        List<CityEntity> list = new ArrayList<>();
        RealmResults<CityEntity> realmResults = realm.where(CityEntity.class).findAll().sort("ordinal", Sort.ASCENDING);
        for (CityEntity cityEntity : realmResults) {
            list.add(cityEntity);
        }
        return Observable.just(list);
    }

    public void addCity (String cityName) {
        boolean isExist = realm.where(CityEntity.class).equalTo("cityName", cityName).findFirst() != null;
        int position = realm.where(CityEntity.class).findAll().size();

        if( !isExist ) {
            realm.executeTransaction(realm1 -> {
                CityEntity cityEntity = realm1.createObject(CityEntity.class, cityName);
                cityEntity.setOrdinal(position);
            });
        }
    }
}
