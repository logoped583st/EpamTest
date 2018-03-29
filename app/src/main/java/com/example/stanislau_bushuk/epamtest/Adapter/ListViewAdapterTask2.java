package com.example.stanislau_bushuk.epamtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.stanislau_bushuk.epamtest.GlideApp;

import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task2.ListenerItemSecondTaskActivity;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.Photos;
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotosObj;

import io.realm.RealmList;


public class ListViewAdapterTask2 extends RecyclerView.Adapter<ListViewAdapterTask2.ViewHolder> {

    private LayoutInflater mInflater;
    private RealmList<PhotosObj> arrayList;
    private Context context;


    public ListViewAdapterTask2(Context context, RealmList<PhotosObj> getPhoto) {
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = getPhoto;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewAdapterTask2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_second_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterTask2.ViewHolder holder, int position) {
        holder.linearLayout.setTag(position);
        holder.countryName.setText(arrayList.get(position).getTitle());
        GlideApp.with(context)
                .load(arrayList.get(position).getUrl())
                .fitCenter()
                .error(R.drawable.eror)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.countryPhoto);

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView countryName;
        ImageView countryPhoto;
        private LinearLayout linearLayout;


        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list_item);
            countryName = itemView.findViewById(R.id.countryName);
            countryPhoto = itemView.findViewById(R.id.imageCountry);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ListenerItemSecondTaskActivity.class);
            intent.putExtra("URL", arrayList.get((int) view.getTag()).getUrl());
            intent.putExtra("TITLE", arrayList.get((int) view.getTag()).getTitle());
            context.startActivity(intent);
        }
    }
}
