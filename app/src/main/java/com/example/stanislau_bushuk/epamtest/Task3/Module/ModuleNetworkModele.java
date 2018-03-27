package com.example.stanislau_bushuk.epamtest.Task3.Module;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.NetworkModele;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleNetworkModele {

    @Provides
    @Singleton
    public NetworkModele getNetworkModele(){
        return new NetworkModele();
    }
}
