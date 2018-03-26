package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponseFromApiPresenter extends MvpPresenter<GetResponceFromApi> {

    @Inject
    MainModele mainModele;
    @Inject
    Request request;
    private Observer<ListPhotoRealmMoxy> listPhotoRealmMoxyObserver;

    public GetResponseFromApiPresenter() {
        App.getAppComponent().inject(this);
        callApi();
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
                mainModele.setListPhotoRealm(listPhotoRealmMoxy);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("Eror");
                if (mainModele.getPhotoRealmArrayList().size() != 0) {
                    getViewState().getResponce(mainModele.getPhotoRealmArrayList());//кинуть обзёрвабл с реалма
                }
            }

            @Override
            public void onComplete() {
                Timber.e("complete");
                getViewState().getResponce(mainModele.getPhotoRealmArrayList());
            }
        };
    }

    private void callApi() {
        getResponse();
        request.getListPhotoRealmMoxyObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listPhotoRealmMoxyObserver);
    }
}
