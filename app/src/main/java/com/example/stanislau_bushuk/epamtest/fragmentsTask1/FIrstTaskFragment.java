package com.example.stanislau_bushuk.epamtest.fragmentsTask1;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask1;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Module.Element;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstTaskFragment extends Fragment implements AdapterView.OnItemClickListener {


    private ArrayList<Element> arrayList;
    private ListenItemFirstTask fragment;


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
        arrayList = new ArrayList<Element>();
        setElements();
        ListViewAdapterTask1 adapter = new ListViewAdapterTask1(App.context, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    public void setElements() {
        for (int i = 0; i < 20; i++) {
            arrayList.add(new Element("Item " + i, UUID.randomUUID().toString(), "Item is" + i));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setFragment(arrayList.get(position).getName(),arrayList.get(position).getDescription());
        arrayList.get(position);
    }

    public Fragment setFragment(String title, String subtitle){
        fragment=new ListenItemFirstTask();
        Bundle bundle = new Bundle();
        bundle.putString("Title",title);
        bundle.putString("Subtitle",subtitle);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().add(R.id.fragmentContainer,fragment).addToBackStack(null);
        fragmentTransaction.commit();
        return fragment;
    }
}
