package com.example.umair.supercricketliveline.POJOClasses;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BallByBall {
    private String Batsman, bowlername, Result;
    private long score;
    private double over;

    public BallByBall() {
    }

    public BallByBall(String batsman, String bowlername, String result, long score, double over) {
        Batsman = batsman;
        this.bowlername = bowlername;
        Result = result;
        this.score = score;
        this.over = over;
    }

    public String getBatsman() {
        return Batsman;
    }

    public void setBatsman(String batsman) {
        Batsman = batsman;
    }

    public String getBowlername() {
        return bowlername;
    }

    public void setBowlername(String bowlername) {
        this.bowlername = bowlername;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public double getOver() {
        return over;
    }

    public void setOver(double over) {
        this.over = over;
    }
    public String getRandS(){
        return Result + score;
    }
}
