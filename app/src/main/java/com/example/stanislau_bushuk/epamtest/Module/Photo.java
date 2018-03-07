package com.example.stanislau_bushuk.epamtest.Module;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class Photo {
    private int id;
    private String title;
    private String url;
    private double longitude;
    private double latitude;

    public Photo(int id, String name, String url, double longitude, double latitude) {
        this.id = id;
        this.title=name;
        this.url = url;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        title = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
