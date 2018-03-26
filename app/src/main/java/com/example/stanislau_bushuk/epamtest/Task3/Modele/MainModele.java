package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.realm.Realm;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {

    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;

    private ListPhotoRealmMoxy listPhotoRealmMoxy;

    public MainModele() {
        initRealm();
        listPhotoRealmMoxy = realm.where(ListPhotoRealmMoxy.class).findFirst();
        try {
            listPhotoRealmMoxy = realm.createObject(ListPhotoRealmMoxy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealmMoxy != null)
            photoRealmArrayList.addAll(listPhotoRealmMoxy.getPhotos());
    }



    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
    }
    public ArrayList<PhotoRealmMoxy> getPhotoRealmArrayList(){
        return photoRealmArrayList;
    }
}
