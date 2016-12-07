package com.icehousecorp.maunorafiq.data.city.disk;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.List;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface RealmService {

    List<CityEntity> get();

    boolean put(String cityName);

    void update(String cityName, int ordinal);
}
