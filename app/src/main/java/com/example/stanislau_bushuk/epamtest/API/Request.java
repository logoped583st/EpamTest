package com.example.stanislau_bushuk.epamtest.API;




import com.example.stanislau_bushuk.epamtest.Task3.Modele.Photos;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Stanislau_Bushuk on 3/9/2018.
 */


public class Request {

    private static IAPI iapi;

    public static IAPI getIapi() {
        return iapi;
    }

    public void getJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iapi = retrofit.create(IAPI.class);

    }

    public interface IAPI {
        @GET("/bins/upt7z")
        Call<Photos> getJson();
    }
}
