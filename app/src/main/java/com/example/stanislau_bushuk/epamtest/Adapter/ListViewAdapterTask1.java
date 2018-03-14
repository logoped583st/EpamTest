package com.example.stanislau_bushuk.epamtest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.Modele.Element;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;

/**
 * Created by logoped583st on 7.3.18.
 */

public class ListViewAdapterTask1 extends BaseAdapter{

    private ArrayList<Element> arrayList;
    private Context myContext;
    private View view;

    public ListViewAdapterTask1(Context context, ArrayList<Element> elements){
        arrayList=elements;
        myContext=context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Element getElement(int position) {
        return ((Element) getItem(position));
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) myContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view==null && mInflater!=null ) {
            view = mInflater.inflate(R.layout.list_item_first_task, viewGroup, false);
            init(view,position);
        }
        return view;
    }

    public void init(View view, int position){
        Element element=getElement(position);
        TextView name = view.findViewById(R.id.NAME);
        TextView description = view.findViewById(R.id.DESCRIPTION);
        name.setText(element.getName());
        description.setText(element.getDescription());
    }

}
