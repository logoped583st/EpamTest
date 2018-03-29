package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import android.annotation.SuppressLint;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {


    private Realm realm;
    private ICallBackFromModele callBack;


    public MainModele() {
        App.getAppComponent().inject(this);
        initRealm();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void setCallback(GetResponseFromApiPresenter presenter) {
        callBack = presenter;
    }

    public void setListPhotoRealm(Photos photos) {
        realm.beginTransaction();
        realm.where(Photos.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(photos);
        realm.commitTransaction();
    }

    public void setAnotherFlowable() {
        Flowable<Photos> flowable = realm.where(Photos.class).findFirstAsync().asFlowable();
        Observable<Photos> observable = flowable.toObservable();
        callBack.callBack(observable.subscribeOn(AndroidSchedulers.mainThread()), false);

        Timber.e("SetAnother Flowable  "+Thread.currentThread().toString());
    }

}

