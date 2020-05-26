package com.example.umair.supercricketliveline.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.umair.supercricketliveline.LiveFragment;
import com.example.umair.supercricketliveline.ScoreBoardFragment;
import com.example.umair.supercricketliveline.infoFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private Intent intent;
    private String tabTitles[] = new String[] { "SCORE BOARD", "LIVE", "INFO" };
    public SimpleFragmentPagerAdapter(FragmentManager fm, Intent intent) {
        super(fm);
        this.intent = intent;
    }


    @Override
    public Fragment getItem(int position) {
        if (position==1){
            LiveFragment lf =  new LiveFragment();
            lf.setArguments(intent.getExtras());
            return lf;
        }
        else if (position==0){
            ScoreBoardFragment sf =  new ScoreBoardFragment();
            sf.setArguments(intent.getExtras());
            return sf;
        }
        else if (position==2){
            infoFragment ift =  new infoFragment();
            ift.setArguments(intent.getExtras());
            return ift;
        }
        else {
            return null;
        }

    }


    @Override
    public CharSequence getPageTitle(int position) {


        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
