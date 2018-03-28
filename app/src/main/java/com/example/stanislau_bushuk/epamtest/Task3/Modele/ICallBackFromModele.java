package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public interface ICallBackFromModele {
    void callBack(Flowable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable, Boolean flag);
}
