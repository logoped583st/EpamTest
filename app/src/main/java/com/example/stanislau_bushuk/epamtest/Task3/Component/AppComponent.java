package com.example.stanislau_bushuk.epamtest.Task3.Component;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModele;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleMainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleNetworkModele;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleRequest;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stanislau_Bushuk on 3/26/2018.
 */
@Singleton
@Component(modules = {ModuleMainModele.class, ModuleRequest.class, ModuleNetworkModele.class})
public interface AppComponent {
    void inject(GetResponseFromApiPresenter getResponseFromApiPresenter);
    void inject(MainModele mainModele);
    void inject(NetworkModele networkModele);
}
