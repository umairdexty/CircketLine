package com.example.umair.supercricketliveline;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.umair.supercricketliveline.Adapters.MatchesAdapter;
import com.example.umair.supercricketliveline.POJOClasses.MatchDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesActivity extends AppCompatActivity implements MatchesAdapter.MyCLickListener {
    private static final String TAG = MatchesActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MatchesAdapter mAdapter;
    List<MatchDetails> matchesList;
    private ProgressBar progressBar;
    private String matchId;
    private Map<String,Integer> map;
    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_matches);
        progressBar = findViewById(R.id.progressBar);
        cl = findViewById(R.id.no_internet);
        map = new HashMap<>();
        progressBar.setVisibility(View.VISIBLE);
        cl.setVisibility(View.GONE);
         new NetworkCheck().execute();
    }
    private void internetIsAvailable(boolean b){
        if (b) {
            mRecyclerView = findViewById(R.id.match_card_recycler_view);
            FirebaseDatabase mFBDatabase = FirebaseDatabase.getInstance();
            matchesList = new ArrayList<>();
            mAdapter = new MatchesAdapter(matchesList, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(mAdapter);
            mFBDatabase.getReference("match").
                    addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            final MatchDetails match = dataSnapshot.getValue(MatchDetails.class);
                            match.setMatchPudhId(dataSnapshot.getKey());

                            progressBar.setVisibility(View.GONE);
                            if (match.getMatch_status().equals("Live")) {

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("totalscore/" + match.getMatchPudhId());
                                Log.d("totalscore","score");
                                matchId = match.getMatchPudhId();
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dSnapshot) {
                                        for (DataSnapshot ds : dSnapshot.getChildren()) {
                                            if (ds.getKey().equals(match.getTeamA())) {
                                                String s = ds.child("score").getValue(Long.class).toString() + "-"
                                                        + ds.child("wickets").getValue(Long.class).toString() + "("
                                                        + ds.child("overs").getValue().toString() + ")";
                                                Log.e("TAG!", s);

                                                match.setFirstTeamScore(s);

                                            } else {
                                                if (ds.getKey().equals(match.getTeamB())) {
                                                    String s = ds.child("score").getValue(Long.class).toString() + "-"
                                                            + ds.child("wickets").getValue(Long.class).toString() + "("
                                                            + ds.child("overs").getValue().toString() + ")";
                                                    match.setSecondTeamScore(s);
                                                    progressBar.setVisibility(View.GONE);
                                                    Log.e("TAN!", s);
                                                }
                                            }



                                        }int index;
                                        try {
                                            index = map.get(String.valueOf(matchId));
                                        }catch (NullPointerException e){
                                            matchesList.add(match);
                                            map.put(matchId,matchesList.indexOf(match));
                                            mAdapter.notifyDataSetChanged();
                                            mRecyclerView.invalidate();
                                            return;
                                        }
                                        matchesList.set(index,match);

                                        mAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            } else {
                                matchesList.add(match);
                                map.put(dataSnapshot.getKey(),matchesList.indexOf(match));
                                mAdapter.notifyDataSetChanged();
                                mRecyclerView.invalidate();
                            }


                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            MatchDetails match = dataSnapshot.getValue(MatchDetails.class);
                            match.setMatchPudhId(dataSnapshot.getKey());
                            int index;
                            try {
                                index = map.get(dataSnapshot.getKey());
                            }catch (NullPointerException e){
                                matchesList.add(match);
                                map.put(dataSnapshot.getKey(),matchesList.indexOf(match));
                                mAdapter.notifyDataSetChanged();
                                return;
                            }
                            matchesList.set(index,match);
                            mAdapter.notifyDataSetChanged();

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
        }else {
            progressBar.setVisibility(View.GONE);
            cl.setVisibility(View.VISIBLE);
            findViewById(R.id.button_refresh).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    cl.setVisibility(View.GONE);
                    new NetworkCheck().execute();
                }
            });
        }

    }

    @Override
    public void Onclick(MatchDetails match) {
        Intent intent = new Intent(
                MatchesActivity.this,
                ContainerActivity.class);
        intent.putExtra("MatchPushId", match.getMatch_id());
        intent.putExtra("FirstTeam", match.getTeamA());
        intent.putExtra("SecondTeam", match.getTeamB());
        intent.putExtra("MatchType", match.getMatch_type());
        startActivity(intent);

    }

    public class NetworkCheck extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                int timeoutMs = 1500;
                Socket sock = new Socket();
                SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

                sock.connect(sockaddr, timeoutMs);
                sock.close();

                return true;
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            internetIsAvailable(aBoolean);
        }

    }
}