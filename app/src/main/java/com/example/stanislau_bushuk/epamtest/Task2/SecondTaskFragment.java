package com.example.stanislau_bushuk.epamtest.Task2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.stanislau_bushuk.epamtest.Task3.Modele.Photos;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotosObj;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.example.stanislau_bushuk.epamtest.R.layout.fragment_second_task;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondTaskFragment extends Fragment {


    private RealmList<PhotosObj> arrayPhoto;
    private RecyclerView recyclerView;
    private ListViewAdapterTask2 adapter;
    private ImageView errorImage;


    public SecondTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(fragment_second_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((AppCompatActivity) getActivity())!= null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part2));
        recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);
        arrayPhoto = new RealmList<>();
        adapter = new ListViewAdapterTask2(getActivity(), arrayPhoto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        getResponse();
    }


    public void getResponse() {
        Request.getIapi().getJson().enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(@NonNull Call<Photos> call, @NonNull Response<Photos> response) {
                Timber.e(String.valueOf(response.body().getPhotos().size()));
                arrayPhoto.addAll(response.body().getPhotos());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<Photos> call, @NonNull Throwable t) {
                t.printStackTrace();
                Timber.e(t.getMessage());
                errorImage.setImageResource(R.drawable.eror);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
