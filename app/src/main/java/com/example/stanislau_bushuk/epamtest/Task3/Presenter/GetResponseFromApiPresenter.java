package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.StartCheck;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponseFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements StartCheck {

    @Inject
    MainModele mainModele;
    private boolean flag = true;

    public GetResponseFromApiPresenter() {
        App.getAppComponent().inject(this);
        mainModele.setGetResponseFromApiPresenter(this);
    }

    public void callApi(Observable<ListPhotoRealmMoxy> listPhotoRealmObservable) {//вызов через интерфейс с модели
        listPhotoRealmObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListPhotoRealmMoxy>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.e("Subscribe");

                    }

                    @Override
                    public void onNext(ListPhotoRealmMoxy listPhotoRealmMoxy) {
                        Timber.e("Next");
                        if(flag!=false) {
                            mainModele.setListPhotoRealm(listPhotoRealmMoxy);
                            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                        }else{
                            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                        }
                        flag=true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Eror");
                        e.printStackTrace();
                        mainModele.setAnotherObservable();
                        flag = false;
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("complete");
                    }
                });
    }

    @Override
    public void startGoToView(Observable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable) {
        if (listPhotoRealmMoxyObservable != null) {
            callApi(listPhotoRealmMoxyObservable);
        }else getViewState().getResponseFromRealmInFail();
    }
}
