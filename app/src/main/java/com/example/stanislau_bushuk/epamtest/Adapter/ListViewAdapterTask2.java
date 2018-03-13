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
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task2.ListenerItemSecondTaskActivity;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by Stanislau_Bushuk on 3/7/2018.
 */

public class ListViewAdapterTask2 extends RecyclerView.Adapter<ListViewAdapterTask2.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Request.GetPhoto> arrayList;
    private Context context;


    public ListViewAdapterTask2(Context context, ArrayList<Request.GetPhoto> getPhoto) {
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = getPhoto;
        this.context=context;
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
        holder.countryName.setText(arrayList.get(position).title);
        GlideApp.with(context)
                .load(arrayList.get(position).url)
                .error(R.drawable.ic_menu_gallery)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.countryPhoto);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LinearLayout linearLayout;
        TextView countryName;
        ImageView countryPhoto;


        ViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.list_item);
            countryName=itemView.findViewById(R.id.countryName);
            countryPhoto=itemView.findViewById(R.id.imageCountry);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Timber.e("testClick");
            Intent intent = new Intent(context,ListenerItemSecondTaskActivity.class);
            Toast.makeText(context,arrayList.get((int) view.getTag()).url,Toast.LENGTH_LONG).show();
            intent.putExtra("URL",arrayList.get((int) view.getTag()).url);
            intent.putExtra("TITLE",arrayList.get((int) view.getTag()).title);
            context.startActivity(intent);
        }

    }
}
