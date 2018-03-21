package com.example.stanislau_bushuk.epamtest.IView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public interface GetResponceFromApi extends MvpView {



   @StateStrategyType(AddToEndSingleStrategy.class)
   void getResponce(ArrayList<PhotoRealm> listPhotoRealm);

   void getResponceFromRealm(ArrayList<PhotoRealm> photoRealmArrayList);

   void getResponseFromRealmInFail();

}
