package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import io.realm.RealmList;
import io.realm.RealmObject;

public class ListPhotoRealmMoxy extends RealmObject {

    private RealmList<PhotoRealmMoxy> photos;

    public ListPhotoRealmMoxy() {

    }


    public RealmList<PhotoRealmMoxy> getPhotos() {
        return photos;
    }
}
