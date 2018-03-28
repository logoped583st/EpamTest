package com.example.stanislau_bushuk.epamtest.Task3.Component;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModel;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModel;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleIAPI;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleMainModel;
import com.example.stanislau_bushuk.epamtest.Task3.Module.ModuleNetworkModel;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stanislau_Bushuk on 3/26/2018.
 */
@Singleton
@Component(modules = {ModuleMainModel.class, ModuleNetworkModel.class, ModuleIAPI.class})
public interface AppComponent {
    void inject(GetResponseFromApiPresenter getResponseFromApiPresenter);
    void inject(MainModel mainModele);
    void inject(NetworkModel networkModele);
}
