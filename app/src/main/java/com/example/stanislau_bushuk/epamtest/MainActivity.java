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

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Fragment firstTaskFragment;
    private Fragment secondTaskFragment;
    private Fragment thirdTaskFragment;
    private Fragment forthTaskFragment;
    private ActionBar actionBar;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm=Realm.getDefaultInstance();
        initActionBar(getResources().getString(R.string.Part1));
        if (savedInstanceState == null) {
            firstTaskFragment = new FIrstTaskFragment();
            initActionBar(getResources().getString(R.string.Part1));
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
            fragmentTransaction.commit();
        }
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
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.Part1: {
                firstTaskFragment = new FIrstTaskFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle(R.string.Part1);

                break;
            }
            case R.id.Part2: {
                secondTaskFragment = new SecondTaskFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, secondTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle(R.string.Part2);
                break;
            }
            case R.id.Part3: {
                thirdTaskFragment = new ThirdTaskFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, thirdTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle(R.string.Part3);
                break;
            }
            case R.id.Part4: {
                forthTaskFragment = new ForthTaskFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.contaier, forthTaskFragment);
                fragmentTransaction.commit();
                actionBar.setTitle(R.string.Part4);
                break;
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}



