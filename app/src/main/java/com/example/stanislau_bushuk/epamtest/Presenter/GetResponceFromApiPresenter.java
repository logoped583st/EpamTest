package com.example.stanislau_bushuk.epamtest.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;

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


    public GetResponceFromApiPresenter() {
        realm = Realm.getDefaultInstance();
        getResponce();
    }

    public void getResponce() {
        listPhotoRealm = realm.where(ListPhotoRealm.class).findFirst();

        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(Call<ListPhotoRealm> call, Response<ListPhotoRealm> response) {
                realm.beginTransaction();
                realm.where(ListPhotoRealm.class).findAll().deleteAllFromRealm();

                listPhotoRealm = realm.createObject(ListPhotoRealm.class);
                for (PhotoRealm photo : response.body().getPhotos()) {
                    listPhotoRealm.getPhotos().add(realm.copyToRealm(new PhotoRealm(photo.getTitle(), photo.getDescription(),
                            photo.getUrl(), photo.getId(), photo.getLatitude(), photo.getLongitude())));
                }
                Timber.e("SizeRealm%s", String.valueOf(realm.where(ListPhotoRealm.class).findAll()));
                realm.commitTransaction();
                getViewState().getResponce(listPhotoRealm);
            }

            @Override
            public void onFailure(Call<ListPhotoRealm> call, Throwable t) {
                Timber.e("RESPONCEFAIL");
                Timber.e("SizeRealm%s", String.valueOf(realm.where(ListPhotoRealm.class).findAll()));
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                if (realmResults != null) {
                    if (!realmResults.getPhotos().isEmpty()) {
                        getViewState().getResponce(realmResults);
                    }
                }
            }
        });
    }
}
