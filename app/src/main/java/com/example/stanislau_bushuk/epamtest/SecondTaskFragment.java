package com.example.stanislau_bushuk.epamtest;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask2;

import java.util.ArrayList;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondTaskFragment extends Fragment {


    private ArrayList<com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto> arrayPhoto;
    private GridView gridView;
    private ListViewAdapterTask2 adapter;


    public SecondTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_second_task, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.list);
        arrayPhoto = new ArrayList<>();
        getResponse();
    }


    public void getResponse() {
        com.example.stanislau_bushuk.epamtest.API.Request request = new com.example.stanislau_bushuk.epamtest.API.Request();
        request.getJson(new com.example.stanislau_bushuk.epamtest.API.Request.IJsonReady() {
            @Override
            public void onJsonReady(ArrayList<com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto> arr) {
                arrayPhoto.addAll(arr);
                for (com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto photo : arr) {
                    Timber.e(photo.url);
                }
                adapter = new ListViewAdapterTask2(App.context, arrayPhoto);
                gridView.setAdapter(adapter);
            }
        });
    }
}
