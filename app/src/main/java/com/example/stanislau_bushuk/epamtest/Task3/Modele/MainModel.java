package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModel {


    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;
    private StartCheck startCheck;
    private Flowable<ListPhotoRealmMoxy> flowable;

    public MainModel() {
        App.getAppComponent().inject(this);
        initRealm();
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void setCallback(GetResponseFromApiPresenter getResponseFromApiPresenter) {
        setRealmObjects();
        startCheck = getResponseFromApiPresenter;
    }

    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealmMoxy) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAllAsync().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealmMoxy);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealmMoxy.getPhotos());
        flowable = realm.where(ListPhotoRealmMoxy.class).findFirstAsync().asFlowable();
    }

    public ArrayList<PhotoRealmMoxy> getPhotoRealmArrayList() {
        return photoRealmArrayList;
    }

    public void setAnotherObservable() {
        realm=Realm.getDefaultInstance();
        Timber.e("METHOD");
        flowable=realm.where(ListPhotoRealmMoxy.class).findFirstAsync().asFlowable();
        startCheck.startGoToView(flowable,false);
        Timber.e("EndMethod");
    }

    public void setRealmObjects() {
        ListPhotoRealmMoxy listPhotoRealmMoxy = realm.where(ListPhotoRealmMoxy.class).findFirst();
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealmMoxy != null) {
            photoRealmArrayList.addAll(listPhotoRealmMoxy.getPhotos());
        }
    }

    public Realm getRealm() {
        return realm;
    }
}

