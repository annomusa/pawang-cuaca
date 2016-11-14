package com.example.maunorafiq.pawangcuaca.model.reamldb;

import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by maunorafiq on 11/14/16.
 */

public class RealmService {
    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm(){
        mRealm.close();
    }

    public RealmResults<RealmCity> getAllcitities() {
        return mRealm.where(RealmCity.class).findAll().sort("ordinal", Sort.ASCENDING);
    }

    public String getCityNameByOrdinal (int position) {
        return mRealm.where(RealmCity.class).equalTo("ordinal", position).findFirst().getName();
    }

    public void deleteCity (int position) {
        if (position == 0) return;
        final RealmResults<RealmCity> cities = mRealm.where(RealmCity.class).findAll().sort("ordinal", Sort.ASCENDING);
        RealmCity city = cities.where().equalTo("ordinal", position).findFirst();

        mRealm.executeTransaction(realm -> {
            if (city != null)
                city.deleteFromRealm();
        });

        for (int i=position+1; i<cities.size(); i++) {
            int movedOrdinal = i;
            mRealm.executeTransaction(realm -> {
                RealmCity movedItem = new RealmCity();
                movedItem = cities.get(movedOrdinal);
                movedItem.setOrdinal(movedOrdinal-1);
                realm.copyToRealmOrUpdate( movedItem );
            });
        }
    }

    public void addCity (String id, String cityName) {
        boolean isNotExist = mRealm.where(RealmCity.class).equalTo("id", id).findAll().size() == 0;
        RealmResults<RealmCity> realmCities = mRealm.where(RealmCity.class).findAll();
        if ( isNotExist ) {
            mRealm.executeTransaction(realm -> {
                RealmCity realmCity = realm.createObject(RealmCity.class, id);
                realmCity.setName(cityName);
                realmCity.setOrdinal(realmCities.size());
//                findWeather(realmCities.size(), id, cityName, 0, 0);
            });
        }
    }

    public void updateObject (GetWeather.CityWeather response) {
        mRealm.executeTransaction(realm -> {
            RealmCity realmResponse = new RealmCity();
            realmResponse.setId ( response.getId() );
            realmResponse.setOrdinal ( response.getOrdinal() );
            realmResponse.setName ( response.getCity()!= null ? response.getCity() : response.getOWeatherResponse().getName() );
            realmResponse.setTemperature ( Integer.toString(response.getOWeatherResponse().getMain().getTemp().intValue()) );
            realmResponse.setImageUrl ( response.getOWeatherResponse().getWeather().get(0).getIcon() );
            realm.copyToRealmOrUpdate ( realmResponse );
        });
    }
}
