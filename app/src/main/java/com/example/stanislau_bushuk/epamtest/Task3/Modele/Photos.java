package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import io.realm.RealmList;
import io.realm.RealmObject;

public class Photos extends RealmObject {

    private RealmList<PhotosObj> photos;

    public Photos() {

    }

    public RealmList<PhotosObj> getPhotos() {
        return this.photos;
    }
}
