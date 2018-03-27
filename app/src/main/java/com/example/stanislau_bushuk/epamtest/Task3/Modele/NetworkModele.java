package com.example.stanislau_bushuk.epamtest.Task3.Modele;



import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import javax.inject.Inject;


public class NetworkModele {

    @Inject
    Request request;

    public NetworkModele() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(GetResponseFromApiPresenter getResponseFromApiPresenter) {
        StartCheck startCheck = getResponseFromApiPresenter;
        startCheck.startGoToView(request.getListPhotoRealmMoxyObservable(), true);
    }
}
