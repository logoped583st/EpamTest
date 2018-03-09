package com.example.stanislau_bushuk.epamtest.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.R;

import java.util.ArrayList;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class ListViewAdapterTask2 extends BaseAdapter {

    private ArrayList<Request.GetPhoto> arrayList;
    private Context myContext;

    public ListViewAdapterTask2(Context context, ArrayList<Request.GetPhoto> elements) {
        arrayList = elements;
        myContext = context;

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

    Request.GetPhoto getPhoto(int position) {
        return ((Request.GetPhoto) getItem(position));
    }

    static class ViewHolder {
        TextView countryName;
        ImageView imageCountry;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) myContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.list_item_second_task, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.countryName = view.findViewById(R.id.countryName);
            viewHolder.imageCountry = view.findViewById(R.id.imageCountry);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Request.GetPhoto getPhoto = getPhoto(position);


        viewHolder.countryName.setText(getPhoto.title);
        GlideApp.with(myContext)
                .load(getPhoto.url)
                .error(R.drawable.ic_menu_gallery)
                .into(viewHolder.imageCountry);

        return view;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
