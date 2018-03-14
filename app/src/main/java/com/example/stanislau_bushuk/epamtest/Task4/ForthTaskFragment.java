package com.example.stanislau_bushuk.epamtest.Task4;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForthTaskFragment extends Fragment implements OnMapReadyCallback {

    private Realm realm;
    private ListPhotoRealm listPhotosRealm;
    private GoogleMap gmap;
    private View view;
    private MapView mapView;
    private Context context;


    public ForthTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mapfragment, container, false);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        MapsInitializer.initialize(context);
        mapView.getMapAsync(this);
        setRealm();
        getResponse();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(((AppCompatActivity)getActivity()).getSupportActionBar()!=null)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part4));
    }

    public void getResponse() {
        Request.getIapi().getJson().enqueue(new Callback<Request.GetPhotoResponce>() {
            @Override
            public void onResponse(@NonNull Call<Request.GetPhotoResponce> call, @NonNull Response<Request.GetPhotoResponce> response) {
                realm.beginTransaction();
                listPhotosRealm = realm.createObject(ListPhotoRealm.class);
                if(response.body()!=null) {
                    for (Request.GetPhoto photo : response.body().photos) {
                        listPhotosRealm.getPhotosFromRealm().add(realm.copyToRealm(new PhotoRealm(photo.title, photo.description, photo.url, photo.id, photo.latitude, photo.longitude)));
                    }
                    Timber.e("%ssize", String.valueOf(listPhotosRealm.getPhotosFromRealm().size()));
                    realm.commitTransaction();
                    for (final PhotoRealm photoRealm : listPhotosRealm.getPhotosFromRealm()) {

                        GlideApp.with(context)
                                .asBitmap()
                                .load(photoRealm.getUrl())
                                .error(R.drawable.eror)
                                .into(new SimpleTarget<Bitmap>(75, 100) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        gmap.addMarker(new MarkerOptions().position(new LatLng(photoRealm.getLatitude(), photoRealm.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(resource)));
                                    }
                                });
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Request.GetPhotoResponce> call, @NonNull Throwable t) {
                t.printStackTrace();
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                if (!realmResults.getPhotosFromRealm().isEmpty()) {
                    Timber.e(String.valueOf(realmResults.getPhotosFromRealm().size()));
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
        mapView.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
