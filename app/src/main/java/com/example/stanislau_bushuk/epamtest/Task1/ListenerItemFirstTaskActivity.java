package com.example.stanislau_bushuk.epamtest.Task1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.R;

/**
 * Created by Stanislau_Bushuk on 3/10/2018.
 */

public class ListenerItemFirstTaskActivity extends AppCompatActivity {


    private String title;
    private String subTitle;
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listen_item_first_task);
        init();
    }

    public void init() {
        title = getIntent().getStringExtra("TITLE");
        subTitle = getIntent().getStringExtra("SUBTITLE");
        TextView titleView = findViewById(R.id.Title);
        TextView subTitleView = findViewById(R.id.SubTitle);
        titleView.setText(title);
        subTitleView.setText(subTitle);
        initActionBar(title);
    }

    public void initActionBar(String title) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.arrow);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
