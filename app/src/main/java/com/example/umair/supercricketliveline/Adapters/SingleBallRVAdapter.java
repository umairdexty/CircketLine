package com.example.umair.supercricketliveline.Adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umair.supercricketliveline.POJOClasses.BallByBall;
import com.example.umair.supercricketliveline.R;

import java.util.List;

public class SingleBallRVAdapter extends RecyclerView.Adapter<SingleBallRVAdapter.MyViewHolder> {


    private List<BallByBall> balls;
    private Context context;

    public SingleBallRVAdapter(List<BallByBall> balls, Context context) {
        this.context = context;
        this.balls = balls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ball,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BallByBall blb = balls.get(position);
        switch (blb.getResult() ){
            case "Wd":
            case "B":
            case "W":
            case "Lb":
            case "N":{
                if (blb.getScore()!=0)
                    holder.ballCircle.setText(blb.getRandS());
                else
                    holder.ballCircle.setText(blb.getResult());
                break;
            }
            default:
                holder.ballCircle.setText(blb.getScore()+"");
                break;
        }

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.ballCircle.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getBallColor(blb.getResult());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


    }

    @Override
    public int getItemCount() {
        return balls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ballCircle;
        public MyViewHolder(View itemView) {

            super(itemView);
            ballCircle = itemView.findViewById(R.id.txt_single_ball);

        }
    }
    public int getBallColor(String result){
        int mresultColorResourceId;
        switch (result) {
            case "0":
                mresultColorResourceId = R.color.noRun;
                break;
            case "1":
            case "2":
            case "3":
                mresultColorResourceId = R.color.singleRun;
                break;
            case "4":
                mresultColorResourceId = R.color.fourRun;
                break;
            case "6":
                mresultColorResourceId = R.color.sixRun;
                break;
            case "W":
            case "N":
                mresultColorResourceId = R.color.out;
                break;
            default:
                mresultColorResourceId = R.color.extraRun;
                break;
        }
        return ContextCompat.getColor(context, mresultColorResourceId);
    }

}
