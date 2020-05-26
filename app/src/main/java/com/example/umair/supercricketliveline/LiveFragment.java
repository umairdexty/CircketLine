package com.example.umair.supercricketliveline;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umair.supercricketliveline.Adapters.PlayerPerfRVAdapter;
import com.example.umair.supercricketliveline.Adapters.SingleBallRVAdapter;
import com.example.umair.supercricketliveline.POJOClasses.BallByBall;
import com.example.umair.supercricketliveline.POJOClasses.BatingCardClass;
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


public class LiveFragment extends Fragment {

    private int COUNTER = 0;
    private volatile String BatingTeam, currentBowler, BowlingTeam;
    private String firstTeam, secondTeam, Innings, matchType;
    private long matchId, mTarget;

    private TextView txtTitle,
             mTxtTargetView,
            txtCRR, txtRRR, txtBLeft, txtRLeft,
            ballStatus,board_target,board_ballREsult,
            feedteamLable,  feedteam1Value, team1Overs;


    private FirebaseDatabase mDatabase;
    private List<BallByBall> overs;
    private RecyclerView oversRV, playing_batsmans_rv, bowler_rv;
    private SingleBallRVAdapter adapter;
    private List<BatingCardClass> ppList;
    private Map<String, Integer> map;
    private PlayerPerfRVAdapter mBatsmanAdapter;
    private PlayerPerfRVAdapter mBowlerAdapter;
    private List<OversCardClass> allBwolers;
    private List<OversCardClass> mBowler;


    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {

        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //all variable initialization
        Bundle bundle = getArguments();
        firstTeam = bundle.getString("FirstTeam");
        secondTeam = bundle.getString("SecondTeam");
        matchType = bundle.getString("MatchType");
        matchId = bundle.getLong("MatchPushId", 0);
        overs = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance();
        ppList = new ArrayList<>();
        map = new HashMap<>();
        mBowler = new ArrayList<>();
        allBwolers = new ArrayList<>();
        mBowlerAdapter = new PlayerPerfRVAdapter(mBowler, PlayerPerfRVAdapter.BOWLER_CODE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViews(view);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        oversRV.setLayoutManager(lm);
        adapter = new SingleBallRVAdapter(overs, getContext());
        oversRV.setAdapter(adapter);
        bowler_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        bowler_rv.setAdapter(mBowlerAdapter);
        playing_batsmans_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBatsmanAdapter = new PlayerPerfRVAdapter(ppList, PlayerPerfRVAdapter.BATSMAN_CODE);
        playing_batsmans_rv.setAdapter(mBatsmanAdapter);
        mDatabase.getReference("results").child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot ds) {

                        BatingTeam = ds.child("Battingteam").getValue(String.class);
                        feedteamLable.setText(BatingTeam);

                        BowlingTeam = ds.child("BowlingTeam").getValue(String.class);

                        Innings = ds.child("Innings").getValue(String.class);
                        if (Innings.equals("2nd")) {
                            mTarget = ds.child("Target").getValue(Long.class);
                            board_target.setText(String.valueOf(mTarget));
                            mTxtTargetView.setText(String.valueOf(mTarget));

                        }
                        if (COUNTER == 0) {
                            setListener();
                            COUNTER++;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
        mDatabase.getReference("BallByBall").child(String.valueOf(matchId))
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        BallByBall blb = dataSnapshot.getValue(BallByBall.class);
                        overs.add(blb);
                        currentBowler = blb.getBowlername();
                        board_ballREsult.setText(blb.getResult());
                        if (adapter != null) {
                            oversRV.smoothScrollToPosition(adapter.getItemCount() - 1);
                            adapter.notifyDataSetChanged();
                        }
                        updateCurrentBowler();
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

    private void getViews(View view) {


        feedteamLable = view.findViewById(R.id.feedteamlable);


        feedteam1Value = view.findViewById(R.id.feedteam1Value);
        team1Overs = view.findViewById(R.id.team1Overs);

//        sessionLable=view.findViewById(R.id.sessionlable);
//
//        session1Value = view.findViewById(R.id.session1Value);
//        session2Value = view.findViewById(R.id.session2Value);
//
//        runsPerBallLable=view.findViewById(R.id.runsperballlable);

        txtCRR = view.findViewById(R.id.crr);
        txtRRR = view.findViewById(R.id.rrr);



        txtBLeft = view.findViewById(R.id.b_left);
        txtRLeft = view.findViewById(R.id.r_left);
        mTxtTargetView = view.findViewById(R.id.target_score);
        oversRV = view.findViewById(R.id.ball_recyclerview);
        bowler_rv = view.findViewById(R.id.bowlers_rv);
        playing_batsmans_rv = view.findViewById(R.id.batsmen_rv);

        txtTitle = view.findViewById(R.id.title);
        ballStatus = view.findViewById(R.id.board_ballstatus);
        board_ballREsult = view.findViewById(R.id.board_ballresult);
        board_target = view.findViewById(R.id.board_target);

        String s = firstTeam + " vs " + secondTeam + " (" + matchType + ")";
        txtTitle.setText(s);



    }

    private void setListener() {
        mDatabase.getReference("RunsxBallsFeed")
                .child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        mDatabase.getReference("SessionFeed")
                .child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        sessionLable.setText(dataSnapshot.child("label_Session").getValue().toString());
//                        session1Value.setText(dataSnapshot.child("feeds_Session1").getValue().toString());
//                        session2Value.setText(dataSnapshot.child("feeds_Session2").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        mDatabase.getReference("TeamFeed")
                .child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        mDatabase.getReference("BallStatus")
                .child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ballStatus.setText(dataSnapshot.child("ballstatus").getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        mDatabase.getReference("totalscore")
                .child(String.valueOf(matchId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long firstTeamScore = 0, secondTeamScore = 0;
                        double secondTeamOvers = 0.0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (Innings.equals("2nd")) {
                                if (ds.getKey().equals(BatingTeam)) {

                                    secondTeamScore = ds.child("score").getValue(Long.class);
                                    secondTeamOvers = ds.child("overs").getValue(Double.class);
                                    feedteamLable.setText(BatingTeam);


                                    feedteam1Value.setText(String.valueOf(secondTeamScore) + "-" + ds.child("wickets").getValue(Long.class));
                                    team1Overs.setText(String.valueOf(secondTeamOvers));


                                } else {
                                    firstTeamScore = ds.child("score").getValue(Long.class);
//                            firstTeamWickets = ds.child("wickets").getValue(Long.class);
//                            firstTeamOvers = ds.child("overs").getValue(Double.class);
                                }

                            } else {
                                if (ds.getKey().equals(BatingTeam)) {
                                    }
                            }

                        }
                        if (Innings.equals("2nd")) {
                            int runLeft = (int) (firstTeamScore - secondTeamScore);
                            int ballDone = (int) ((int) secondTeamOvers * 6 + ((secondTeamOvers - (int) secondTeamOvers) * 10));
                            int ballleft = 0;
                            switch ("T20") {
                                case "T20":
                                    ballleft = 120 - ballDone;
                                    break;
                                default:
                                    ballleft = 300 - ballDone;

                            }

                            txtCRR.setText( " CRR = " + (double) Math.round((secondTeamScore / secondTeamOvers) * 10) / 10);
                            try {
                                txtRRR.setText(" RRR = " + (double) Math.round((runLeft / (ballleft / 6)) * 10) / 10);
                            }
                            catch (ArithmeticException e){
                                if (runLeft == 0){
                                    txtRRR.setText("-");
                                }
                            }
                            txtBLeft.setText(String.valueOf(ballleft));

                            txtRLeft.setText(String.valueOf(runLeft));


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        mDatabase.getReference("BattingCard").child(String.valueOf(matchId)).child(BatingTeam)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        BatingCardClass bbl = dataSnapshot.getValue(BatingCardClass.class);
                        bbl.setBatsmanName(dataSnapshot.getKey());

                        if (bbl.getStatus().equals("playing")) {
                            ppList.add(bbl);
                            int index = ppList.indexOf(bbl);
                            map.put(dataSnapshot.getKey(), index);
                            mBatsmanAdapter.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        BatingCardClass bbl = dataSnapshot.getValue(BatingCardClass.class);
                        String name = dataSnapshot.getKey();
                        if (bbl.getStatus().equals("playing")) {
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
                        } else if (bbl.getStatus().equals("out")) {
                            int index = map.get(name);
                            ppList.remove(index);
                            map.remove(name);
                            mBatsmanAdapter.notifyDataSetChanged();
                        }
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
        mDatabase.getReference("OverCard").child(String.valueOf(matchId)+"/"+BowlingTeam ).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                OversCardClass occ = dataSnapshot.getValue(OversCardClass.class);
                occ.setBowlerName(dataSnapshot.getKey());
                allBwolers.add(occ);
                map.put(dataSnapshot.getKey(), allBwolers.indexOf(occ));
                updateCurrentBowler();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                OversCardClass occ = dataSnapshot.getValue(OversCardClass.class);
                occ.setBowlerName(dataSnapshot.getKey());
                int index = map.get(dataSnapshot.getKey());
                allBwolers.set(index, occ);
                updateCurrentBowler();

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

    public void updateCurrentBowler() {
        if (allBwolers != null && currentBowler != null) {
            int index;
            try {

                index = map.get(currentBowler);
            } catch (NullPointerException e) {
                return;
            }
            mBowler.clear();
            mBowler.add(allBwolers.get(index));
            mBowlerAdapter.notifyDataSetChanged();

        }
    }
}
