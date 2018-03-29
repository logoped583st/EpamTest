package com.example.stanislau_bushuk.epamtest.Task3.API;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.Photos;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IAPI {
    @GET("/bins/upt7z")
    Observable<Photos> getJson();
}

