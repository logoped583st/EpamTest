package com.example.stanislau_bushuk.epamtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask3;
import com.example.stanislau_bushuk.epamtest.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.Presenter.GetResponceFromApiPresenter;
import com.example.stanislau_bushuk.epamtest.Presenter.SetActionBarPresenter;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class FragmentMoxy extends MvpAppCompatFragment implements GetResponceFromApi {

    @InjectPresenter
    GetResponceFromApiPresenter getResponceFromApiPresenter;

    private RecyclerView recyclerView;
    private ListViewAdapterTask3 adapter;
    private ImageView errorImage;
    private View view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.e("Created");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.e("View Created");

        recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);

    }
    @Override
    public void getResponce(ListPhotoRealm listPhotoRealm) {
        if (!listPhotoRealm.getPhotos().isEmpty()) {
            Timber.e("RESPONCE %s", listPhotoRealm.getPhotos().size());
            adapter = new ListViewAdapterTask3(getActivity(), listPhotoRealm);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerView.setAdapter(adapter);
        }else{
            errorImage.setImageResource(R.drawable.eror);
        }
    }
}
