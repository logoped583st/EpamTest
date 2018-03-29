package com.example.stanislau_bushuk.epamtest.Task3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task3.Adapter.ListViewAdapterTask3;
import com.example.stanislau_bushuk.epamtest.Task3.IView.GetResponceFromApi;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotosObj;
import com.example.stanislau_bushuk.epamtest.Task3.Presenter.GetResponseFromApiPresenter;
import com.example.stanislau_bushuk.epamtest.Task4.ForthTaskFragment;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class FragmentMoxy extends MvpAppCompatFragment implements GetResponceFromApi, View.OnClickListener {

    @InjectPresenter(type = PresenterType.LOCAL)
    GetResponseFromApiPresenter getResponceFromApiPresenter;


    private ListViewAdapterTask3 adapter;
    private ImageView errorImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.e("Created");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.e("View Created");
        if (((AppCompatActivity) getActivity() != null) && ((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part3));
        RecyclerView recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);
        ArrayList<PhotosObj> photoRealmList = new ArrayList<>();
        adapter = new ListViewAdapterTask3(getActivity(), photoRealmList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);
    }


    @Override
    public void getResponce(List<PhotosObj> photoRealmArrayList) {
        //результат с апи
        Timber.e("getResponse from Api");
        adapter.update(photoRealmArrayList);
        errorImage.setVisibility(View.INVISIBLE);
    }


    @Override
    public void getResponseFromRealmInFail() {
        //пустой реалм
        Timber.e("FAIL");
        Timber.e("SetAnother Flowable  " + Thread.currentThread().toString());
        errorImage.setImageResource(R.drawable.eror);
        errorImage.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton: {
                FragmentTransaction fragmentTransaction;
                if (getActivity() != null) {
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contaier, new ForthTaskFragment());
                    fragmentTransaction.commit();
                }
                break;
            }
        }
    }


}
