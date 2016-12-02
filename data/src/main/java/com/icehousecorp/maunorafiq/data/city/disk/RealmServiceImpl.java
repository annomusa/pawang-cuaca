package com.icehousecorp.maunorafiq.data.city.disk;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;
import com.icehousecorp.maunorafiq.data.weather.repository.WeatherDataRepository;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by maunorafiq on 12/1/16.
 */

@Singleton
public class RealmServiceImpl implements RealmService {

    private Realm realm;
    private final WeatherDataRepository weatherDataRepository;

    @Inject
    public RealmServiceImpl(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public Observable<List<CityEntity>> get() {
        if (this.realm == null) {
            this.realm = Realm.getDefaultInstance();
        }

        if (this.realm.isClosed()) {
            this.realm = Realm.getDefaultInstance();
        }

        RealmResults<CityEntity> realmResults = realm.where(CityEntity.class).findAll().sort("ordinal", Sort.ASCENDING);

        List<CityEntity> list = new ArrayList<>();
        list.addAll(realmResults);
        realm.close();

        return Observable.just(list);
    }

    @Override
    public Observable<Weather> put(String cityName) {
        if (this.realm == null) {
            this.realm = Realm.getDefaultInstance();
        }

        if (this.realm.isClosed()) {
            this.realm = Realm.getDefaultInstance();
        }

        int size = realm.where(CityEntity.class).equalTo("cityName", cityName).findAll().size();
        if (size == 0) {
            realm.executeTransaction(realm1 -> {
                CityEntity cityEntity = realm1.createObject(CityEntity.class, cityName);
                cityEntity.setOrdinal(size);
            });
            this.realm.close();

            return this.weatherDataRepository.currentWeather(cityName);
        }
        this.realm.close();
        return null;
    }

    @Override
    public void update(String cityName, int ordinal) {

    }
}
