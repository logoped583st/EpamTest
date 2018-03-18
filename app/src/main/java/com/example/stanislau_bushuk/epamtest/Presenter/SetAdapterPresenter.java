package com.example.stanislau_bushuk.epamtest.Presenter;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask3;
import com.example.stanislau_bushuk.epamtest.IView.SetAdapterView;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

import io.realm.RealmList;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class SetAdapterPresenter extends MvpPresenter<SetAdapterView> {

    public SetAdapterPresenter(){
        getViewState().setImageAdapter("aaa");
        getViewState().setTextAdapter("aaa");
    }


}
