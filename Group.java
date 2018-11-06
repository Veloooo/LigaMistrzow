package javaapplication3;
/**
 * Created by Dorota on 2016-12-25.
 */

public class Group {
    int day=1;
    String name;
    Team GroupTeams[]=new Team[4];

    // gettery i konstruktor
    public Team getGroupTeams(int i) {
        return GroupTeams[i];
    }
    public Group(String n, Team t1, Team t2, Team t3, Team t4) {

        GroupTeams[0] = t1;
        GroupTeams[1] = t2;
        GroupTeams[2] = t3;
        GroupTeams[3] = t4;
        name=n;
    }
    public String getName() {
        return name;
    }


    // metoda pozwalajaca rozegrać fazę grupową z zachowaniem harmonogramu używanego oficjalnie
    public int[] GroupStage(int day){
            int score1[]=new int [2];
            int score2[]=new int [2];
            switch(day){
                case 1: score1=GroupTeams[0].PlayMatch(GroupTeams[2],90); score2=GroupTeams[1].PlayMatch(GroupTeams[3],90) ; break;
                case 2: score1=GroupTeams[2].PlayMatch(GroupTeams[1],90); score2=GroupTeams[3].PlayMatch(GroupTeams[0],90) ; break;
                case 3: score1=GroupTeams[0].PlayMatch(GroupTeams[1],90); score2=GroupTeams[2].PlayMatch(GroupTeams[3],90) ; break;
                case 4: score1=GroupTeams[1].PlayMatch(GroupTeams[0],90); score2=GroupTeams[3].PlayMatch(GroupTeams[2],90) ; break;
                case 5: score1=GroupTeams[2].PlayMatch(GroupTeams[0],90); score2=GroupTeams[3].PlayMatch(GroupTeams[1],90) ; break;
                case 6: score1=GroupTeams[1].PlayMatch(GroupTeams[2],90); score2=GroupTeams[0].PlayMatch(GroupTeams[3],90); break;
                default: System.out.println("Group stage is over. Here are the standings:"); Show(); break;
               }
            int scores[]=new int[4];
            scores[0]=score1[0];
            scores[1]=score1[1];
            scores[2]=score2[0];
            scores[3]=score2[1];
            return scores;
    }
    // metoda wyświetlająca sytuację w grupie łącznie z uszeregowaniem zespołów w kolejności malejącej według punktów, następnie bilansu bramkowego, ilości strzelonych goli
    public void Show(){
        Team table[]= new Team[5];
        table[0]=GroupTeams[0];
        table[1]=GroupTeams[1];
        table[2]=GroupTeams[2];
        table[3]=GroupTeams[3];
        int j=1;
        for (int i=0; i<4; i++){
            while(j<4) {
                if (table[i].getPoints() < table[j].getPoints()) {
                    table[4] = table[i];
                    table[i] = table[j];
                    table[j] = table[4];
                }
                if (table[i].getPoints() == table[j].getPoints()) {
                    if(table[i].getScored()-table[i].getConceded() < table[j].getScored()-table[j].getConceded()) {
                        table[4] = table[i];
                        table[i] = table[j];
                        table[j] = table[4];
                    }
                    if(table[i].getScored()-table[i].getConceded() == table[j].getScored()-table[j].getConceded()){
                        if(table[i].getScored() < table[j].getScored()){
                            table[4] = table[i];
                            table[i] = table[j];
                            table[j] = table[4];
                        }
                    }
                }
                j++;
            }
            j=1+i;
        }
        System.out.println("Group "+getName());
        System.out.println(" #  Team Name                 P  W D L   S   C    P");
        System.out.println(" 1. "+table[0].getName()+"         "+table[0].getPlayed()+"  "+table[0].getWon()+" "+table[0].getDrawn()+" "+table[0].getLost()+"   "+table[0].getScored()+"   "+table[0].getConceded()+"    "+table[0].getPoints() );
        System.out.println(" 2. "+table[1].getName()+"         "+table[1].getPlayed()+"  "+table[1].getWon()+" "+table[1].getDrawn()+" "+table[1].getLost()+"   "+table[1].getScored()+"   "+table[1].getConceded()+"    "+table[1].getPoints() );
        System.out.println(" 3. "+table[2].getName()+"         "+table[2].getPlayed()+"  "+table[2].getWon()+" "+table[2].getDrawn()+" "+table[2].getLost()+"   "+table[2].getScored()+"   "+table[2].getConceded()+"    "+table[2].getPoints() );
        System.out.println(" 4. "+table[3].getName()+"         "+table[3].getPlayed()+"  "+table[3].getWon()+" "+table[3].getDrawn()+" "+table[3].getLost()+"   "+table[3].getScored()+"   "+table[3].getConceded()+"    "+table[3].getPoints() );

        GroupTeams[0]=table[0];
        GroupTeams[1]=table[1];
        GroupTeams[2]=table[2];
        GroupTeams[3]=table[3];
    }
    // metoda pozwalająca rozlosować grupy, tak aby dwa zespoły z tego samego państwa nie spotkały się ze sobą na etapie rozgrywek grupowych
    public void Randomo(Team t1[]){

        int j=1;
        int k=1;
        int counter=1;
        int max=2;
        int index=0;
        int iter=0;

        while(max>1) {
        for (int i=iter; i<16; i++) {
            max=0;
            counter=1;
            k = 1;
            j = i + 1;
            while (j < 16) {
                if (t1[i].getNationality() == t1[j].getNationality()) {
                    t1[16] = t1[i + k];
                    t1[i + k] = t1[j];
                    t1[j] = t1[16];
                    k++;
                }
                j++;
            }
        }


            for (int i =iter; i < 16; i++) {
                if (t1[i].getNationality() == t1[i + 1].getNationality()) {
                    counter++;
                    if (counter > max) {
                        max = counter;
                        index = i + 1;
                    }
                } else counter = 1;
            }

            for (int i =0; i < max; i++) {
                t1[16] = t1[iter+i];
                t1[iter+i] = t1[index-i];
                t1[index-i] = t1[16];
            }

            iter = iter + max;

        }

        for(int i=0; i<4; i++){
           int a= (int)((Math.random()*4)-1);
           t1[16]=t1[i];
           t1[i]=t1[a];
           t1[a]=t1[16];
       }
       for(int i=4; i<7; i++){
            int a= (int)((Math.random()*3)+4);
            t1[16]=t1[i];
            t1[i]=t1[a];
            t1[a]=t1[16];
        }
        for(int i=7; i<10; i++){
            int a= (int)((Math.random()*3)+7);
            t1[16]=t1[i];
            t1[i]=t1[a];
            t1[a]=t1[16];
        }
        for(int i=10; i<12; i++){
            int a= (int)((Math.random()*2)+10);
            t1[16]=t1[i];
            t1[i]=t1[a];
            t1[a]=t1[16];
        }
        for(int i=12; i<14; i++){
            int a= (int)((Math.random()*2)+12);
            t1[16]=t1[i];
            t1[i]=t1[a];
            t1[a]=t1[16];
        }
        for (int i=0; i<16; i++) {
            k = 1;
            j = i + 1;
            while (j < 16) {
                if (t1[i].getNationality() == t1[j].getNationality()) {
                    t1[16] = t1[i + k];
                    t1[i + k] = t1[j];
                    t1[j] = t1[16];
                    k++;
                }
                j++;
            }
        }

    }
}

/*   public void Randomize(Team t1[]){

        for (int i=1; i<17; i++){
            int a=(int)((Math.random()*16)-1);
            t1[16]=t1[i];
            t1[i]=t1[a];
            t1[a]=t1[16];

        }


        for (int k=0; k<4 ;k++){
            for (int i = 0; i < 3; i++) {
                int j = 1+ (4*k);
                while (j < 4+4*k) {
                    if (t1[i+4*k].getNationality() == t1[j].getNationality()) {
                        int a = (int) (Math.random() * 16);
                        t1[16] = t1[j];
                        t1[j] = t1[a];
                        t1[a] = t1[16];
                    }j++;
                }
            }
        }
    }*/