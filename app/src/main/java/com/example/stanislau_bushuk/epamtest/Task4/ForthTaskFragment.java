package com.example.stanislau_bushuk.epamtest.Task4;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task3.FragmentMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotoRealmMoxy;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponceFromApiPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import io.realm.Realm;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForthTaskFragment extends FragmentMoxy implements OnMapReadyCallback, GetResponceFromApi {

    @InjectPresenter(type = PresenterType.LOCAL)
    GetResponceFromApiPresenter getResponceFromApiPresenter;

    private ArrayList<PhotoRealmMoxy> photoRealmMoxies;
    private GoogleMap gmap;
    private MapView mapView;


    public ForthTaskFragment() {
        photoRealmMoxies = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapfragment, container, false);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        MapsInitializer.initialize(getActivity());
        mapView.getMapAsync(this);
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part4));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

    }

    public void setMarkers() {
        for (final PhotoRealmMoxy photoRealmMoxy : photoRealmMoxies) {
            try {
                GlideApp.with(getActivity())
                        .asBitmap()
                        .load(photoRealmMoxy.getUrl())
                        .error(R.drawable.eror)
                        .fitCenter()
                        .into(new SimpleTarget<Bitmap>(75, 100) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                gmap.addMarker(new MarkerOptions().position(new LatLng(photoRealmMoxy.getLatitude(), photoRealmMoxy.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(resource)));
                            }
                        });
            } catch (NullPointerException exeption) {
                exeption.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void getResponce(ArrayList<PhotoRealmMoxy> listPhotoRealm) {
        photoRealmMoxies.addAll(listPhotoRealm);
        Timber.e("responce");
        Timber.e(String.valueOf(photoRealmMoxies.size()));
        if (gmap != null)
            setMarkers();
    }

    @Override
    public void getResponseFromRealmInFail() {
        Timber.e("Responce Fail");
    }
}
