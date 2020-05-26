package com.example.umair.supercricketliveline;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.umair.supercricketliveline.Adapters.MatchesAdapter;
import com.example.umair.supercricketliveline.POJOClasses.HomeViewModel;
import com.example.umair.supercricketliveline.POJOClasses.MatchDetails;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements MatchesAdapter.MyCLickListener {
    private RecyclerView mRecyclerView;
    private MatchesAdapter mAdapter;
    List<MatchDetails> matchesList = new ArrayList<>();
    private HomeViewModel homeViewModel;
    private View view;


    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.match_card_recycler_view_upcoming);
        mAdapter = new MatchesAdapter(matchesList, this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeViewModel.get_upcoming_matchesList().observe(getViewLifecycleOwner(), new Observer<List<MatchDetails>>() {
            @Override
            public void onChanged(List<MatchDetails> matchDetails) {
                mAdapter.setMatches(matchDetails);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.invalidate();
                Log.d("matchDettailsupcom",matchDetails.toString());
            }

        });
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }


    @Override
    public void Onclick(MatchDetails match) {
//        Intent intent = new Intent(
//                getContext(),
//                ContainerActivity.class);
//        intent.putExtra("MatchPushId", match.getMatch_id());
//        intent.putExtra("FirstTeam", match.getTeamA());
//        intent.putExtra("SecondTeam", match.getTeamB());
//        intent.putExtra("MatchType", match.getMatch_type());
//        startActivity(intent);

        Toast.makeText(getContext(), "Match Will Start Soon!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);


    }

    }

