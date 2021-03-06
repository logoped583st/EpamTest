package com.example.stanislau_bushuk.epamtest.Task3.IView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotosObj;


import java.util.List;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public interface GetResponceFromApi extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void getResponce(List<PhotosObj> listPhotoRealm);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void getResponseFromRealmInFail();

}
