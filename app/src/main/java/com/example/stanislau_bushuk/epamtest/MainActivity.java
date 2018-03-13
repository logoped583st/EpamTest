package com.example.stanislau_bushuk.epamtest;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.stanislau_bushuk.epamtest.Task3.ThirdTaskFragment;
import com.example.stanislau_bushuk.epamtest.Task4.ForthTaskFragment;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FIrstTaskFragment firstTaskFragment;
    private Fragment secondTaskFragment;
    private Fragment thirdTaskFragment;
    private Fragment forthTaskFragment;
    private ActionBar actionBar;
    private Bundle bundle=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar("Part 1");
        firstTaskFragment = new FIrstTaskFragment();
        secondTaskFragment = new SecondTaskFragment();
        thirdTaskFragment = new ThirdTaskFragment();
        forthTaskFragment=new ForthTaskFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
        fragmentTransaction.commit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    public void initActionBar(String title) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getFragmentManager().getBackStackEntryCount();
            if (count == 0) {
                super.onBackPressed();
            } else {
                checkActionBar();
                getFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        String id = (String) item.getTitle();
        switch (id) {
            case "Part 1": {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle("Part 1");

                break;
            }
            case "Part 2": {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, secondTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle("Part 2");
                break;
            }
            case "Part 3": {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, thirdTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle("Part 3");
                break;
            }
            case "Part 4": {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, forthTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle("Part 4");
                break;
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void checkActionBar() {
        String actionBarTitle = (String) actionBar.getTitle();
        if (actionBarTitle != null) {
            switch (actionBarTitle) {
                case "Listen Task 1":
                    initActionBar("Task 1");
            }
        }
    }

    public Bundle getBundle(){
        return this.bundle;
    }
}



