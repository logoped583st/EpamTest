package com.example.stanislau_bushuk.epamtest.Presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

@InjectViewState
public class GetResponceFromApiPresenter extends MvpPresenter<GetResponceFromApi> {
    private ListPhotoRealm listPhotoRealm;
    private Realm realm;
    private ArrayList<PhotoRealm> tempListPhotoRealm;


    public GetResponceFromApiPresenter() {
        realm = Realm.getDefaultInstance();
        initTempListPhotoRealm();
        getResponce();
    }

    public void getResponce() {
        listPhotoRealm = realm.where(ListPhotoRealm.class).findFirst();

        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(@NonNull Call<ListPhotoRealm> call, @NonNull Response<ListPhotoRealm> response) {
                realm.beginTransaction();
                realm.where(ListPhotoRealm.class).findAll().deleteAllFromRealm();

                listPhotoRealm = realm.createObject(ListPhotoRealm.class);
                for (PhotoRealm photo : response.body().getPhotos()) {
                    listPhotoRealm.getPhotos().add(realm.copyToRealm(new PhotoRealm(photo.getTitle(), photo.getDescription(),
                            photo.getUrl(), photo.getId(), photo.getLatitude(), photo.getLongitude())));
                }
                Timber.e("SizeRealm%s", String.valueOf(realm.where(ListPhotoRealm.class).findAll()));
                realm.commitTransaction();
                checkResponce(listPhotoRealm);
            }

            @Override
            public void onFailure(@NonNull Call<ListPhotoRealm> call, @NonNull Throwable t) {
                Timber.e("RESPONCEFAIL");
            }
        });
    }

    public void initTempListPhotoRealm(){
        tempListPhotoRealm=new ArrayList<PhotoRealm>();
        try {
            tempListPhotoRealm.addAll(realm.where(ListPhotoRealm.class).findFirst().getPhotos());
            getViewState().getResponceFromRealm(tempListPhotoRealm);

        }catch (NullPointerException exception){
            exception.printStackTrace();
            getViewState().getResponseFromRealmInFail();
        }
    }

    public void checkResponce(ListPhotoRealm listPhotoRealm){
        Timber.e("RESPONCE %s", listPhotoRealm.getPhotos().size());
        ArrayList<PhotoRealm> responcePhotoRealm = new ArrayList<>();
        responcePhotoRealm.addAll(listPhotoRealm.getPhotos());
        if (!tempListPhotoRealm.isEmpty()) {
            if(tempListPhotoRealm.size()==responcePhotoRealm.size()){
                for(int i=0;i<tempListPhotoRealm.size();i++){
                    if(tempListPhotoRealm.get(i).getId()!=responcePhotoRealm.get(i).getId()){
                        tempListPhotoRealm.clear();
                        tempListPhotoRealm.addAll(responcePhotoRealm);
                        getViewState().getResponce(tempListPhotoRealm);
                        break;
                    }
                }
            }
            else {
                tempListPhotoRealm.clear();
                tempListPhotoRealm.addAll(responcePhotoRealm);
                getViewState().getResponce(tempListPhotoRealm);
                Timber.e("notify size");
            }
        }else {
            tempListPhotoRealm.clear();
            tempListPhotoRealm.addAll(responcePhotoRealm);
            getViewState().getResponce(tempListPhotoRealm);
            Timber.e("notify null");
        }
    }
}
