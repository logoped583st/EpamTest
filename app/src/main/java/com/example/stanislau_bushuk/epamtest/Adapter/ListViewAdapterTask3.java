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
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task2.ListenerItemSecondTaskActivity;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class ListViewAdapterTask3 extends RecyclerView.Adapter<ListViewAdapterTask3.ViewHolder> {

    private LayoutInflater mInflater;
    private ListPhotoRealm arrayList;
    private Context context;
    private PhotoRealm photo;


    public ListViewAdapterTask3(Context context, ListPhotoRealm getPhoto) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = getPhoto;
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
        photo = arrayList.getPhotosFromRealm().get(position);
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
        return arrayList.getPhotosFromRealm().size();
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
            if (arrayList.getPhotosFromRealm() != null) {
                intent.putExtra("URL", (arrayList.getPhotosFromRealm().get((int) view.getTag()).getUrl()));
                intent.putExtra("TITLE", (arrayList.getPhotosFromRealm().get((int) view.getTag()).getTitle()));
                context.startActivity(intent);
            }
        }
    }
}
