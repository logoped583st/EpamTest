package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.StartCheck;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponceFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements StartCheck {

    private MainModele mainModele;


    public GetResponceFromApiPresenter() {
        mainModele = new MainModele(this);
    }

    @Override
    public void start(ListPhotoRealmMoxy listPhotoRealm) {
        Timber.e("interface %s", listPhotoRealm.getPhotos());
        if(!listPhotoRealm.getPhotos().isEmpty())
            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
        else{
            getViewState().getResponseFromRealmInFail();
        }
    }
}
