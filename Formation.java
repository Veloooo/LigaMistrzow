package javaapplication3;

/**
 * Created by Dorota on 2017-04-19.
 */
public class Formation {
    private String name;


    private Position[] formation = new Position[11];

    public String getName() {
        return name;
    }

    public Position getPosition(int index) {
        return formation[index];
    }

    public Formation(String name, Position f0, Position f1, Position f2, Position f3, Position f4, Position f5, Position f6, Position f7, Position f8, Position f9, Position f10) {
        this.name=name;
        this.formation[0] = f0;
        this.formation[1] = f1;
        this.formation[2] = f2;
        this.formation[3] = f3;
        this.formation[4] = f4;
        this.formation[5] = f5;
        this.formation[6] = f6;
        this.formation[7] = f7;
        this.formation[8] = f8;
        this.formation[9] = f9;
        this.formation[10] = f10;
    }
}
