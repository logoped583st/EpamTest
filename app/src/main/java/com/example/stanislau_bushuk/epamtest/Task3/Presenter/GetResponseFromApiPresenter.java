package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ICallBackFromModele;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public void callApi(Flowable<ListPhotoRealmMoxy> listPhotoRealmObservable, final Boolean flag) {//вызов через интерфейс с модели
        listPhotoRealmObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<ListPhotoRealmMoxy>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Timber.e("Subscribe");
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ListPhotoRealmMoxy listPhotoRealmMoxy) {
                        Timber.e("Next");
                        if (flag) {
                            mainModele.setListPhotoRealm(listPhotoRealmMoxy);
                            getViewState().getResponce(mainModele.getPhotoList());
                        } else {
                            getViewState().getResponce(mainModele.getPhotoList());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e("Error");
                        t.printStackTrace();
                        mainModele.setRealmObjects();
                        mainModele.setAnotherFlowable();
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("complete");
                    }
                });

    }

    @Override
    public void callBack(Flowable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable, Boolean flag) {
        if (listPhotoRealmMoxyObservable != null) {
            callApi(listPhotoRealmMoxyObservable, flag);
        } else getViewState().getResponseFromRealmInFail();
    }

}
