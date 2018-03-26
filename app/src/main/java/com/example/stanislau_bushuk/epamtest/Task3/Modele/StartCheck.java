package com.example.stanislau_bushuk.epamtest.Task3.Modele;


import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Stanislau_Bushuk on 3/21/2018.
 */

public interface StartCheck {
    void startGoToView(Observable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable);
}
