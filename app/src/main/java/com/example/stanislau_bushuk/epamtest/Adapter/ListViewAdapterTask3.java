package com.example.stanislau_bushuk.epamtest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.IView.SetAdapterView;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.Presenter.GetResponceFromApiPresenter;
import com.example.stanislau_bushuk.epamtest.Presenter.SetAdapterPresenter;
import com.example.stanislau_bushuk.epamtest.R;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public class ListViewAdapterTask3 extends RecyclerView.Adapter<ListViewAdapterTask3.ViewHolder> {

    private LayoutInflater mInflater;
    private ListPhotoRealm arrayList;
    private Context context;
    private PhotoRealm photo;
    private ViewHolder viewHolder;



    public ListViewAdapterTask3(Context context, @NonNull ListPhotoRealm getPhoto) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = getPhoto;
    }

    @NonNull
    @Override
    public ListViewAdapterTask3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_item_second_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterTask3.ViewHolder holder, int position) {
        holder.linearLayout.setTag(position);
        photo = arrayList.getPhotos().get(position);
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return arrayList.getPhotos().size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements SetAdapterView  {
        TextView countryName;
        ImageView countryPhoto;
        private LinearLayout linearLayout;
        @InjectPresenter
        SetAdapterPresenter setAdapterPresenter;


        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list_item);
            countryName = itemView.findViewById(R.id.countryName);
            countryPhoto = itemView.findViewById(R.id.imageCountry);
        }

        @Override
        public void setImage(String url) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .error(R.drawable.eror)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(countryPhoto);
        }

        @Override
        public void setText(String title) {
            countryName.setText(title);
        }
    }
}
