package com.example.stanislau_bushuk.epamtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.stanislau_bushuk.epamtest.Task1.FIrstTaskFragment;
import com.example.stanislau_bushuk.epamtest.Task2.SecondTaskFragment;
import com.example.stanislau_bushuk.epamtest.Task3.FragmentMoxy;
import com.example.stanislau_bushuk.epamtest.Task4.ForthTaskFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FIrstTaskFragment firstTaskFragment;
    private SecondTaskFragment secondTaskFragment;
    private FragmentMoxy thirdTaskFragment;
    private ForthTaskFragment forthTaskFragment;
    private ActionBar actionBar;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        initActionBar(getResources().getString(R.string.Part1));
        if (savedInstanceState == null) {
            firstTaskFragment = new FIrstTaskFragment();
            initActionBar(getResources().getString(R.string.Part1));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
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
        int id = item.getItemId();
        switch (id) {
            case R.id.Part1: {
                firstTaskFragment = new FIrstTaskFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contaier, firstTaskFragment);
                fragmentTransaction.commit();


                break;
            }
            case R.id.Part2: {
                secondTaskFragment = new SecondTaskFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contaier, secondTaskFragment);
                fragmentTransaction.commit();

                break;
            }
            case R.id.Part3: {
                thirdTaskFragment = new FragmentMoxy();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contaier, thirdTaskFragment);
                fragmentTransaction.commit();

                break;
            }
            case R.id.Part4: {
                forthTaskFragment = new ForthTaskFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.contaier, forthTaskFragment);
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



