package com.example.stanislau_bushuk.epamtest.fragmentsTask1;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenItemFirstTask extends Fragment implements FragmentManager.OnBackStackChangedListener {


    private View view;
    private TextView title;
    private TextView subTitle;

    public ListenItemFirstTask() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = view.findViewById(R.id.Title);
        subTitle = view.findViewById(R.id.SubTitle);
        title.setText(getArguments().getString("Name"));
        subTitle.setText(getArguments().getString("Subtitle"));
        setActionBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_listen_item_first_task, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void setActionBar() {
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.arrow);
            actionBar.setTitle("Listen" + title.getText());
        }
    }

    @Override
    public void onBackStackChanged() {
        getFragmentManager().popBackStack();
    }
}
