package com.example.umair.supercricketliveline.POJOClasses;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TeamPlayers {
    private String   player_name, player_type,
            status,team_id;
    //private Long player_id, Fifties,Wickets ,batting_rank,centuries,bowling_rank, match_played ;
    public TeamPlayers() {
    }

    public TeamPlayers(String player_name, String player_type, String status, Long player_id, Long fifties, Long wickets, Long batting_rank, Long centuries, Long bowling_rank, Long match_played, String team_id) {
        this.player_name = player_name;
        this.player_type = player_type;
        this.status = status;
//        this.player_id = player_id;
//        Fifties = fifties;
//        Wickets = wickets;
//        this.batting_rank = batting_rank;
//        this.centuries = centuries;
//        this.bowling_rank = bowling_rank;
//        this.match_played = match_played;
//        this.team_id = team_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Long getPlayer_id() {
//        return player_id;
//    }
//
//    public void setPlayer_id(Long player_id) {
//        this.player_id = player_id;
//    }
//
//    public Long getFifties() {
//        return Fifties;
//    }
//
//    public void setFifties(Long fifties) {
//        Fifties = fifties;
//    }
//
//    public Long getWickets() {
//        return Wickets;
//    }
//
//    public void setWickets(Long wickets) {
//        Wickets = wickets;
//    }
//
//    public Long getBatting_rank() {
//        return batting_rank;
//    }
//
//    public void setBatting_rank(Long batting_rank) {
//        this.batting_rank = batting_rank;
//    }
//
//    public Long getCenturies() {
//        return centuries;
//    }
//
//    public void setCenturies(Long centuries) {
//        this.centuries = centuries;
//    }
//
//    public Long getBowling_rank() {
//        return bowling_rank;
//    }
//
//    public void setBowling_rank(Long bowling_rank) {
//        this.bowling_rank = bowling_rank;
//    }
//
//    public Long getMatch_played() {
//        return match_played;
//    }
//
//    public void setMatch_played(Long match_played) {
//        this.match_played = match_played;
//    }
//
//    public String getTeam_id() {
//        return team_id;
//    }
//
//    public void setTeam_id(String team_id) {
//        this.team_id = team_id;
//    }
}
