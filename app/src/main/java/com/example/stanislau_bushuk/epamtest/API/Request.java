package com.example.stanislau_bushuk.epamtest.API;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/9/2018.
 */

public class Request {

    private static IAPI iapi;

    public void getJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iapi = retrofit.create(IAPI.class);

    }

    public interface IAPI {
        @GET("/bins/upt7z")
        Call<GetPhotoResponce> getJson();
    }

    public class GetPhoto {
        public String title;
        public String description;
        public String url;
        public int id;
        public double latitude;
        public double longitude;
    }

    public class GetPhotoResponce{
        public ArrayList<GetPhoto> photos;
    }

    public static IAPI getIapi(){
        return iapi;
    }
}
