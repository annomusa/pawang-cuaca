package com.example.maunorafiq.pawangcuaca.model.reamldb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by maunorafiq on 10/28/16.
 */
public class RealmCity extends RealmObject{

    @PrimaryKey
    private String id;

    private int ordinal;

    private String timeChecked;

    private String name;

    private String temperature;

    private String imageUrl;



    public void setId(String id) {
        this.id = id;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public void setTimeChecked(String timeChecked) {
        this.timeChecked = timeChecked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getId() {
        return id;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getTimeChecked() {
        return timeChecked;
    }

    public String getName(){
        return name;
    }

    public String getTemperature(){
        return temperature;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
