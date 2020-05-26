package com.example.umair.supercricketliveline.POJOClasses;

public class Feed {

    private Long feeds_team1,feeds_team2,feeds_session1, feeds_session2 , feeds_balls,feeds_runs;
    private String label_runxball,label_session,label_teams;
    public Feed() {
    }

    public Long getFeeds_team1() {
        return feeds_team1;
    }

    public String getLabel_runxball() {
        return label_runxball;
    }

    public void setLabel_runxball(String label_runxball) {
        this.label_runxball = label_runxball;
    }

    public String getLabel_session() {
        return label_session;
    }

    public void setLabel_session(String label_session) {
        this.label_session = label_session;
    }

    public String getLabel_teams() {
        return label_teams;
    }

    public void setLabel_teams(String label_teams) {
        this.label_teams = label_teams;
    }

    public void setFeeds_team1(Long feeds_team1) {
        this.feeds_team1 = feeds_team1;
    }

    public Long getFeeds_team2() {
        return feeds_team2;
    }

    public void setFeeds_team2(Long feeds_team2) {
        this.feeds_team2 = feeds_team2;
    }

    public Long getFeeds_session1() {
        return feeds_session1;
    }

    public void setFeeds_session1(Long feeds_session1) {
        this.feeds_session1 = feeds_session1;
    }

    public Long getFeeds_session2() {
        return feeds_session2;
    }

    public void setFeeds_session2(Long feeds_session2) {
        this.feeds_session2 = feeds_session2;
    }

    public Long getFeeds_balls() {
        return feeds_balls;
    }

    public void setFeeds_balls(Long feeds_balls) {
        this.feeds_balls = feeds_balls;
    }

    public Long getFeeds_runs() {
        return feeds_runs;
    }

    public void setFeed_runs(Long feeds_runs) {
        this.feeds_runs = feeds_runs;
    }
}
