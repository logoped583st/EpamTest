package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {


    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;
    private StartCheck startCheck;
    private Flowable<ListPhotoRealmMoxy> observable;

    public MainModele() {
        App.getAppComponent().inject(this);
        initRealm();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        setRealmObjects();
    }

    public void setCallback(GetResponseFromApiPresenter getResponseFromApiPresenter) {
        startCheck = getResponseFromApiPresenter;
    }

    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        observable=Flowable.just(listPhotoRealm);
    }

    public ArrayList<PhotoRealmMoxy> getPhotoRealmArrayList() {
        return photoRealmArrayList;
    }

    public void setAnotherObservable() {
        setRealmObjects();
        if (photoRealmArrayList.size() != 0) {
            startCheck.startGoToView(observable, false);
        } else {
            startCheck.startGoToView(null, false);
        }
    }

    public void setRealmObjects() {
        ListPhotoRealmMoxy listPhotoRealmMoxy = realm.where(ListPhotoRealmMoxy.class).findFirst();
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealmMoxy != null) {
            photoRealmArrayList.addAll(listPhotoRealmMoxy.getPhotos());
            observable = Flowable.just(listPhotoRealmMoxy);
        }
    }
}
