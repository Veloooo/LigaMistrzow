package javaapplication3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Match {

    private Team home;
    private Team away;
    private ArrayList<Goal> goalsHome;
    private ArrayList<Goal> goalsAway;
    private int time;
    private int currentMinute;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
        goalsHome= new ArrayList<>();
        goalsAway= new ArrayList<>();
        this.time = 90;
        this.currentMinute=0;
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

    public int getTime() {
        return time;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public ArrayList<Goal> getGoals(){
        ArrayList<Goal> goals = new ArrayList<>();
        goals.addAll(goalsHome);
        goals.addAll(goalsAway);
        Collections.sort(goals, new Comparator<Goal>() {
            @Override
            public int compare(Goal o1, Goal o2) {
                return o2.getMinute()-o1.getMinute();
            }
        });
        return goals;
    }

    public void sumUp(){
        int goalsHome = getGoalsHome().size();
        int goalsAway = getGoalsAway().size();
        home.addPlayed();
        away.addPlayed();
        home.addConceded(goalsAway);
        away.addScored(goalsAway);
        away.addConceded(goalsHome);
        home.addScored(goalsHome);
        if (goalsHome > goalsAway) {
            home.addPoints(3);
            home.addWon();
            away.addLost();
        }
        if (goalsAway == goalsHome) {
            home.addPoints(1);
            away.addPoints(1);
            home.addDrawn();
            away.addDrawn();
        }
        if (goalsAway > goalsHome) {
            away.addPoints(3);
            home.addLost();
            away.addWon();
        }
    }

    public void playMatch(){
        System.out.println("          " + home.getName() + "           " + away.getName());
        for(int i=0; i<=time; i++){
            matchEngine();
        }
        sumUp();
    }

    public Goal matchEngine(){
        home.clearRatios();
        away.clearRatios();
        home.getMatchUp();
        away.getMatchUp();
        home.calculateRatio(false);
        away.calculateRatio(false);
        int goal;
        goal = (int) (Math.random() * (away.getTeamDefenseRatio()));
        for (int j = 0; j < 11; j++) {
            for (int k = 0; k < (int) (home.getTeamOffensiveRatio(j) / 40); k++) {
                int shot = (int) (Math.random() * (1000 + away.getTeamDefenseRatio()));
                if (shot == goal) {
                    Goal goal1 = new Goal(home,home.getMatchUp2(j),currentMinute);
                    goalsHome.add(new Goal(home,home.getMatchUp2(j),currentMinute));
                    home.getMatchUp2(j).GoalScored();
                    System.out.println(currentMinute + "'       " + home.getMatchUp2(j).getNumber() + "." + home.getMatchUp2(j).getName() + " " + home.getMatchUp2(j).getSurname());
                    currentMinute++;
                    return goal1;
                }
            }
        }
        goal = (int) (Math.random() * (home.getTeamDefenseRatio() + 1500));
        for (int j = 0; j < 11; j++) {
            for (int k = 0; k < (int) (away.getTeamOffensiveRatio(j) / 50); k++) {
                int shot = (int) (Math.random() * (1500 + home.getTeamDefenseRatio()));
                if (shot == goal) {
                    Goal goal1 = new Goal(away,away.getMatchUp2(j),currentMinute);
                    goalsAway.add(goal1);
                    away.getMatchUp2(j).GoalScored();
                    System.out.println(currentMinute + "'                                 " + away.getMatchUp2(j).getNumber() + "." + away.getMatchUp2(j).getName() + " " + away.getMatchUp2(j).getSurname());
                    currentMinute++;
                    return goal1;
                }
            }
        }
        currentMinute++;
        return null;
    }

    public String[] matchEngineFormatter(){
        Goal goal = matchEngine();
        if(goal != null){
            String team;
            if(goal.getTeam().equals(home)){
                team="Home";
            }
            else team ="Away";
            return new String[]{String.valueOf(goal.getMinute())+"'",String.valueOf(goal.getPlayer().getName()+" "+goal.getPlayer().getSurname()),team};
        }
        else return null;
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
