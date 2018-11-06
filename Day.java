package javaapplication3;
import java.util.ArrayList;

public class Day {
    private int number;
    private ArrayList<Match> matches;

    public Day(int number, ArrayList<Match> matches) {
        this.number = number;
        this.matches = matches;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Match m){
        matches.add(m);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void printDay(){
        System.out.println("Gameweek "+ number);
        for(Match m : matches){
            System.out.println(m.getHome().getName() + " " + m.getGoalsHome().size()+ " "+ m.getGoalsAway().size()+ " "+m.getAway().getName());
        }
    }
}
