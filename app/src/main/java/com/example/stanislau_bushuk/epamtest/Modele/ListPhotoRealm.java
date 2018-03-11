package com.example.stanislau_bushuk.epamtest.Modele;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ListPhotoRealm extends RealmObject {

    private RealmList<PhotoRealm> photosFromRealm;
    public ListPhotoRealm(){

    }
    public ListPhotoRealm(RealmList <PhotoRealm> photosFromRealm) {
        this.photosFromRealm = photosFromRealm;
    }

    public RealmList<PhotoRealm> getPhotosFromRealm() {
        return photosFromRealm;
    }

    public void setPhotosFromRealm(RealmList<PhotoRealm> photosFromRealm) {
        this.photosFromRealm = photosFromRealm;
    }
}
