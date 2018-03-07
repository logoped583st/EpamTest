package com.example.stanislau_bushuk.epamtest;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListVIewAdapter;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstTaskFragment extends Fragment {


    private ArrayList<Element> arrayList;


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
        ListView listView = view.findViewById(R.id.list);
        arrayList=new ArrayList<Element>();
        setElements();
        ListVIewAdapter adapter = new ListVIewAdapter(getActivity().getBaseContext(), arrayList);
        listView.setAdapter(adapter);

    }
    public void setElements(){
        for (int i=0;i<20;i++) {
            arrayList.add(new Element("Item "+i, UUID.randomUUID().toString(),"Item is"+i));
        }
    }
}
