package com.example.stanislau_bushuk.epamtest;

import android.app.Application;


import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.Task3.Component.AppComponent;
import com.example.stanislau_bushuk.epamtest.Task3.Component.DaggerAppComponent;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleMainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleRequest;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class App extends Application {

    private static AppComponent appComponent;
    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Request request = new Request();
        request.getJson();


        setRealm();
        Timber.plant(new Timber.DebugTree());
        appComponent=buildComponent();
    }
    public void setRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realm.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
    public AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .moduleMainModele(new ModuleMainModele())
                .moduleRequest(new ModuleRequest())
                .build();
    }
}
