package com.example.stanislau_bushuk.epamtest.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.IView.getResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */
@InjectViewState
public class GetResponceFromApiPresenter extends MvpPresenter<getResponceFromApi> {
    private ListPhotoRealm listPhotoRealm;

    public GetResponceFromApiPresenter(){
        getResponce();
    }

    public ListPhotoRealm getResponce(){

        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(Call<ListPhotoRealm> call, Response<ListPhotoRealm> response) {
                Timber.e("RESPONCE:%s", response.body().toString());
                listPhotoRealm=response.body();
                getViewState().getResponce(listPhotoRealm);
            }

            @Override
            public void onFailure(Call<ListPhotoRealm> call, Throwable t) {
                Timber.e("RESPONCEFAIL");
            }
        });
        return listPhotoRealm;
    }
}
