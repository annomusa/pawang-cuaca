package com.example.maunorafiq.pawangcuaca.model;

/**
 * Created by maunorafiq on 10/28/16.
 */
public class City {
    private String name;
    private String temperature;

    public City (String name, String temperature){
        this.name = name;
        this.temperature = temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getTemperature(){
        return temperature;
    }


}
