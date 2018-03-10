package com.example.stanislau_bushuk.epamtest;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.stanislau_bushuk.epamtest.Task1.FIrstTaskFragment;
import com.example.stanislau_bushuk.epamtest.Task2.SecondTaskFragment;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FIrstTaskFragment firstTaskFragment;
    private Fragment secondTaskFragment;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar("Task1");
        firstTaskFragment = new FIrstTaskFragment();
        secondTaskFragment = new SecondTaskFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
        fragmentTransaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    public void initActionBar(String title) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getFragmentManager().getBackStackEntryCount();
            if(count==0){
                super.onBackPressed();
            }else {
                checkActionBar();
                getFragmentManager().popBackStack();

            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String id = (String) item.getTitle();
        Timber.e("rock");
        if (id.equals("Task 1")) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
            fragmentTransaction.commit();
            actionBar.setTitle("Task 1");

        } else if (id.equals("Task 2")) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, secondTaskFragment);
            fragmentTransaction.commit();
            actionBar.setTitle("Task 2");
        } else if (id.equals("Task 3")) {
            actionBar.setTitle("Task 3");
        } else if (id.equals("Task 4")) {
            actionBar.setTitle("Task 4");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void checkActionBar(){
        String actionBarTitle = (String) actionBar.getTitle();
        switch (actionBarTitle){
            case "Listen Task 1": initActionBar("Task 1");
        }

    }
}



