package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ICallBackFromModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.Photos;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponseFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements ICallBackFromModele {

    @Inject
    MainModele mainModele;

    @Inject
    NetworkModele networkModele;

    public GetResponseFromApiPresenter() {
        App.getAppComponent().inject(this);
        mainModele.setCallback(this);
        networkModele.setCallBack(this);
    }

    public void callApi(final Observable<Photos> observable, final Boolean flag) {//вызов через интерфейс с модели;

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Photos>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.e("Subscribe " + d.toString());
                        Timber.e("Subscribe  " + Thread.currentThread().toString());
                    }

                    @Override
                    public void onNext(Photos listPhotoRealmMoxy) {
                        Timber.e("Next");
                        if (flag) {
                            mainModele.setListPhotoRealm(listPhotoRealmMoxy);
                        }
                        getViewState().getResponce(listPhotoRealmMoxy.getPhotos());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e("Error");
                        t.printStackTrace();
                        Timber.e("Eror  " + Thread.currentThread().toString());
                        //mainModele.setRealmObjects();
                        mainModele.setAnotherFlowable();
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("complete");
                    }
                });

    }

    @Override
    public void callBack(Observable<Photos> listPhotoRealmMoxyObservable, Boolean flag) {
        if (listPhotoRealmMoxyObservable != null) {
            callApi(listPhotoRealmMoxyObservable, flag);
        } else getViewState().getResponseFromRealmInFail();
    }

}
