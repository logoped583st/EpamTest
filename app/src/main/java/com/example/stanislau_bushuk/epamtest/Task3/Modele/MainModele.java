package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.realm.Realm;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {


    private ArrayList<PhotoRealmMoxy> photoList;
    private Realm realm;
    private ICallBackFromModele callBack;
    private Flowable<ListPhotoRealmMoxy> observable;

    public MainModele() {
        App.getAppComponent().inject(this);
        initRealm();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        setRealmObjects();
    }

    public void setCallback(GetResponseFromApiPresenter presenter) {
        callBack = presenter;
    }

    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoList.clear();
        photoList.addAll(listPhotoRealm.getPhotos());
        observable=Flowable.just(listPhotoRealm);
    }

    public ArrayList<PhotoRealmMoxy> getPhotoList() {
        return photoList;
    }

    public void setAnotherFlowable() {
        setRealmObjects();
        if (photoList.size() != 0) {
            callBack.callBack(observable, false);
        } else {
            callBack.callBack(null, false);
        }
    }

    public void setRealmObjects() {
        ListPhotoRealmMoxy listPhotoRealmMoxy = realm.where(ListPhotoRealmMoxy.class).findFirst();
        photoList = new ArrayList<>();
        if (listPhotoRealmMoxy != null) {
            photoList.addAll(listPhotoRealmMoxy.getPhotos());
            observable = Flowable.just(listPhotoRealmMoxy);
        }
    }
}

