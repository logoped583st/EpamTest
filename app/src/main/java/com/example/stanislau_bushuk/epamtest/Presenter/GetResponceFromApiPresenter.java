package com.example.stanislau_bushuk.epamtest.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.StartCheck;

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
        initTempListPhotoRealm();

        // checkResponce(mainModele.getListPhotoRealm()); //вызов с интерфейса
    }


    public void initTempListPhotoRealm() {
        if (!mainModele.getPhotoRealmArrayList().isEmpty()) {
            getViewState().getResponceFromRealm(mainModele.getPhotoRealmArrayList());
        } else {
            getViewState().getResponseFromRealmInFail();
        }
    }

    public void checkResponce(ListPhotoRealm listPhotoRealm) {
        ArrayList<PhotoRealm> responcePhotoRealm = new ArrayList<>();
        responcePhotoRealm.addAll(listPhotoRealm.getPhotos());
        if (!mainModele.getPhotoRealmArrayList().isEmpty()) {
            if (mainModele.getPhotoRealmArrayList().size() == responcePhotoRealm.size()) {
                for (int i = 0; i < responcePhotoRealm.size(); i++) {
                    if (mainModele.getPhotoRealmArrayList().get(i).getId() != responcePhotoRealm.get(i).getId()) {
                        mainModele.getPhotoRealmArrayList().clear();
                        mainModele.getPhotoRealmArrayList().addAll(responcePhotoRealm);
                        getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                        break;
                    }
                }
            } else {
                mainModele.getPhotoRealmArrayList().clear();
                mainModele.getPhotoRealmArrayList().addAll(responcePhotoRealm);
                getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                Timber.e("notify size");
            }
        } else {
            mainModele.getPhotoRealmArrayList().clear();
            mainModele.getPhotoRealmArrayList().addAll(responcePhotoRealm);
            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
            Timber.e("notify null");
        }
    }


    @Override
    public void start(ListPhotoRealm listPhotoRealm) {
        checkResponce(listPhotoRealm);
    }
}
