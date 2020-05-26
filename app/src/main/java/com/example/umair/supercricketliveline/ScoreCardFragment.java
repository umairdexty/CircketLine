package com.example.umair.supercricketliveline;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umair.supercricketliveline.Adapters.FOWAdapter;
import com.example.umair.supercricketliveline.Adapters.PlayerPerfRVAdapter;
import com.example.umair.supercricketliveline.POJOClasses.BatingCardClass;
import com.example.umair.supercricketliveline.POJOClasses.FoL;
import com.example.umair.supercricketliveline.POJOClasses.OversCardClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCardFragment extends Fragment {
    private FirebaseDatabase mDB;
    private String batingTeam , bowlingTeam;
    private long matchId;
    private boolean flag;
    private TextView txtExtra, txtTotal;
    private RecyclerView fowListView;
    private PlayerPerfRVAdapter mBatsmanAdapter;
    private PlayerPerfRVAdapter mBowlerAdapter;
    private List<BatingCardClass> ppList;
    private List<OversCardClass> allBwolers;
    private List<FoL> fallOfWicketList;
    private FOWAdapter fallOfWicketAdapter;
    private Map<String, Integer> map;
    private Map<String, Integer> bowelersMap;

    public ScoreCardFragment() {
        //required empty constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = FirebaseDatabase.getInstance();
        batingTeam = getArguments().getString("TeamA");
        bowlingTeam = getArguments().getString("TeamB");
        flag = getArguments().getBoolean("flag");
        matchId = getArguments().getLong("MatchId",0);
        Log.d("AWAIN",batingTeam+" "+bowlingTeam+" "+matchId);
        ppList = new ArrayList<>();
        allBwolers = new ArrayList<>();
        fallOfWicketList = new ArrayList<>();
        fallOfWicketAdapter = new
                FOWAdapter(fallOfWicketList,getContext());
        map = new HashMap<>();
        bowelersMap = new HashMap<>();
        mBatsmanAdapter = new PlayerPerfRVAdapter(ppList, PlayerPerfRVAdapter.BATSMAN_CODE);
        mBowlerAdapter = new PlayerPerfRVAdapter(allBwolers, PlayerPerfRVAdapter.BOWLER_CODE);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //String matchId = getArguments().getString("matchid");

        return inflater.inflate(R.layout.scorcard_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (flag){
            super.onViewCreated(view, savedInstanceState);
            RecyclerView batsmenRV = view.findViewById(R.id.batsmen_rv);
            RecyclerView bowlerRV = view.findViewById(R.id.bowlers_rv);
            batsmenRV.setLayoutManager(new LinearLayoutManager(getContext()));
            bowlerRV.setLayoutManager(new LinearLayoutManager(getContext()));
            batsmenRV.setAdapter(mBatsmanAdapter);
            bowlerRV.setAdapter(mBowlerAdapter);
            txtExtra = view.findViewById(R.id.txt_extra);
            txtTotal = view.findViewById(R.id.txt_total);
            fowListView = view.findViewById(R.id.fall_of_wickets_listview);
            fowListView.setLayoutManager(new LinearLayoutManager(getContext()));
            fowListView.setAdapter(fallOfWicketAdapter);
            mDB.getReference("BattingCard").child(String.valueOf(matchId)).child(batingTeam)
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            Log.d("Match",dataSnapshot.toString());
                            BatingCardClass bbl = dataSnapshot.getValue(BatingCardClass.class);
                            bbl.setBatsmanName(dataSnapshot.getKey());
                            ppList.add(bbl);
                            int index = ppList.indexOf(bbl);
                            map.put(dataSnapshot.getKey(), index);
                            mBatsmanAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            BatingCardClass bbl = dataSnapshot.getValue(BatingCardClass.class);
                            String name = dataSnapshot.getKey();
                            bbl.setBatsmanName(name);
                            int index;
                            try {
                                index = map.get(name);
                            } catch (NullPointerException e) {
                                ppList.add(bbl);
                                index = ppList.indexOf(bbl);
                                map.put(name, index);
                                mBatsmanAdapter.notifyDataSetChanged();
                                return;
                            }
                            ppList.set(index, bbl);
                            mBatsmanAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            mDB.getReference("OverCard").child(String.valueOf(matchId)).child(bowlingTeam)
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            Log.d("Match",dataSnapshot.toString());

                            OversCardClass occ = dataSnapshot.getValue(OversCardClass.class);
                            occ.setBowlerName(dataSnapshot.getKey());
                            allBwolers.add(occ);
                            bowelersMap.put(dataSnapshot.getKey(), allBwolers.indexOf(occ));
                            mBowlerAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            OversCardClass occ = dataSnapshot.getValue(OversCardClass.class);
                            occ.setBowlerName(dataSnapshot.getKey());
                            int index;
                            try {
                                index = bowelersMap.get(dataSnapshot.getKey());
                            } catch (NullPointerException e) {
                                allBwolers.add(occ);
                                index = allBwolers.indexOf(occ);
                                bowelersMap.put(dataSnapshot.getKey(), index);
                                mBowlerAdapter.notifyDataSetChanged();
                                return;
                            }
                            allBwolers.set(index, occ);
                            mBowlerAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            mDB.getReference("Extras").child(String.valueOf(matchId)).child(bowlingTeam)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot ds) {
                            String s = "Wd: " + ds.child("a_wides").getValue().toString()
                                    + ", B: " + ds.child("b_byes").getValue().toString()
                                    + ", N: " + ds.child("c_noballs").getValue().toString();
                            txtExtra.setText(s);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            mDB.getReference("totalscore").child(String.valueOf(matchId)).child(batingTeam)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot ds) {
                            String s = ds.child("score").getValue().toString() + "-"
                                    + ds.child("wickets").getValue(Long.class).toString() + "("
                                    + ds.child("overs").getValue().toString() + ")";
                            txtTotal.setText(s);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            mDB.getReference("fallofwickets").child(String.valueOf(matchId)).child(batingTeam)
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            FoL f  = dataSnapshot.getValue(FoL.class);
                            fallOfWicketList.add(f);
                            fallOfWicketAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


        }
        else {
            view.findViewById(R.id.innings_not).setVisibility(View.VISIBLE);
            view.findViewById(R.id.scrollable_view).setVisibility(View.GONE);

        }
    }
}
