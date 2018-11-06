package javaapplication3;

import java.util.ArrayList;

public class Match {

    private Team home;
    private Team away;
    private ArrayList<Goal> goalsHome;
    private ArrayList<Goal> goalsAway;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
        goalsHome= new ArrayList<>();
        goalsAway= new ArrayList<>();

    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public ArrayList<Goal> getGoalsHome() {
        return goalsHome;
    }

    public ArrayList<Goal> getGoalsAway() {
        return goalsAway;
    }

    private class Goal{
        Team team;
        Player player;
        int minute;

        public Goal(Team team, Player player, int minute) {
            this.team = team;
            this.player = player;
            this.minute = minute;
        }

        public Team getTeam() {
            return team;
        }

        public int getMinute() {
            return minute;
        }
        public Player getPlayer() {
            return player;
        }

    }

}
