package com.example.umair.supercricketliveline;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ScoreBoardFragment extends Fragment implements View.OnClickListener {
    private String firstTeam, secondTeam,batingTeam,innings;
    long matchId;
    private Bundle bundle;

    public ScoreBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        firstTeam = bundle.getString("FirstTeam");
        secondTeam = bundle.getString("SecondTeam");
        matchId = bundle.getLong("MatchPushId", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_score_board, container, false);
        v.findViewById(R.id.team_a_card).setOnClickListener(this);
        v.findViewById(R.id.team_b_card).setOnClickListener(this);
        TextView txtAname = v.findViewById(R.id.team_a_name);
        txtAname.setText(firstTeam);
        TextView txtBname = v.findViewById(R.id.team_b_name);
        txtBname.setText(secondTeam);
        return v;

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(getContext(), ScoreboardActivity.class);
        i.putExtra("FirstTeam",firstTeam);
        i.putExtra("SecondTeam",secondTeam);
        i.putExtra("MatchId",matchId);
        i.putExtra("Innings",innings);
        i.putExtra("batingTeam",batingTeam);
        if (R.id.team_a_card == view.getId()){
            i.putExtra("Default",0);
        }else
            i.putExtra("Default",1);
        startActivity(i);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TextView txtAscore, txtBscore;
        txtAscore = view.findViewById(R.id.team_a_score);
        txtBscore = view.findViewById(R.id.team_b_score);
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("totalscore/" + matchId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dSnapshot) {
                for (DataSnapshot ds : dSnapshot.getChildren()) {
                    if (ds.getKey().equals(firstTeam)) {
                        String s = ds.child("score").getValue(Long.class).toString() + "-"
                                + ds.child("wickets").getValue(Long.class).toString() + "("
                                + ds.child("overs").getValue().toString() + ")";
                        txtAscore.setText(s);


                    } else if (ds.getKey().equals(secondTeam)) {
                        String s = ds.child("score").getValue(Long.class).toString() + "-"
                                + ds.child("wickets").getValue(Long.class).toString() + "("
                                + ds.child("overs").getValue().toString() + ")";
                        txtBscore.setText(s);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference("results/"+matchId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                batingTeam = dataSnapshot.child("Battingteam").getValue().toString();
                innings = dataSnapshot.child("Innings").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
