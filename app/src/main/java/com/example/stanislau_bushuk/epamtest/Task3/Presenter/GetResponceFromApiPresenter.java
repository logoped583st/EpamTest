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

    public void checkResponce(ListPhotoRealmMoxy listPhotoRealm) {
        ArrayList<PhotoRealmMoxy> responcePhotoRealm = new ArrayList<>();
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
    public void start(ListPhotoRealmMoxy listPhotoRealm) {
        checkResponce(listPhotoRealm);
    }
}
