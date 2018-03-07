package com.example.stanislau_bushuk.epamtest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by logoped583st on 7.3.18.
 */

public class ListVIewAdapter extends BaseAdapter {

    private ArrayList<Element> arrayList;

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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
