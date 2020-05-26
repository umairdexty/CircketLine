package com.example.umair.supercricketliveline;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.umair.supercricketliveline.Adapters.TeamsExpandableAdapter;
import com.example.umair.supercricketliveline.POJOClasses.TeamPlayers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class infoFragment extends Fragment {
    private ArrayList<String> title;
    private HashMap<String,List<TeamPlayers>> playerList;
    private FirebaseDatabase mFBDatabase;
    private String firstTeam, secondTeam;
    private long matchId;


    public infoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        firstTeam = bundle.getString("FirstTeam");
        secondTeam = bundle.getString("SecondTeam");
        matchId = bundle.getLong("MatchPushId", 0);
        mFBDatabase = FirebaseDatabase.getInstance();
        title = new ArrayList<>();
        playerList = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ExpandableListView exp = getView().findViewById(R.id.epanable_listview);
        final TeamsExpandableAdapter mAdapter = new TeamsExpandableAdapter(getContext(),title,playerList);
        exp.setAdapter(mAdapter);
        mFBDatabase.getReference("player").child(firstTeam).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("ns",dataSnapshot.toString());
                    ArrayList<TeamPlayers> tms = new ArrayList<>();
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        TeamPlayers tm = ds.getValue(TeamPlayers.class);
                        tms.add(tm);
                    }
                    title.add(firstTeam);
                    playerList.put(firstTeam,tms);
                    mAdapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mFBDatabase.getReference("player").child(secondTeam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("ns",dataSnapshot.toString());
                ArrayList<TeamPlayers> tms = new ArrayList<>();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    TeamPlayers tm = ds.getValue(TeamPlayers.class);
                    tms.add(tm);
                }
                title.add(secondTeam);
                playerList.put(secondTeam,tms);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
