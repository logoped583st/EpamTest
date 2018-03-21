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
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.Presenter.GetResponceFromApiPresenter;

import java.util.ArrayList;

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
    private ArrayList<PhotoRealm> photoRealmList;
    private Realm realm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
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
        photoRealmList = new ArrayList<>();
        adapter = new ListViewAdapterTask3(getActivity(), photoRealmList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void getResponce(ArrayList<PhotoRealm> photoRealmArrayList) {
        //результат с апи
        Timber.e("getResponce from Api");
        adapter.update(photoRealmArrayList);
        errorImage.setVisibility(View.INVISIBLE);

    }

    @Override
    public void getResponceFromRealm(ArrayList<PhotoRealm> photoRealmArrayList) {
        Timber.e("Result from realm");
        adapter.update(photoRealmArrayList);
        errorImage.setVisibility(View.INVISIBLE);
        //результат с реалма
    }

    @Override
    public void getResponseFromRealmInFail() {
        //пустой реалм
        Timber.e("FAIL");
        errorImage.setImageResource(R.drawable.eror);
        errorImage.setVisibility(View.VISIBLE);
    }


}
