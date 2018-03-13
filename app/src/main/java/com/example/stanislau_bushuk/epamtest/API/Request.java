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

    public void getJson(@NonNull final IJsonReady jsonReady) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                okhttp3.Request original = chain.request();
                okhttp3.Request request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IAPI iapi = retrofit.create(IAPI.class);
        Call<GetPhotoResponce> call;
        call = iapi.getJson();
        call.enqueue(new Callback<GetPhotoResponce>() {
            @Override
            public void onResponse(@NonNull Call<GetPhotoResponce> call, @NonNull retrofit2.Response<GetPhotoResponce> response) {
                if (response.body() !=null) {
                    Timber.e("responce");
                    jsonReady.onJsonReady(response.body().photos);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetPhotoResponce> call, @NonNull Throwable t) {
                Timber.e("Fail");
                jsonReady.onJsonError(t);

            }
        });

    }

    public interface IAPI {
        @GET("/bins/upt7z")
        Call<GetPhotoResponce> getJson();
    }

    public interface IJsonReady {
        void onJsonReady(ArrayList<GetPhoto> arr);

        void onJsonError(Throwable t);
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
}
