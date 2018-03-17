package com.example.stanislau_bushuk.epamtest.Modele;


import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ListPhotoRealm extends RealmObject {

    public ListPhotoRealm(){

    }

    private RealmList <PhotoRealm> photos;

    public RealmList<PhotoRealm> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<PhotoRealm> photos) {
        this.photos = photos;
    }
}
