package com.example.stanislau_bushuk.epamtest.Task3.Module;

import com.example.stanislau_bushuk.epamtest.Task3.API.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stanislau_Bushuk on 3/26/2018.
 */

@Module
public class ModuleRequest {

    @Provides
    @Singleton
    public Request getRequest(){
        return new Request();
    }
}
