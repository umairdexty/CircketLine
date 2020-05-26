package com.example.umair.supercricketliveline;

import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.umair.supercricketliveline.Adapters.SecondPagerAdapter;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        getSupportActionBar().setElevation(0);
        Intent intent = getIntent();
        ViewPager viewPager = findViewById(R.id.viewPagerScoreBoard);
        viewPager.setOffscreenPageLimit(2);
        String[] tabs = {intent.getStringExtra("FirstTeam"),intent.getStringExtra("SecondTeam")};
        SecondPagerAdapter adapter = new SecondPagerAdapter(getSupportFragmentManager(),tabs,intent);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(intent.getIntExtra("Default",0));
        viewPager.setOffscreenPageLimit(2);
        final TabLayout tabLayout = findViewById(R.id.tab_teams);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
