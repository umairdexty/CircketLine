package com.example.umair.supercricketliveline.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umair.supercricketliveline.POJOClasses.BatingCardClass;
import com.example.umair.supercricketliveline.POJOClasses.OversCardClass;
import com.example.umair.supercricketliveline.R;

import java.util.List;

public class PlayerPerfRVAdapter extends RecyclerView.Adapter<PlayerPerfRVAdapter.PPViewHolder>{

    public static final int BATSMAN_CODE = 1, BOWLER_CODE = 2;

    private List list;
    private int code;

    public PlayerPerfRVAdapter(List map, int Code) {
        this.list = map;
        this.code = Code;
    }

    @NonNull
    @Override
    public PPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PPViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.player_performance,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PPViewHolder holder, int position) {
        if (code == BATSMAN_CODE){
            BatingCardClass bcl = (BatingCardClass) list.get(position);
            holder.txtPlayerName.setText(bcl.getBatsmanName());
            holder.txtRunsOvers.setText(String.valueOf(bcl.getE_score()));
            holder.txtBallsMaiden.setText(String.valueOf(bcl.getD_balls()));
            holder.txtFoursRuns.setText(String.valueOf(bcl.getC_fours()));
            holder.txtSixesWickets.setText(String.valueOf(bcl.getB_sixes()));
            double f = bcl.getE_score()/bcl.getD_balls()*100;
            holder.txtSRER.setText(String.format("%.1f",f));
        }else if (code == BOWLER_CODE){
            OversCardClass occ = (OversCardClass) list.get(position);
            holder.txtPlayerName.setText(occ.getBowlerName());
            holder.txtRunsOvers.setText(String.valueOf(occ.getD_overs()));
            holder.txtBallsMaiden.setText(String.valueOf(occ.getB_maiden()));
            holder.txtFoursRuns.setText(String.valueOf(occ.getE_score()));
            holder.txtSixesWickets.setText(String.valueOf(occ.getC_wickets()));
            double total = (double) Math.round((occ.getE_score()/occ.getD_overs()) * 10) / 10;
            holder.txtSRER.setText(String.valueOf(total));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PPViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPlayerName, txtRunsOvers, txtBallsMaiden, txtFoursRuns, txtSixesWickets, txtSRER;
        public PPViewHolder(View itemView) {
            super(itemView);
            txtPlayerName = itemView.findViewById(R.id.txt_player_name);
            txtRunsOvers = itemView.findViewById(R.id.txt_runs_overs);
            txtBallsMaiden = itemView.findViewById(R.id.txt_ball_maiden);
            txtFoursRuns = itemView.findViewById(R.id.txt_fours_runs);
            txtSixesWickets = itemView.findViewById(R.id.txt_sixs_wickets);
            txtSRER = itemView.findViewById(R.id.txt_sr_er);
        }
    }
}
