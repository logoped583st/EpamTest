package com.example.stanislau_bushuk.epamtest.Task4;

import android.app.Activity;
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
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotosObj;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForthTaskFragment extends FragmentMoxy implements OnMapReadyCallback, GetResponceFromApi {

    @InjectPresenter(type = PresenterType.LOCAL)
    GetResponseFromApiPresenter getResponceFromApiPresenter;

    private ArrayList<PhotosObj> photoRealmMoxies;
    private GoogleMap gmap;
    private MapView mapView;
    private Activity activity;


    public ForthTaskFragment() {
        photoRealmMoxies = new ArrayList<>();

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapfragment, container, false);

        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        if (getActivity() != null) {
            activity = getActivity();
            MapsInitializer.initialize(activity);
            NavigationView navigationView = activity.findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(3).setChecked(true);
        }
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) activity).getSupportActionBar().setTitle(getResources().getString(R.string.Part4));
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
        Timber.e("setMarkers");
        for (final PhotosObj photoRealmMoxy : photoRealmMoxies) {

            try {
                GlideApp.with(getActivity())
                        .asBitmap()
                        .load(photoRealmMoxy.getUrl())
                        .error(R.drawable.eror)
                        .fitCenter()
                        .into(new SimpleTarget<Bitmap>(100, 150) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                gmap.addMarker(new MarkerOptions().position(new LatLng(photoRealmMoxy.getLatitude(),
                                        photoRealmMoxy.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(resource)));
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
    public void getResponce(List<PhotosObj> listPhotoRealm) {
        photoRealmMoxies.addAll(listPhotoRealm);
        Timber.e("responce");
        Timber.e(String.valueOf(photoRealmMoxies.size()));
        setMarkers();
    }

    @Override
    public void getResponseFromRealmInFail() {
        Timber.e("Responce Fail");
    }

}
