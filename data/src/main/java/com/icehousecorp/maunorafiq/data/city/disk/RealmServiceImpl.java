package com.icehousecorp.maunorafiq.data.city.disk;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by maunorafiq on 12/1/16.
 */

@Singleton
public class RealmServiceImpl implements RealmService {

    private final String TAG  = this.getClass().getSimpleName();

    private Realm realm;

    @Inject
    public RealmServiceImpl() {
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public List<CityEntity> get() {
        RealmResults<CityEntity> realmResults = realm.where(CityEntity.class).findAll().sort("ordinal", Sort.ASCENDING);

        List<CityEntity> cityEntityList = new ArrayList<>();
        cityEntityList.addAll(realmResults);

        return cityEntityList;
    }

    @Override
    public boolean put(String cityName) {
        boolean isEmpty = realm.where(CityEntity.class).equalTo("cityName", cityName).findAll().size() == 0;
        int position = realm.where(CityEntity.class).findAll().size();
        if (isEmpty) {
            realm.executeTransaction(realm1 -> {
                CityEntity cityEntity = realm1.createObject(CityEntity.class, cityName);
                cityEntity.setOrdinal(position);
            });
            return true;
        }
        return false;
    }

    @Override
    public void update(String cityName, int ordinal) {

    }
}
