package com.icehousecorp.maunorafiq.domain.city;

/**
 * Created by maunorafiq on 12/5/16.
 */

public class City {
    private final String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    private int ordinal;

    public String getCityName() {
        return cityName;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** City ***\n");
        stringBuilder.append("City name : " + this.getCityName() + "\n");
        stringBuilder.append("City ordinal : " + this.getOrdinal() + "\n");
        stringBuilder.append("************\n");

        return stringBuilder.toString();
    }
}
