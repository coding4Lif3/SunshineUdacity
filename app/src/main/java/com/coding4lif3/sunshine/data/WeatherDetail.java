package com.coding4lif3.sunshine.data;

/**
 * Created by federicomonaco on 9/28/14.
 */
public class WeatherDetail {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    private String description;
    private String icon;
    private long id;
    private String main;
}
