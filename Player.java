package javaapplication3;
/**
 * Created by Dorota on 2016-12-25.
 */

public class Player {

    String name;
    String surname;
    Position position;
    int number;
    int overall;
    int goals=0;
    int task;

    // konstruktor i gettery
    public Player(String name, String surname,Position position, int number, int overall) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.position=position;
        this.overall = overall;
        this.task=5;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setTask(int task) {
        if (task>10) this.task=10;
        else if (task<1) this.task=1;
        else this.task = task;
    }

    public int getTask() {
        return task;
    }

    public int getNumber() {
        return number;
    }

    public int getOverall() {
        return overall;
    }

    public int getGoals() {
        return goals;
    }

    // metoda pozwalająca zwiększyć liczbę goli zdobytych przez zawodnika w przypadku gdy tego dokona
    public void GoalScored(){
        goals++;
    }
}
