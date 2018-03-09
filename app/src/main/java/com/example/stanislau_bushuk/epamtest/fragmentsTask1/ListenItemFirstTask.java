package com.example.stanislau_bushuk.epamtest.fragmentsTask1;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stanislau_bushuk.epamtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenItemFirstTask extends Fragment implements FragmentManager.OnBackStackChangedListener {


    private View view;
    public ListenItemFirstTask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_listen_item_first_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onBackStackChanged() {
        getFragmentManager().popBackStack();
    }
}
