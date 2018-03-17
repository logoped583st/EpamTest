package com.example.stanislau_bushuk.epamtest.IView;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

import io.realm.RealmList;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public interface getResponceFromApi extends MvpView {
   void getResponce(ListPhotoRealm listPhotoRealm);
}
