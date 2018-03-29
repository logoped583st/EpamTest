package com.example.stanislau_bushuk.epamtest.Task3.Modele;



import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.IAPI;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;
import javax.inject.Inject;


public class NetworkModele {

    @Inject
    IAPI iapi;

    public NetworkModele() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(GetResponseFromApiPresenter presenter) {
        ICallBackFromModele callback = presenter;
        callback.callBack(iapi.getJson(), true);
    }
}
