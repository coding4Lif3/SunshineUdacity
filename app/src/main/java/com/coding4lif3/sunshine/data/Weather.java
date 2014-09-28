package com.coding4lif3.sunshine.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by federicomonaco on 9/28/14.
 */
public class Weather {

    private City city;
    private int cnt;
    private int cod;
    @SerializedName("list")
    private ArrayList<WeatherDescription> weatherDesc;
    private String message;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public ArrayList<WeatherDescription> getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(ArrayList<WeatherDescription> weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
