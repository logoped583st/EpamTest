package com.example.stanislau_bushuk.epamtest.Task3.Module;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleNetworkModel {

    @Provides
    @Singleton
    public NetworkModel getNetworkModele(){
        return new NetworkModel();
    }
}
