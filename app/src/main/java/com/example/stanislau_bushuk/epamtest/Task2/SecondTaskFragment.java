package com.example.stanislau_bushuk.epamtest.Task2;


import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask2;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondTaskFragment extends Fragment {


    private ArrayList<com.example.stanislau_bushuk.epamtest.API.Request.GetPhoto> arrayPhoto;
    private RecyclerView recyclerView;
    private ListViewAdapterTask2 adapter;
    private View view;
    private ImageView errorImage;


    public SecondTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        errorImage=view.findViewById(R.id.ErrorImage);
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
                recyclerView.setLayoutManager(new GridLayoutManager(App.context,3));
                adapter = new ListViewAdapterTask2(App.context, arrayPhoto);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onJsonError(Throwable t) {
                t.printStackTrace();
                errorImage.setImageResource(R.drawable.eror);
            }
        });
    }

}
