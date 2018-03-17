package com.example.stanislau_bushuk.epamtest.Presenter;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.IView.SetAdapterView;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class SetAdapterPresenter extends MvpPresenter<SetAdapterView> {

    public SetAdapterPresenter(String text, String url){
        getViewState().setText(text);
        getViewState().setImage(url);
    }


}
