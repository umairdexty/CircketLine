package com.example.umair.supercricketliveline.POJOClasses;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
@IgnoreExtraProperties
public class MatchDetails {






    private String Datetime, Match_type, current_status, Toss_win , match_status, teamA, teamB, venue;
     private String matchPudhId, firstTeamScore, secondTeamScore ,batting_first;
    private long match_id;


    public MatchDetails() {
    }

    public MatchDetails(String datetime, String match_type, String current_status, String toss_win, String match_status, String teamA, String teamB, String venue, String matchPudhId, String firstTeamScore, String secondTeamScore, String batting_first, long match_id) {
        Datetime = datetime;
        Match_type = match_type;
        this.current_status = current_status;
        Toss_win = toss_win;
        this.match_status = match_status;
        this.teamA = teamA;
        this.teamB = teamB;
        this.venue = venue;
        this.matchPudhId = matchPudhId;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.batting_first = batting_first;
        this.match_id = match_id;
    }

    public String getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(String firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public String getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(String secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }



    public String getDatetime() {
        return this.Datetime;
    }

    public String getMatch_type() {
        return this.Match_type;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public long getMatch_id() {
        return match_id;
    }

    public String getMatch_status() {
        return match_status;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public String getVenue() {
        return venue;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public void setMatch_type(String match_type) {
        Match_type = match_type;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }



    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String  getMatchPudhId() {
        return matchPudhId;
    }

    public void setMatchPudhId(String matchPudhId) {
        this.matchPudhId = matchPudhId;
    }

    public String getToss_win() {
        return Toss_win;
    }

    public void setToss_win(String toss_win) {
        Toss_win = toss_win;
    }

    public String getBatting_first() {
        return batting_first;
    }

    public void setBatting_first(String batting_first) {
        this.batting_first = batting_first;
    }
}
