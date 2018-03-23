package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.StartCheck;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponceFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements StartCheck {

    private MainModele mainModele;


    public GetResponceFromApiPresenter() {
        mainModele = new MainModele(this);
        callApi();
    }

    @Override
    public void start(ArrayList<PhotoRealmMoxy> listPhotoRealm) {

        if (!listPhotoRealm.isEmpty()) {

            getViewState().getResponce(listPhotoRealm);
        } else {
            getViewState().getResponseFromRealmInFail();
        }

    }

    public void callApi(){
        Request.getListPhotoRealmMoxyObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainModele.getObserver());
    }
}
