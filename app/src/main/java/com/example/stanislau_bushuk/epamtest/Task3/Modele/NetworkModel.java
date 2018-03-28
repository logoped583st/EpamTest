package com.example.stanislau_bushuk.epamtest.Task3.Modele;



import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.IAPI;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;
import javax.inject.Inject;


public class NetworkModel {

    private StartCheck startCheck;
    @Inject
    IAPI iapi;

    public NetworkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(GetResponseFromApiPresenter getResponseFromApiPresenter) {
        startCheck = getResponseFromApiPresenter;
        startCheck.startGoToView(iapi.getJson(), true);
    }
}
