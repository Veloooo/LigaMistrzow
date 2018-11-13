package javaapplication3;

import java.util.ArrayList;

public class Match {

    private Team home;
    private Team away;
    private ArrayList<Goal> goalsHome;
    private ArrayList<Goal> goalsAway;
    private int time;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
        goalsHome= new ArrayList<>();
        goalsAway= new ArrayList<>();
        this.time = 90;
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

    public void playMatch(){
        home.clearRatios();
        away.clearRatios();
        System.out.println("          " + home.getName() + "           " + away.getName());
        int GoalsHome = 0;
        int GoalsAway = 0;
        ArrayList<Integer> minutes =new ArrayList<>();
        ArrayList<Integer> teamGoal= new ArrayList<>();
        ArrayList<Integer> scorers=new ArrayList<>();
        home.getMatchUp();
        away.getMatchUp();
        home.calculateRatio(false);
        away.calculateRatio(false);
        System.out.println(away.getTeamDefenseRatio()+"    "+home.getTeamDefenseRatio());
        int goal;
        for (int i = 1; i < time + 1; i++) {
            goal = (int) (Math.random() * (away.getTeamDefenseRatio()));
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < (int) (home.getTeamOffensiveRatio(j) / 40); k++) {
                    int shot = (int) (Math.random() * (1000 + away.getTeamDefenseRatio()));
                    if (shot == goal) {
                        goalsHome.add(new Goal(home,home.getMatchUp2(j),i));
                        GoalsHome++;
                        minutes.add(i);
                        teamGoal.add(1);
                        scorers.add(j);
                        home.getMatchUp2(j).GoalScored();
                        System.out.println(i + "'       " + home.getMatchUp2(j).getNumber() + "." + home.getMatchUp2(j).getName() + " " + home.getMatchUp2(j).getSurname());
                        i++;
                    }
                }
            }
            goal = (int) (Math.random() * (home.getTeamDefenseRatio() + 1500));
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < (int) (away.getTeamOffensiveRatio(j) / 50); k++) {
                    int shot = (int) (Math.random() * (1500 + home.getTeamDefenseRatio()));
                    if (shot == goal) {
                        goalsAway.add(new Goal(away,away.getMatchUp2(j),i));
                        minutes.add(i);
                        teamGoal.add(2);
                        scorers.add(j);
                        GoalsAway++;
                        away.getMatchUp2(j).GoalScored();
                        System.out.println(i + "'                                 " + away.getMatchUp2(j).getNumber() + "." + away.getMatchUp2(j).getName() + " " + away.getMatchUp2(j).getSurname());
                        i++;
                    }
                }
            }


        }

        System.out.println(GoalsHome + ":" + GoalsAway + "\n ");


        home.addPlayed();
        away.addPlayed();
        home.addConceded(GoalsAway);
        away.addScored(GoalsAway);
        away.addConceded(GoalsHome);
        home.addScored(GoalsHome);
        if (GoalsHome > GoalsAway) {
            home.addPoints(3);
            home.addWon();
            away.addLost();
        }
        if (GoalsAway == GoalsHome) {
            home.addPoints(1);
            away.addPoints(1);
            home.addDrawn();
            away.addDrawn();
        }
        if (GoalsAway > GoalsHome) {
            away.addPoints(3);
            home.addLost();
            away.addWon();
        }


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
