package com.example.stanislau_bushuk.epamtest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.Module.Photo;
import com.example.stanislau_bushuk.epamtest.R;


import java.net.URL;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class ListViewAdapterTask2 extends BaseAdapter {

    private ArrayList<Photo> arrayList;
    private Context myContext;

    public ListViewAdapterTask2(Context context, ArrayList<Photo> elements){
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

    public Photo getProduct(int position) {
        return ((Photo) getItem(position));
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) myContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view==null) {
            view = mInflater.inflate(R.layout.list_item_second_task, viewGroup, false);
        }
        Photo photo=getProduct(position);
        TextView textView=view.findViewById(R.id.countryName);
        final ImageView imageView=view.findViewById(R.id.imageCountry);
        Timber.e(photo.getUrl());
        textView.setText(photo.getName());

        com.example.stanislau_bushuk.epamtest.GlideApp.with(myContext)
                .asBitmap()
                .load(photo.getUrl())
                .centerCrop()
                .error(R.drawable.ic_menu_gallery)
                .into(imageView);
                /*.into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }
                })*/;


        return view;
    }

}
