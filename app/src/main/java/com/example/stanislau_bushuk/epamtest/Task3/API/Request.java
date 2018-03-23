package com.example.stanislau_bushuk.epamtest.Task3.API;


import com.example.stanislau_bushuk.epamtest.Task3.Modele.ListPhotoRealmMoxy;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Stanislau_Bushuk on 3/9/2018.
 */

public class Request {

    private static IAPI iapi;
    private static Observable<ListPhotoRealmMoxy> listPhotoRealmMoxyObservable;

    public static IAPI getIapi() {
        return iapi;
    }

    public static Observable<ListPhotoRealmMoxy> getListPhotoRealmMoxyObservable() {
        return listPhotoRealmMoxyObservable;
    }

    public void getJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        iapi = retrofit.create(IAPI.class);
        listPhotoRealmMoxyObservable = getIapi().getJson();
    }


    public interface IAPI {
        @GET("/bins/upt7z")
        Observable<ListPhotoRealmMoxy> getJson();
    }
}
