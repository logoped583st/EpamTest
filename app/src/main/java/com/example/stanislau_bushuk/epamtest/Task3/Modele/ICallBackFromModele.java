package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import io.reactivex.Observable;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public interface ICallBackFromModele {
    void callBack(Observable<Photos> listPhotoRealmMoxyObservable, Boolean flag);
}
