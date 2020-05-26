package com.example.umair.supercricketliveline.POJOClasses;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class OversCardClass {
    private long b_maiden, c_wickets, e_score;
    private double d_overs;
    private String BowlerName;


    public OversCardClass() {
    }

    public long getB_maiden() {
        return b_maiden;
    }

    public void setB_maiden(long b_maiden) {
        this.b_maiden = b_maiden;
    }

    public long getC_wickets() {
        return c_wickets;
    }

    public void setC_wickets(long c_wickets) {
        this.c_wickets = c_wickets;
    }

    public double getD_overs() {
        return d_overs;
    }

    public void setD_overs(double d_overs) {
        this.d_overs = d_overs;
    }

    public long getE_score() {
        return e_score;
    }

    public void setE_score(long e_score) {
        this.e_score = e_score;
    }

    public String getBowlerName() {
        return BowlerName;
    }

    public void setBowlerName(String bowlerName) {
        BowlerName = bowlerName;
    }
}
