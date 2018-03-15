package com.example.stanislau_bushuk.epamtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


import com.example.stanislau_bushuk.epamtest.API.Request;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Request request = new Request();
        request.getJson();
        setRealm();
        Timber.plant(new Timber.DebugTree());
    }
    public void setRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realm.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
