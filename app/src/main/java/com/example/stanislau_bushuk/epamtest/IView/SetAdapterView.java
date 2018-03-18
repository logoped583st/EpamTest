package com.example.stanislau_bushuk.epamtest.IView;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask3;

/**
 * Created by Stanislau_Bushuk on 3/17/2018.
 */

public interface SetAdapterView extends MvpView {
    void setImageAdapter(String url);
    void setTextAdapter(String title);
}
