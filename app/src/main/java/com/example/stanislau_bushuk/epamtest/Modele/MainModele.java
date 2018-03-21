package com.example.stanislau_bushuk.epamtest.Modele;

import android.support.annotation.NonNull;

import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.Presenter.GetResponceFromApiPresenter;

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


    private ListPhotoRealm listPhotoRealm;
    private ArrayList<PhotoRealm> photoRealmArrayList;
    private Realm realm;
    private StartCheck startCheck;

    public MainModele(GetResponceFromApiPresenter getResponceFromApiPresenter) {
        initRealm();
        listPhotoRealm = realm.where(ListPhotoRealm.class).findFirst();
        startCheck=getResponceFromApiPresenter;
        try {
            this.listPhotoRealm = realm.createObject(ListPhotoRealm.class);
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

    public ListPhotoRealm getListPhotoRealm() {
        return this.listPhotoRealm;
    }

    public void setListPhotoRealm(ListPhotoRealm listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealm.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        Timber.e("Realm%s", realm.where(ListPhotoRealm.class).findAll());
        Timber.e(String.valueOf(photoRealmArrayList.size()));
    }

    public ArrayList<PhotoRealm> getPhotoRealmArrayList() {
        return this.photoRealmArrayList;
    }

    public void getResponce() {
        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(@NonNull Call<ListPhotoRealm> call, @NonNull Response<ListPhotoRealm> response) {
                setListPhotoRealm(response.body());
                startCheck.start(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ListPhotoRealm> call, @NonNull Throwable t) {
                Timber.e("RESPONCEFAIL");
                t.printStackTrace();
            }
        });
    }
}
