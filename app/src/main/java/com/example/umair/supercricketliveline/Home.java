package com.example.umair.supercricketliveline;

import com.example.umair.supercricketliveline.POJOClasses.CheckInternetConnection;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.solver.GoalRow;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import androidx.lifecycle.ViewModelProvider;

import com.example.umair.supercricketliveline.Adapters.HomeViewPagerAdapter;
import com.example.umair.supercricketliveline.POJOClasses.HomeViewModel;


public class Home extends AppCompatActivity implements CheckInternetConnection {
    HomeViewModel homeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBarHome;
    private AppCompatButton appCompatButtonHome;
    private LinearLayout linearLayout;
    private int[] tabIcons = {R.drawable.outline_live_tv_24,
            R.drawable.outline_watch_later_24};
    CheckInternetConnection checkInternetConnection;
    HomeViewPagerAdapter homeViewPagerAdapter;

    public Home() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setElevation(0);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        progressBarHome = findViewById(R.id.progressBarHome);
        linearLayout = findViewById(R.id.inner_Linear_Layout_);

        appCompatButtonHome = findViewById(R.id.button_refresh_Home);
        linearLayout.setVisibility(View.VISIBLE);
        viewPager = findViewById(R.id.viewPagerHome);
        checkInternetConnection = this;
        final CheckInternet checkInternet = new CheckInternet(this);
        checkInternet.execute();

        tabLayout = (TabLayout) findViewById(R.id.homeTabLayout);

        appCompatButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarHome.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);

                new CheckInternet(checkInternetConnection).execute();

            }
        });

    }


    @Override
    public void onInternetConnected(boolean isConnected) {

        Log.d("connect", Boolean.toString(isConnected));
        if (isConnected) {
            linearLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            progressBarHome.setVisibility(View.GONE);
            tabLayout.setupWithViewPager(viewPager);

            viewPager.setAdapter(homeViewPagerAdapter);
            setupTabIcons();
            viewPager.setOffscreenPageLimit(2);
            viewPager.setCurrentItem(1);

        } else {
            linearLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            progressBarHome.setVisibility(View.GONE);
        }
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);}
}


