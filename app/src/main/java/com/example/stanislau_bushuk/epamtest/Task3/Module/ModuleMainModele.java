package com.example.stanislau_bushuk.epamtest.Task3.Module;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.MainModele;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stanislau_Bushuk on 3/26/2018.
 */
@Module
public class ModuleMainModele {

    @Provides
    @Singleton
    public MainModele getMainModele(){
        return new MainModele();
    }
}
