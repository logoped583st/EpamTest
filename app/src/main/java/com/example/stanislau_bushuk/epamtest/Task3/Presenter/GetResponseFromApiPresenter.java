package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.StartCheck;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponseFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements StartCheck {

    @Inject
    MainModele mainModele;
    @Inject
    Request request;

    public GetResponseFromApiPresenter() {
        App.getAppComponent().inject(this);
        mainModele.setStartCheck(this);
        callApi();
    }

    @Override
    public void startGoToView(ArrayList<PhotoRealmMoxy> listPhotoRealm) {
        if (!listPhotoRealm.isEmpty()) {
            getViewState().getResponce(listPhotoRealm);
        } else {
            getViewState().getResponseFromRealmInFail();
        }

    }

    public void callApi() {
        request.getListPhotoRealmMoxyObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainModele.getObserver());
    }

}
