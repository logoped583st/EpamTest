package com.example.stanislau_bushuk.epamtest.Modele;

import io.realm.RealmObject;

/**
 * Created by Stanislau_Bushuk on 3/11/2018.
 */

public class PhotoRealm extends RealmObject {
    private String title;
    private String description;
    private String url;
    private int id;
    private double latitude;
    private double longitude;

    public PhotoRealm(){

    }

    public PhotoRealm(String title, String description, String url, int id, double latitude, double longitude) {
        this.title=title;
        this.description=description;
        this.id=id;
        this.latitude=latitude;
        this.url=url;
        this.longitude=longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
