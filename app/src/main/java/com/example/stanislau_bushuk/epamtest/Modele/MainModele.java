package com.example.stanislau_bushuk.epamtest.Modele;

import java.util.ArrayList;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {
    private ListPhotoRealm listPhotoRealm;
    private ArrayList<PhotoRealm> photoRealmArrayList;

    private Realm realm;

    public MainModele() {
        initRealm();
        listPhotoRealm = realm.where(ListPhotoRealm.class).findFirst();

        try {
            this.listPhotoRealm = realm.createObject(ListPhotoRealm.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealm != null)
            photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    public ListPhotoRealm getListPhotoRealm() {
        return this.listPhotoRealm;
    }

    public void setListPhotoRealm(ListPhotoRealm listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealm.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        // photoRealmArrayList.clear();
        // photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        Timber.e("Realm%s", realm.where(ListPhotoRealm.class).findAll());
        Timber.e(String.valueOf(photoRealmArrayList.size()));
    }

    public ArrayList<PhotoRealm> getPhotoRealmArrayList() {
        return this.photoRealmArrayList;
    }

}
