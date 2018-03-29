package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.IAPI;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class NetworkModele {

    @Inject
    IAPI iapi;

    private GetResponseFromApiPresenter presenter;

    public NetworkModele() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(GetResponseFromApiPresenter presenter) {
        this.presenter = presenter;
        ICallBackFromModele callback = presenter;
        Observable<Photos> observable = iapi.getJson();
        callback.callBack(observable.subscribeOn(Schedulers.newThread()), true);
    }
}
