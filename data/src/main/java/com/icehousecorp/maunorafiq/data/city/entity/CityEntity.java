package com.icehousecorp.maunorafiq.data.city.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class CityEntity extends RealmObject {

    @PrimaryKey
    private String cityName;
    private int ordinal;

    public CityEntity() {
    }

    public CityEntity(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
