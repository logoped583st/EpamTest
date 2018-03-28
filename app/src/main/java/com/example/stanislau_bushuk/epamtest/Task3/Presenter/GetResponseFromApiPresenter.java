package com.example.stanislau_bushuk.epamtest.Task3.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModel;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModel;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.StartCheck;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponseFromApiPresenter extends MvpPresenter<GetResponceFromApi> implements StartCheck {

    @Inject
    MainModel mainModele;

    @Inject
    NetworkModel networkModele;

    public GetResponseFromApiPresenter() {
        App.getAppComponent().inject(this);
        mainModele.setCallback(this);
        networkModele.setCallBack(this);
    }

    public void callApi(final Flowable<ListPhotoRealmMoxy> listPhotoRealmObservable, final Boolean flag) {//вызов через интерфейс с модели
        listPhotoRealmObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListPhotoRealmMoxy>() {
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
                            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                        } else {
                            //syda ne doxodi
                           // mainModele.getRealm()=Realm.getDefaultInstance();
                            getViewState().getResponce(mainModele.getPhotoRealmArrayList());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e("Error");
                        t.printStackTrace();
                        mainModele.setAnotherObservable();
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("complete");
                    }
                });

    }

    @Override
    public void startGoToView(Flowable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable, Boolean flag) {
        if (listPhotoRealmMoxyObservable != null) {
            callApi(listPhotoRealmMoxyObservable, flag);
        } else getViewState().getResponseFromRealmInFail();
    }

}
