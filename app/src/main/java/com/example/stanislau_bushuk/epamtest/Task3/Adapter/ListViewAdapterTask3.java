package com.example.stanislau_bushuk.epamtest.Task3.Adapter;

import android.content.Context;
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
import com.example.stanislau_bushuk.epamtest.Task3.Modele.PhotoRealmMoxy;

import java.util.ArrayList;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class ListViewAdapterTask3 extends RecyclerView.Adapter<ListViewAdapterTask3.ViewHolder> {


    private LayoutInflater mInflater;
    private ArrayList<PhotoRealmMoxy> arrayList;
    private Context context;


    public ListViewAdapterTask3(Context context, @NonNull ArrayList<PhotoRealmMoxy> getPhoto) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = getPhoto;
    }

    public void update(ArrayList<PhotoRealmMoxy> photoRealms) {
        this.arrayList.clear();
        this.arrayList.addAll(photoRealms);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewAdapterTask3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_second_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterTask3.ViewHolder holder, int position) {
        holder.linearLayout.setTag(position);
        PhotoRealmMoxy photo = arrayList.get(position);
        if (photo != null) {
            holder.countryName.setText(photo.getTitle());
            GlideApp.with(context)
                    .load(photo.getUrl())
                    .fitCenter()
                    .error(R.drawable.eror)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.countryPhoto);
        }
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryPhoto;
        private LinearLayout linearLayout;


        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list_item);
            countryName = itemView.findViewById(R.id.countryName);
            countryPhoto = itemView.findViewById(R.id.imageCountry);
        }

    }
}
