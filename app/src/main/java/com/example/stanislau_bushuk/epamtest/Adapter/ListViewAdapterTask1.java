package com.example.stanislau_bushuk.epamtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.Modele.Element;
import com.example.stanislau_bushuk.epamtest.R;
import com.example.stanislau_bushuk.epamtest.Task1.ListenerItemFirstTaskActivity;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by logoped583st on 7.3.18.
 */

public class ListViewAdapterTask1 extends RecyclerView.Adapter<ListViewAdapterTask1.ViewHolder> {

    private ArrayList<Element> arrayList;
    private Context context;
    private LayoutInflater mInflater;

    public ListViewAdapterTask1(Context context, ArrayList<Element> elements) {
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = elements;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewAdapterTask1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_first_task, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterTask1.ViewHolder holder, int position) {
        holder.relativeLayout.setTag(position);
        holder.name.setText(arrayList.get(position).getName());
        holder.description.setText(arrayList.get(position).getDescription());

    }


    @Override
    public int getItemCount() {
        Timber.e(String.valueOf(arrayList.size()));
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView description;
        private RelativeLayout relativeLayout;


        ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.list_item);
            name = itemView.findViewById(R.id.NAME);
            description = itemView.findViewById(R.id.DESCRIPTION);

            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ListenerItemFirstTaskActivity.class);
            intent.putExtra("TITLE", arrayList.get((int) view.getTag()).getName());
            intent.putExtra("SUBTITLE", arrayList.get((int) view.getTag()).getDescription());
            context.startActivity(intent);
        }
    }

}
