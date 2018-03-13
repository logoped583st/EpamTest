package com.example.stanislau_bushuk.epamtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Timber.plant(new Timber.DebugTree());
    }
}
