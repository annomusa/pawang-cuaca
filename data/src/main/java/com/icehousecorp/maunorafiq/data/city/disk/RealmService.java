package com.icehousecorp.maunorafiq.data.city.disk;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface RealmService {

    List<CityEntity> get();

    boolean put(String cityName);

    void update(String cityName, int ordinal);
}
