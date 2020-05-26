package com.example.umair.supercricketliveline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.umair.supercricketliveline.Adapters.MatchesAdapter;
import com.example.umair.supercricketliveline.POJOClasses.HomeViewModel;
import com.example.umair.supercricketliveline.POJOClasses.MatchDetails;
import java.util.ArrayList;
import java.util.List;


public class OngoingFragment extends Fragment implements MatchesAdapter.MyCLickListener {
    private RecyclerView mRecyclerView;
    private MatchesAdapter mAdapter;
    private List<MatchDetails> matchesListOnGoing = new ArrayList<>();
    private HomeViewModel homeViewModel;


    public OngoingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = view.findViewById(R.id.match_card_recycler_view_onGoing);
        mAdapter = new MatchesAdapter(matchesListOnGoing, this);
       mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }









    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OngoingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OngoingFragment newInstance(String param1, String param2) {
        OngoingFragment fragment = new OngoingFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel.get_ongoing_matchesList().observe(getViewLifecycleOwner(), new Observer<List<MatchDetails>>() {
            @Override
            public void onChanged(List<MatchDetails> matchDetails) {
                mAdapter.setMatches(matchDetails);
                mAdapter.notifyDataSetChanged();
                Log.d("observer",matchDetails.toString());
                mRecyclerView.invalidate();
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing, container, false);
    }




    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void Onclick(MatchDetails match) {
        Intent intent = new Intent(
                getContext(),
                ContainerActivity.class);
        intent.putExtra("MatchPushId", match.getMatch_id());
        intent.putExtra("FirstTeam", match.getTeamA());
        intent.putExtra("SecondTeam", match.getTeamB());
        intent.putExtra("MatchType", match.getMatch_type());
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
