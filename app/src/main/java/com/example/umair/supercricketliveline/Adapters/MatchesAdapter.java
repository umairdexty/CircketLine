package com.example.umair.supercricketliveline.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umair.supercricketliveline.POJOClasses.MatchDetails;
import com.example.umair.supercricketliveline.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchCardViewHolder> {

    private MyCLickListener myCLickListener;

    public interface MyCLickListener{
        void Onclick(MatchDetails match);
    }
    List<MatchDetails> matches;

    public MatchesAdapter(List<MatchDetails> matches, MyCLickListener myCLickListener) {
        this.matches = matches;
        this.myCLickListener = myCLickListener;
    }

    public void setMatches(List<MatchDetails> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_match_card,parent,false);
        return new MatchCardViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MatchCardViewHolder holder, int position) {
        MatchDetails match = matches.get(position);
        holder.matchSheduleView.setText(match.getMatch_type()+", "+match.getVenue());
        holder.firstTeamView.setText(match.getTeamA());
        //holder.firstTeamScoreView.setVisibility(View.GONE);
        holder.secondTeamView.setText(match.getTeamB());
        //holder.secondTeamScoreView.setVisibility(View.GONE);
        holder.statusView.setText(match.getDatetime() );
        if (match.getMatch_status().equals("Live")){
            holder.statusView.setText("Match is Live" );

        }
        else if (match.getMatch_status().equals("Played")){
            holder.statusView.setText("Match has ended" );

        }
        if (match.getFirstTeamScore()!=null){
            holder.firstTeamScoreView.setText(match.getFirstTeamScore());
        }if (match.getSecondTeamScore() != null){
            Log.d("SCORE",match.getSecondTeamScore());
            holder.secondTeamScoreView.setText(match.getSecondTeamScore());
        }

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class  MatchCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Toast mToast;
        private TextView matchSheduleView, firstTeamView, secondTeamView, firstTeamScoreView, secondTeamScoreView, statusView;
        private ImageView firstTeamFlagView, secondTeamFlagView;
        public MatchCardViewHolder(View itemView) {
            super(itemView);
            matchSheduleView = itemView.findViewById(R.id.match_schedule);
            firstTeamFlagView = itemView.findViewById(R.id.team_a_flag);
            firstTeamView = itemView.findViewById(R.id.team_a_name);
            firstTeamScoreView = itemView.findViewById(R.id.team_a_score);
            secondTeamFlagView = itemView.findViewById(R.id.team_b_flag);
            secondTeamScoreView = itemView.findViewById(R.id.team_b_score);
            secondTeamView = itemView.findViewById(R.id.team_b_name);
            statusView = itemView.findViewById(R.id.status);
            itemView.findViewById(R.id.mainCard1).setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            MatchDetails mh = matches.get(adapterPosition);
            myCLickListener.Onclick(mh);

        }
    }
}
