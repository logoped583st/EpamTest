package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import android.support.annotation.NonNull;

import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponceFromApiPresenter;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {


    private ListPhotoRealmMoxy listPhotoRealm;
    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;
    private StartCheck startCheck;

    public MainModele(GetResponceFromApiPresenter getResponceFromApiPresenter) {
        initRealm();
        listPhotoRealm = realm.where(ListPhotoRealmMoxy.class).findFirst();
        startCheck = getResponceFromApiPresenter;
        try {
            this.listPhotoRealm = realm.createObject(ListPhotoRealmMoxy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoRealmArrayList = new ArrayList<>();
        if (listPhotoRealm != null)
            photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        getResponce();
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
    }


    public void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        Timber.e("Realm%s", realm.where(ListPhotoRealmMoxy.class).findAll());
        Timber.e(String.valueOf(photoRealmArrayList.size()));
    }

    public ArrayList<PhotoRealmMoxy> getPhotoRealmArrayList() {
        return this.photoRealmArrayList;
    }

    public void getResponce() {
        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealmMoxy>() {
            @Override
            public void onResponse(@NonNull Call<ListPhotoRealmMoxy> call, @NonNull Response<ListPhotoRealmMoxy> response) {
                setListPhotoRealm(response.body());
                startCheck.start(photoRealmArrayList);
            }

            @Override
            public void onFailure(@NonNull Call<ListPhotoRealmMoxy> call, @NonNull Throwable t) {
                Timber.e("RESPONCEFAIL");
                startCheck.start(photoRealmArrayList);
                t.printStackTrace();
            }
        });
    }
}
