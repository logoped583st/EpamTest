package com.example.stanislau_bushuk.epamtest.Task2;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask2;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondTaskFragment extends Fragment {


    private ArrayList<Request.GetPhoto> arrayPhoto;
    private RecyclerView recyclerView;
    private ListViewAdapterTask2 adapter;
    private View view;
    private ImageView errorImage;
    private Context context;


    public SecondTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part2));
        recyclerView = view.findViewById(R.id.list);
        errorImage=view.findViewById(R.id.ErrorImage);
        arrayPhoto = new ArrayList<>();
        getResponse();
    }


    public void getResponse() {
        Request.getIapi().getJson().enqueue(new Callback<Request.GetPhotoResponce>() {
            @Override
            public void onResponse(@NonNull Call<Request.GetPhotoResponce> call, @NonNull Response<Request.GetPhotoResponce> response) {
                Timber.e("SUKA");
                arrayPhoto.addAll(response.body().photos);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                adapter = new ListViewAdapterTask2(context, arrayPhoto);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<Request.GetPhotoResponce> call, @NonNull Throwable t) {
                t.printStackTrace();
                Timber.e(t.getMessage());
                errorImage.setImageResource(R.drawable.eror);

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
