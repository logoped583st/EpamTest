package com.example.stanislau_bushuk.epamtest;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class App extends Application {
    public static volatile Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        Timber.plant(new Timber.DebugTree());
    }
}
