package com.example.stanislau_bushuk.epamtest.Task1;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask1;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.Modele.Element;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstTaskFragment extends Fragment implements AdapterView.OnItemClickListener {


    private ArrayList<Element> arrayList;
    private Context context;


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
        ListViewAdapterTask1 adapter = new ListViewAdapterTask1(context, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    public void setElements() {
        for (int i = 0; i < 20; i++) {
            arrayList.add(new Element("Item " + (i+1), UUID.randomUUID().toString(), "This is item " + (i+1)));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        lookItem(arrayList.get(position).getName(),arrayList.get(position).getDescription());
    }

    public void lookItem(String title, String subtitle){
        Intent intent=new Intent(context,ListenerItemFirstTaskActivity.class);
        intent.putExtra("Title",title);
        intent.putExtra("Subtitle",subtitle);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}
