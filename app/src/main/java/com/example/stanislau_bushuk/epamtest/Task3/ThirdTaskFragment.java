package com.example.stanislau_bushuk.epamtest.Task3;


import android.app.Fragment;
import android.content.Context;
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


    private ArrayList<com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto> arrayPhoto;
    private RecyclerView recyclerView;
    private ListViewAdapterTask3 adapter;
    private View view;
    private ImageView errorImage;
    private Realm realm;
    private ListPhotoRealm listPhotosRealm;
    private PhotoRealm photoRealms;
    private Context context;

    public ThirdTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);
        arrayPhoto = new ArrayList<>();
        setRealm();
        listPhotosRealm = realm.where(ListPhotoRealm.class).findFirst();
        getResponse();
    }


    public void getResponse() {
        Request request = new Request();
        request.getJson(new Request.IJsonReady() {
            @Override
            public void onJsonReady(ArrayList<Request.GetPhoto> arr) {
                //arrayPhoto.addAll(arr);
                realm.beginTransaction();

                listPhotosRealm = realm.createObject(ListPhotoRealm.class);


                for (Request.GetPhoto photo : arr) {
                    photoRealms = new PhotoRealm(photo.title, photo.description, photo.url, photo.id, photo.latitude, photo.longitude);

                    listPhotosRealm.getPhotosFromRealm().add(realm.copyToRealm(new PhotoRealm(photo.title, photo.description, photo.url, photo.id, photo.latitude, photo.longitude)));
                }

                Timber.e("%ssize", String.valueOf(listPhotosRealm.getPhotosFromRealm().size()));
                realm.commitTransaction();
                recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                adapter = new ListViewAdapterTask3(context, listPhotosRealm);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onJsonError(Throwable t) {
                t.printStackTrace();
                realm.beginTransaction();
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                if (!realmResults.getPhotosFromRealm().isEmpty()) {
                    Timber.e(String.valueOf(realmResults.getPhotosFromRealm().size()));
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                    adapter = new ListViewAdapterTask3(context, realmResults);
                    recyclerView.setAdapter(adapter);
                } else {
                    errorImage.setImageResource(R.drawable.eror);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}
