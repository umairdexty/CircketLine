package com.example.umair.supercricketliveline.POJOClasses;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BatingCardClass {
    private Long b_sixes,c_fours,d_balls, e_score , bating_order;
    private String batsmanName,bowler_out, field_by, out_type, status;
    public BatingCardClass() {
    }

    public Long getBating_order() {
        return bating_order;
    }

    public void setBating_order(Long bating_order) {
        this.bating_order = bating_order;
    }

    public Long getB_sixes() {
        return b_sixes;
    }

    public void setB_sixes(Long b_sixes) {
        this.b_sixes = b_sixes;
    }

    public Long getC_fours() {
        return c_fours;
    }

    public void setC_fours(Long c_fours) {
        this.c_fours = c_fours;
    }

    public Long getD_balls() {
        return d_balls;
    }

    public void setD_balls(Long d_balls) {
        this.d_balls = d_balls;
    }

    public Long getE_score() {
        return e_score;
    }

    public void setE_score(Long e_score) {
        this.e_score = e_score;
    }

    public String getBowler_out() {
        return bowler_out;
    }

    public void setBowler_out(String bowler_out) {
        this.bowler_out = bowler_out;
    }

    public String getField_by() {
        return field_by;
    }

    public void setField_by(String field_by) {
        this.field_by = field_by;
    }

    public String getOut_type() {
        return out_type;
    }

    public void setOut_type(String out_type) {
        this.out_type = out_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }
}
