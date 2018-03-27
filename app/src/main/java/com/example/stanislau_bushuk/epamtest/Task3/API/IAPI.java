package com.example.stanislau_bushuk.epamtest.Task3.API;

import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;

import io.reactivex.Flowable;
import retrofit2.http.GET;


public interface IAPI {
    @GET("/bins/upt7z")
    Flowable<ListPhotoRealmMoxy> getJson();
}

