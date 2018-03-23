package com.example.stanislau_bushuk.epamtest.Task3.Modele;

import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponceFromApiPresenter;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public class MainModele {


    private ArrayList<PhotoRealmMoxy> photoRealmArrayList;
    private Realm realm;
    private StartCheck startCheck;
    private Observer<ListPhotoRealmMoxy> listPhotoRealmMoxyObserver;

    public MainModele(GetResponceFromApiPresenter getResponceFromApiPresenter) {
        initRealm();
        ListPhotoRealmMoxy listPhotoRealm = realm.where(ListPhotoRealmMoxy.class).findFirst();
        startCheck = getResponceFromApiPresenter;
        try {
            listPhotoRealm = realm.createObject(ListPhotoRealmMoxy.class);
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

    public void getResponce() {
        listPhotoRealmMoxyObserver=new Observer<ListPhotoRealmMoxy>() {
            @Override
            public void onSubscribe(Disposable d) {
                Timber.e("Subscribe");
            }

            @Override
            public void onNext(ListPhotoRealmMoxy listPhotoRealmMoxy) {
                Timber.e("Next");
                setListPhotoRealm(listPhotoRealmMoxy);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("Eror");
            }

            @Override
            public void onComplete() {
                Timber.e("complete");
                startCheck.start(photoRealmArrayList);
            }
        };
    }
    public Observer<ListPhotoRealmMoxy> getObserver(){
        return this.listPhotoRealmMoxyObserver;
    }
}
