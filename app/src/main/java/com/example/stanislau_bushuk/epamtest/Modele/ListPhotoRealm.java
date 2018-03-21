package com.example.stanislau_bushuk.epamtest.Modele;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ListPhotoRealm extends RealmObject {

    private RealmList<PhotoRealm> photos;

    public ListPhotoRealm() {

    }

    public RealmList<PhotoRealm> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<PhotoRealm> photos) {
        this.photos = photos;
    }
}
