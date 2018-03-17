package com.example.stanislau_bushuk.epamtest.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.IView.SetActionBarView;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class SetActionBarPresenter extends MvpPresenter<SetActionBarView> {
    public SetActionBarPresenter() {
        getViewState().initActionBar("Moxy");
    }
}
