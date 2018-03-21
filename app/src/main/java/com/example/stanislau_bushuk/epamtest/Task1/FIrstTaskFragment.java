package com.example.stanislau_bushuk.epamtest.Task1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask1;
import com.example.stanislau_bushuk.epamtest.Modele.Element;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;
import java.util.UUID;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstTaskFragment extends Fragment {


    private ArrayList<Element> arrayList;
    private RecyclerView recyclerView;
    private ListViewAdapterTask1 adapter;


    public FIrstTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_first_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part1));
        arrayList = new ArrayList<>();
        setElements();
        recyclerView = view.findViewById(R.id.list1);
        adapter = new ListViewAdapterTask1(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Timber.e("view created");
    }

    public void setElements() {
        for (int i = 0; i < 20; i++) {
            arrayList.add(new Element(getResources().getString(R.string.item) + " " + (i+1), UUID.randomUUID().toString(), "This is item " + (i+1)));
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
