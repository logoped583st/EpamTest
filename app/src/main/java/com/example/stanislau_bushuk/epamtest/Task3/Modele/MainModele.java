package com.example.stanislau_bushuk.epamtest.Task3.Modele;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import java.util.ArrayList;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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

    public MainModele(GetResponseFromApiPresenter getResponceFromApiPresenter) {
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
        getResponse();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }


    private void setListPhotoRealm(ListPhotoRealmMoxy listPhotoRealm) {
        realm.beginTransaction();
        realm.where(ListPhotoRealmMoxy.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(listPhotoRealm);
        realm.commitTransaction();
        photoRealmArrayList.clear();
        photoRealmArrayList.addAll(listPhotoRealm.getPhotos());
        Timber.e("Realm%s", realm.where(ListPhotoRealmMoxy.class).findAll());
        Timber.e(String.valueOf(photoRealmArrayList.size()));
    }

    private void getResponse() {
        listPhotoRealmMoxyObserver = new Observer<ListPhotoRealmMoxy>() {
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
                startCheck.startGoToView(photoRealmArrayList);
            }

            @Override
            public void onComplete() {
                Timber.e("complete");
                startCheck.startGoToView(photoRealmArrayList);

            }
        };

    }


    public Observer<ListPhotoRealmMoxy> getObserver() {
        return this.listPhotoRealmMoxyObserver;
    }
}
