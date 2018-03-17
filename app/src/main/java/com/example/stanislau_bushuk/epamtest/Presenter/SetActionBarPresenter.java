package com.example.stanislau_bushuk.epamtest.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.IView.SetActionBarView;


/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class SetActionBarPresenter extends MvpPresenter<SetActionBarView> {
    public SetActionBarPresenter() {
        getViewState().initActionBar("Moxy");
    }
}
