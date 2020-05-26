package com.example.umair.supercricketliveline.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umair.supercricketliveline.POJOClasses.FoL;
import com.example.umair.supercricketliveline.R;

import java.util.List;

public class FOWAdapter extends RecyclerView.Adapter<FOWAdapter.MyView> {


    private List<FoL> balls;
    private Context context;

    public FOWAdapter(List<FoL> balls, Context context) {
        this.context = context;
        this.balls = balls;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyView(LayoutInflater.from(parent.getContext()).inflate(R.layout.fallofwickets_layout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        FoL f = balls.get(position);
        holder.name.setText(f.getBatsman());
        holder.score.setText(String.valueOf(f.getScore()));
        holder.over.setText(String.valueOf(f.getOver()));


    }


    @Override
    public int getItemCount() {
        return balls.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        TextView  name,score, over;
        public MyView(View itemView) {

            super(itemView);
            score = itemView.findViewById(R.id.fol_score);
            over = itemView.findViewById(R.id.fol_overs);
            name = itemView.findViewById(R.id.fol_name);

        }
    }

}


