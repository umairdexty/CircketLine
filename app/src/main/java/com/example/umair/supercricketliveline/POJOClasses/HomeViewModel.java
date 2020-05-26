package com.example.umair.supercricketliveline.POJOClasses;

import android.app.Application;

import androidx.annotation.Nullable;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private String matchId;
    MutableLiveData<List<MatchDetails>> _ongoing_matchesList = new MutableLiveData<>();
    MutableLiveData<List<MatchDetails>> _upcoming_matchesList = new MutableLiveData<>();

    public MutableLiveData<List<MatchDetails>> get_ongoing_matchesList() {

        return _ongoing_matchesList;
    }

    public MutableLiveData<List<MatchDetails>> get_upcoming_matchesList() {
        Log.d("upcomingmatchesModel",_upcoming_temp.toString());
        return _upcoming_matchesList;
    }

    final private List<MatchDetails> _ongoing_temp = new ArrayList<>();
    final private List<MatchDetails> _upcoming_temp = new ArrayList<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        FirebaseDatabase mFBDatabase = FirebaseDatabase.getInstance();


        mFBDatabase.getReference("match").
                addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@androidx.annotation.NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        final MatchDetails match = dataSnapshot.getValue(MatchDetails.class);
                        match.setMatchPudhId(dataSnapshot.getKey());


                        if (match.getMatch_status().equals("Live")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("totalscore/" + match.getMatchPudhId());

                            matchId = match.getMatchPudhId();
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@androidx.annotation.NonNull DataSnapshot dSnapshot) {
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

                                                Log.e("TAN!", s);
                                            }
                                        }

                                    }

                                    _ongoing_temp.add(match);
                                    _ongoing_matchesList.postValue(_ongoing_temp);
                                    Log.d("ongoingmatch", _ongoing_temp.toString());

                                }

                                @Override
                                public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {

                                }
                            });

                        } else if (match.getMatch_status().equals("upcoming")) {
                            _upcoming_temp.add(match);
                            _upcoming_matchesList.postValue(_upcoming_temp);
                            Log.d("matchDettailsModel", _upcoming_temp.toString());


                        } else  {
                            _ongoing_temp.add(match);
                            _ongoing_matchesList.postValue(_ongoing_temp);
                        }


                    }

                    @Override
                    public void onChildChanged(@androidx.annotation.NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        MatchDetails match = dataSnapshot.getValue(MatchDetails.class);
                        match.setMatchPudhId(dataSnapshot.getKey());
                        switch (match.getMatch_status()) {
                            case "Live":
                                for (MatchDetails matchDetails : _ongoing_temp) {

                                    if (matchDetails.getMatchPudhId().equals(match.getMatchPudhId())) {
                                        _ongoing_temp.remove(match);
                                    }

                                }
                                _ongoing_temp.add(match);
                                _ongoing_matchesList.postValue(_ongoing_temp);

                                break;
                            case "upcoming":
                                for (MatchDetails matchDetails : _upcoming_temp) {

                                    if (matchDetails.getMatchPudhId().equals(match.getMatchPudhId())) {
                                        _upcoming_temp.remove(match);
                                    }

                                }
                                _upcoming_temp.add(match);
                                _upcoming_matchesList.postValue(_upcoming_temp);

                                break;
                            case "Played":

                                for (MatchDetails matchDetails : _ongoing_temp) {

                                    if (matchDetails.getMatchPudhId().equals(match.getMatchPudhId())) {
                                        _ongoing_temp.remove(match);
                                    }

                                }
                                _ongoing_temp.add(match);
                                _ongoing_matchesList.postValue(_ongoing_temp);

                                break;
                                default:
                                    break;
                        }


                    }

                    @Override
                    public void onChildRemoved(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@androidx.annotation.NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {

                    }
                });


    }


}
