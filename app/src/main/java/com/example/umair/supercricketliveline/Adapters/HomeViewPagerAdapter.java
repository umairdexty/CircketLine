package com.example.umair.supercricketliveline.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.umair.supercricketliveline.OngoingFragment;
import com.example.umair.supercricketliveline.UpcomingFragment;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private String tabTitlesHome[] = new String[] { "ONGOING", "UPCOMING" };
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
            return new OngoingFragment();
        }
        else if (i == 1){
            return new UpcomingFragment();
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {


        return tabTitlesHome[position];
    }
}
