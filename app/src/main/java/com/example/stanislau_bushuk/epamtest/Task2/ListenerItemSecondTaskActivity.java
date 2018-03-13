package com.example.stanislau_bushuk.epamtest.Task2;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.epamtest.App;
import com.example.stanislau_bushuk.epamtest.GlideApp;
import com.example.stanislau_bushuk.epamtest.R;

/**
 * Created by Stanislau_Bushuk on 3/10/2018.
 */

public class ListenerItemSecondTaskActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listen_item_second_task);

        init();
        initActionBar("Listen Task 1");
    }

    public void init(){
        String title = getIntent().getStringExtra("TITLE");
        String url = getIntent().getStringExtra("URL");
        TextView titleView = findViewById(R.id.countryName1);
        ImageView coutryImage = findViewById(R.id.imageCountry1);
        titleView.setText(title);
        GlideApp.with(this)
                .load(url)
                .error(R.drawable.ic_menu_gallery)
                .centerCrop()
                .into(coutryImage);
    }

    public void initActionBar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.arrow);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
