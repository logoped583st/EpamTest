package com.example.stanislau_bushuk.epamtest.Task3;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask3;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdTaskFragment extends Fragment {


    private RecyclerView recyclerView;
    private ListViewAdapterTask3 adapter;
    private View view;
    private ImageView errorImage;
    private Realm realm;
    private ListPhotoRealm listPhotosRealm;


    public ThirdTaskFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRealm();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);
        listPhotosRealm = realm.where(ListPhotoRealm.class).findFirst();
        getResponse();
    }


    public void getResponse() {
        com.example.stanislau_bushuk.epamtest.API.Request request = new com.example.stanislau_bushuk.epamtest.API.Request();
        request.getJson(new Request.IJsonReady() {
            @Override
            public void onJsonReady(ArrayList<com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto> arr) {
                realm.beginTransaction();
                listPhotosRealm = realm.createObject(ListPhotoRealm.class);
                for (com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto photo : arr) {
                    listPhotosRealm.getPhotosFromRealm().add(realm.copyToRealm(new PhotoRealm(photo.title, photo.description, photo.url, photo.id, photo.latitude, photo.longitude)));
                }

                Timber.e("%ssize", String.valueOf(listPhotosRealm.getPhotosFromRealm().size()));
                realm.commitTransaction();
                recyclerView.setLayoutManager(new GridLayoutManager(App.context, 3));
                adapter = new ListViewAdapterTask3(App.context, listPhotosRealm);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onJsonError(Throwable t) {
                t.printStackTrace();
                realm.beginTransaction();
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                if (realmResults != null) {
                    if (!realmResults.getPhotosFromRealm().isEmpty()) {
                        Timber.e(String.valueOf(realmResults.getPhotosFromRealm().size()));
                        recyclerView.setLayoutManager(new GridLayoutManager(App.context, 3));
                        adapter = new ListViewAdapterTask3(App.context, realmResults);
                        recyclerView.setAdapter(adapter);
                    } else {
                        errorImage.setImageResource(R.drawable.eror);
                    }
                }
            }
        });
    }

    public void setRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realm.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
    }


}
