package com.example.stanislau_bushuk.epamtest.Task4;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.stanislau_bushuk.epamtest.API.Request;
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

import io.realm.Realm;
import io.realm.RealmList;
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
        MapsInitializer.initialize(getActivity());
        mapView.getMapAsync(this);
        realm = Realm.getDefaultInstance();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part4));
    }

    public void getResponse() {
        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(@NonNull Call<ListPhotoRealm> call, @NonNull Response<ListPhotoRealm> response) {
                realm.beginTransaction();
                listPhotosRealm = realm.createObject(ListPhotoRealm.class);
                if (response.body() != null) {
                    for (PhotoRealm photo : response.body().getPhotos()) {
                        listPhotosRealm.getPhotos().add(realm.copyToRealm(new PhotoRealm(photo.getTitle(), photo.getDescription(),
                                photo.getUrl(), photo.getId(), photo.getLatitude(), photo.getLongitude())));
                    }
                    Timber.e("%ssize", String.valueOf(listPhotosRealm.getPhotos().size()));
                    realm.commitTransaction();
                    for (final PhotoRealm photoRealm : listPhotosRealm.getPhotos()) {

                        GlideApp.with(getActivity())
                                .asBitmap()
                                .load(photoRealm.getUrl())
                                .error(R.drawable.eror)
                                .fitCenter()
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
            public void onFailure(@NonNull Call<ListPhotoRealm> call, @NonNull Throwable t) {
                t.printStackTrace();
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                try {
                    Timber.e(String.valueOf(realmResults.getPhotos().size()));
                    final RealmList<PhotoRealm> photoRealm = realmResults.getPhotos();
                    for (final PhotoRealm photoRealm1 : photoRealm)
                        GlideApp.with(getActivity())
                                .asBitmap()
                                .load(photoRealm1.getUrl())
                                .error(R.drawable.eror)
                                .fitCenter()
                                .into(new SimpleTarget<Bitmap>(75, 100) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        gmap.addMarker(new MarkerOptions().position(new LatLng(photoRealm1.getLatitude(), photoRealm1.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(resource)));
                                    }
                                });

                } catch (NullPointerException exeption) {
                    exeption.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        realm.close();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        getResponse();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
