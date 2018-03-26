package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {

    @Inject
    Request request;
    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;
    private ListPhotoRealmMoxy listPhotoRealmMoxy;
    private StartCheck startCheck;
    private GetResponseFromApiPresenter getResponseFromApiPresenter;
    private Observable<ListPhotoRealmMoxy> observable;

    public MainModele() {
        App.getAppComponent().inject(this);
        initRealm();
        listPhotoRealmMoxy = realm.where(ListPhotoRealmMoxy.class).findFirst();
        try {
            listPhotoRealmMoxy = realm.createObject(ListPhotoRealmMoxy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealmMoxy != null) {
            photoRealmArrayList.addAll(listPhotoRealmMoxy.getPhotos());
        }
    }


    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void setGetResponseFromApiPresenter(GetResponseFromApiPresenter getResponseFromApiPresenter) {
        this.getResponseFromApiPresenter = getResponseFromApiPresenter;
        startCheck = getResponseFromApiPresenter;
        startCheck.startGoToView(request.getListPhotoRealmMoxyObservable());
    }

    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        observable = Observable.just(listPhotoRealm);
    }

    public ArrayList<PhotoRealmMoxy> getPhotoRealmArrayList() {
        return photoRealmArrayList;
    }

    public void setAnotherObservable() {
        if (photoRealmArrayList.size() != 0) {
            observable = Observable.just(listPhotoRealmMoxy);
            startCheck.startGoToView(observable);
        }else{
            startCheck.startGoToView(null);
        }
    }
}

