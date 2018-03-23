package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import io.realm.RealmObject;

/**
 * Created by Stanislau_Bushuk on 3/11/2018.
 */

public class PhotoRealmMoxy extends RealmObject {

    private String title;
    private String description;
    private String url;
    private int id;
    private double latitude;
    private double longitude;

    public PhotoRealmMoxy() {

    }

    public PhotoRealmMoxy(String title, String description, String url, int id, double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.latitude = latitude;
        this.url = url;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
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

    public double getLongitude() {
        return longitude;
    }

}
