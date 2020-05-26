package com.example.umair.supercricketliveline.Adapters;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.umair.supercricketliveline.ScoreCardFragment;

public class SecondPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles;
    private String innings, batingTeam, TeamA,TeamB ;
    private Long MatchId;
    public SecondPagerAdapter(FragmentManager fm, String[] tabTitles, Intent intent) {
        super(fm);
        this.tabTitles = tabTitles;
        batingTeam = intent.getStringExtra("batingTeam");
        innings = intent.getStringExtra("Innings");
        TeamA = intent.getStringExtra("FirstTeam");
        TeamB = intent.getStringExtra("SecondTeam");
        MatchId = intent.getLongExtra("MatchId",0);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            ScoreCardFragment i = new ScoreCardFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("MatchId", MatchId);
            if (TeamB.equals(batingTeam) && innings.equals("1st")){
                bundle.putBoolean("flag",false);
            }else
            bundle.putBoolean("flag",true);
            bundle.putString("TeamA",TeamA);
            bundle.putString("TeamB",TeamB);
            i.setArguments(bundle);
            return i;
        }else if (position == 1) {
            ScoreCardFragment j = new ScoreCardFragment();
            Bundle b = new Bundle();
            b.putLong("MatchId", MatchId);
            b.putString("TeamB",TeamA);
            b.putString("TeamA",TeamB);
            if (TeamA.equals(batingTeam) && innings.equals("1st")){
                b.putBoolean("flag",false);
            }else
                b.putBoolean("flag",true);
            j.setArguments(b);
            return j;
        }else
            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
