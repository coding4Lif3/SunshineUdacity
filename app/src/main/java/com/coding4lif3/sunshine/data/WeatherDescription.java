package com.coding4lif3.sunshine.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by federicomonaco on 9/28/14.
 */
public class WeatherDescription {

    private long clouds;
    private long deg;
    private long dt;
    private long humidity;
    private double pressure;
    private double speed;
    private Temperature temp;
    @SerializedName("weather")
    private ArrayList<WeatherDetail> weatherDetail;

    public long getClouds() {
        return clouds;
    }

    public void setClouds(long clouds) {
        this.clouds = clouds;
    }

    public long getDeg() {
        return deg;
    }

    public void setDeg(long deg) {
        this.deg = deg;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public ArrayList<WeatherDetail> getWeatherDetail() {
        return weatherDetail;
    }

    public void setWeatherDetail(ArrayList<WeatherDetail> weatherDetail) {
        this.weatherDetail = weatherDetail;
    }
}
