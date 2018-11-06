package javaapplication3;

import java.util.ArrayList;

/**
 * Created by Dorota on 2016-12-25.
 */

public class Team {

    private String name;
    private String nationality;
    private int overall;
    private ArrayList<Player> PlayerList= new ArrayList<>();
    private Player[] MatchUp=new Player[11];
    private Player[] Substitutions = new Player[7];
    private Formation formation;
    private int played=0;
    private int won=0;
    private int drawn=0;
    private int lost=0;
    private int scored=0;
    private int conceded=0;
    private int points=0;
    private int TeamOffensiveRatio[]=new int[11];
    private int TeamDefenseRatio=0;

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    //gettery i podstawowe funkcje zmieniające parametry
    public int getTeamOffensiveRatio(int n) {
        return TeamOffensiveRatio[n];
    }
    public int getTeamDefenseRatio() {
        return TeamDefenseRatio;
    }
    public int getPlayed() {
        return played;
    }
    public int getWon() {
        return won;
    }
    public int getDrawn() {
        return drawn;
    }
    public int getLost() {
        return lost;
    }
    public int getScored() {
        return scored;
    }
    public int getConceded() {
        return conceded;
    }
    public int getPoints() {
        return points;
    }
    public void Clear(){
        scored=0;
        conceded=0;
    }
    public Player getSub(int index){
        return Substitutions[index];
    }
    public Player getMatchUp2(int index) {
        return MatchUp[index];
    }
    public Player[] getMatchUp() {
        return MatchUp;
    }
    public void addPlayer(Player p) {
        this.PlayerList.add(p);
        for (int i=0; i<Substitutions.length; i++)
            if (Substitutions[i]==null) {
                Substitutions[i] = p;
                break;
            }
    }
    public String getName() {
        return name;
    }
    public String getNationality() {
        return nationality;
    }
    public void addPoints(int x){
        points=points+x;
    }
    public void addScored(int x){
        scored=scored+x;
    }
    public void addConceded(int x){
        conceded=conceded+x;
    }
    public void addWon(){
        won++;
    }
    public void addDrawn(){
        drawn++;
    }
    public void addLost(){
        lost++;
    }
    public void addPlayed(){
        played++;
    }
    public Formation getFormation(){
        return this.formation;
    }
    // funkcja pozwalająca na dokonasnie zmiany
    public void substitution(Player p1, Player p2) {

        int index = 100;
        Player buf;
        for (int i = 0; i < Substitutions.length; i++) {
            if (this.Substitutions[i] != null) {
                if (this.Substitutions[i].hashCode() == p1.hashCode()) {
                    index = i;
                    break;
                }
            }
        }
        if (index == 100) {
            System.out.println("Player not found!");
            return;
        }
        boolean check=false;
        for (int i = 0; i < 11; i++) {
            if (this.MatchUp[i].hashCode()==p2.hashCode()) {
                buf = MatchUp[i];
                MatchUp[i] = Substitutions[index];
                Substitutions[index] = buf;
                System.out.println("\n\nSubstitution: "+Substitutions[index].getName()+" "+Substitutions[index].getSurname()+" ----> out");
                System.out.println("              "+MatchUp[i].getName()+" "+MatchUp[i].getSurname()+" <---- in");
                check=true;
                break;
            }

        }
        if (check==false) System.out.println("Player not found!");
        else calculateRatio(false);
    }
    // funkcja wyświetlająca drużyne
    public void showTeam(){
        System.out.println("\n \nMatch up:");

        for (int i=0; i<11; i++){
            System.out.println(MatchUp[i].getNumber()+"."+MatchUp[i].getName()+" "+MatchUp[i].getSurname() + " "+ this.formation.getPosition(i).getWing()+this.formation.getPosition(i).getLine());
        }
        System.out.println("\n \nSubstitutions:");

       for (int i=0; i<7; i++){
           if (Substitutions[i]!=null)
            System.out.println(this.Substitutions[i].getNumber()+"."+this.Substitutions[i].getName()+" "+this.Substitutions[i].getSurname());
           else System.out.println("---||---");
       }
        System.out.println("\n \n");
    }
    // ustawianie zawodników do formacji
    public void setUp(boolean visibility){
        Player z= new Player("","",new Position("","",""),0,0);
        ArrayList <Integer> notUsedPosition= new ArrayList<>();
        ArrayList <Integer> foundPosition= new ArrayList<>();
        Player[] buf= new Player[11];
        ArrayList <Player> missings = new ArrayList<>();
        boolean[] formation_flags = new boolean[11];
        boolean found=false;
        int checker=0;
        int counter=0;
        for(int i=0 ; i<11; i++){
            buf[i]=MatchUp[i];
            this.MatchUp[i]=z;
        }

        for (int i=0; i<11; i++){
            for(int j=0; j<11; j++) {
                if (buf[i].getPosition() == formation.getPosition(j)) {
                    if (formation_flags[j] == false) {
                        MatchUp[j] = buf[i];
                        formation_flags[j] = true;
                        found =true;
                  //      System.out.println("Znaleziono pozycję dla gracza: "+MatchUp[j].getName()+MatchUp[j].getSurname());
                        break;
                    }
              //      System.out.println("Znaleziono pozycję dla gracza: "+buf[i].getName()+buf[i].getSurname()+" ale jest zajęta, szukam dalej");
                }
            }
            if (found==false){
                missings.add(buf[i]);
         //       System.out.println("Nie znaleziono pozycji dla gracza: "+buf[i].getName()+" "+buf[i].getSurname());
            }
            found =false;
        }


        for (int i=0 ; i<11; i++){
            // System.out.println(MatchUp[i].getNumber()+"."+MatchUp[i].getName()+" "+MatchUp[i].getSurname()+formation.getPosition(i).getWing()+formation.getPosition(i).getLine());
            if(formation_flags[i]==false){
                notUsedPosition.add(i);
            }
        }

        for(int i=0; i<notUsedPosition.size(); i++){
            formation_flags[i]=false;
        }
        if (visibility)
        System.out.println("Ustawiono nasępujący skład do formacji:" + this.formation.getName());
        if (missings.size()==0) {
            if (visibility==true) {
                showTeam();
            }
            return;
        }


        for(int i=0; i<notUsedPosition.size(); i++){
            for(int j=0 ; j<notUsedPosition.size(); j++) {
                if (missings.get(i).getPosition().getTask() == formation.getPosition((notUsedPosition.get(j))).getTask() && formation_flags[j]==false){
                    MatchUp[(notUsedPosition.get(j))]=missings.get(i);
                    formation_flags[j]=true;
                    found=true;
                    break;
                }
            }
            if(found==false){
                missings.add(missings.get(i));
            }
            found=false;
            }
        for(int i=0; i<11; i++){
        //    System.out.println(MatchUp[i].getNumber()+"."+MatchUp[i].getName()+" "+MatchUp[i].getSurname()+formation.getPosition(i).getWing()+formation.getPosition(i).getLine());
            if(MatchUp[i].getSurname()=="" && MatchUp[i].getName()==""){
                checker++;
            }
        }
        if (checker==11){
            if (visibility==true) {
                showTeam();
            }
            return;
        }


        checker=0;
        counter=notUsedPosition.size();
        for(int i=0; i<counter; i++){
            missings.remove(0);
            notUsedPosition.remove(0);
        }

        for (int i=0 ; i<11; i++){
            if(MatchUp[i].getSurname()=="" && MatchUp[i].getName()==""){
                notUsedPosition.add(i);
            }
        }

        for(int i=0; i<notUsedPosition.size(); i++){
            formation_flags[i]=false;
        }
        for(int i=0; i<notUsedPosition.size(); i++){
            for(int j=0 ; j<notUsedPosition.size(); j++) {
                if (missings.get(i).getPosition().getLine() == formation.getPosition((notUsedPosition.get(j))).getLine() && formation_flags[j]==false){
                    MatchUp[(notUsedPosition.get(j))]=missings.get(i);
                    formation_flags[j]=true;
                    found=true;
                    break;
                }
            }
            if(found==false){
                missings.add(missings.get(i));
            }
            found=false;
        }
        for(int i=0; i<11; i++){
            if(MatchUp[i].getSurname()!=""){
                checker++;
            }
        }
        if (checker==11){
            if (visibility==true) {
                showTeam();
            }
            return;
        }


        checker=0;
        counter=notUsedPosition.size();
        for(int i=0; i<counter; i++){
            missings.remove(0);
            notUsedPosition.remove(0);
        }


        for (int i=0 ; i<11; i++){
     //       System.out.println(MatchUp[i].getNumber()+"."+MatchUp[i].getName()+" "+MatchUp[i].getSurname()+formation.getPosition(i).getWing()+formation.getPosition(i).getLine());
            if(MatchUp[i].getSurname()=="" && MatchUp[i].getName()==""){
                notUsedPosition.add(i);
            }
        }

        for(int i=0; i<notUsedPosition.size(); i++){
            formation_flags[i]=false;
        }
        for(int i=0; i<notUsedPosition.size(); i++){
            for(int j=0 ; j<notUsedPosition.size(); j++) {
                if (missings.get(i).getPosition().getWing() == formation.getPosition((notUsedPosition.get(j))).getWing() && formation_flags[j]==false){
                    MatchUp[(notUsedPosition.get(j))]=missings.get(i);
                    formation_flags[j]=true;
                    found=true;
                    break;
                }

            }
            if(found==false){
                missings.add(missings.get(i));
            }
            found=false;
        }
        for(int i=0; i<11; i++){
            if(MatchUp[i].getSurname()!=""){
                checker++;
            }
        }
        if (checker==11){
            if (visibility==true) {
                showTeam();
            }
            return;
        }



        counter=notUsedPosition.size();
        for(int i=0; i<counter; i++){
            missings.remove(0);
            notUsedPosition.remove(0);
        }
        for (int i=0 ; i<11; i++){
            if(MatchUp[i].getSurname()=="" && MatchUp[i].getName()==""){
                notUsedPosition.add(i);
            }
        }

        for(int i=0; i<notUsedPosition.size(); i++){
            formation_flags[i]=false;
        }
        for(int i=0; i<notUsedPosition.size(); i++){
           // System.out.println("Szukanie pozycji dla zawodnika: "+missings.get(i).getName()+" "+missings.get(i).getSurname());
            for(int j=0 ; j<notUsedPosition.size(); j++) {
                if (formation_flags[j]==false){
                    MatchUp[(notUsedPosition.get(j))]=missings.get(i);
                  //  System.out.println("Znaleziono! Pozycja "+notUsedPosition.get(j)+"dla "+missings.get(i).getName()+" "+missings.get(i).getSurname());
                    formation_flags[j]=true;
                    found=true;
                    break;
                }
             //   System.out.println("Pozycja zajęta, szukam innej");
            }
            if(found==false){
             //   System.out.println("Nie znaleziono pozycji dla " + missings.get(i).getName() + " " + missings.get(i).getSurname());
                missings.add(missings.get(i));
            }
            found=false;
        }

        if (visibility==true) {
            showTeam();
        }


    }
    // konstruktor
    public Team(String name, String nationality,Formation form, Player p1, Player p2, Player p3, Player p4, Player p5, Player p6, Player p7, Player p8, Player p9, Player p10, Player p11) {

        this.formation=form;
        this.name = name;
        this.nationality = nationality;
        this.overall = (int)(p1.getOverall()+p2.getOverall()+p3.getOverall()+p4.getOverall()+p5.getOverall()+p6.getOverall()+p7.getOverall()+p8.getOverall()+p9.getOverall()+p10.getOverall()+p11.getOverall())/11;
        this.PlayerList.add(p1);
        this.PlayerList.add(p2);
        this.PlayerList.add(p3);
        this.PlayerList.add(p4);
        this.PlayerList.add(p5);
        this.PlayerList.add(p6);
        this.PlayerList.add(p7);
        this.PlayerList.add(p8);
        this.PlayerList.add(p9);
        this.PlayerList.add(p10);
        this.PlayerList.add(p11);
        this.MatchUp[0]= this.PlayerList.get(0);
        this.MatchUp[1]= this.PlayerList.get(1);
        this.MatchUp[2]= this.PlayerList.get(2);
        this.MatchUp[3]= this.PlayerList.get(3);
        this.MatchUp[4]= this.PlayerList.get(4);
        this.MatchUp[5]= this.PlayerList.get(5);
        this.MatchUp[6]= this.PlayerList.get(6);
        this.MatchUp[7]= this.PlayerList.get(7);
        this.MatchUp[8]= this.PlayerList.get(8);
        this.MatchUp[9]= this.PlayerList.get(9);
        this.MatchUp[10]= this.PlayerList.get(10);
        for (int i=0; i<11; i++) TeamOffensiveRatio[i]=0;


    }
    // obliczanie wartości ofensywnych zawodników i całkowitą wartość obrony drużyny
    public int calculateRatio(boolean visibility){
        int [] PlayerOveralls= new int[11];
        int Overalll=0;
        if (visibility)
        System.out.println("Ratios calculated: ");
            for(int i=0; i<11 ; i++) {
                if (MatchUp[i].getPosition().getLine() == formation.getPosition(i).getLine()) {
                    if (MatchUp[i].getPosition().getWing() == formation.getPosition(i).getWing()) {
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=MatchUp[i].getOverall();
                        }
                        else if ( formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E")  {
                            PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.9);
                        }
                        else{
                            PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.8);
                        }
                    }
                    else if(MatchUp[i].getPosition().getWing() !="C" && formation.getPosition(i).getWing() !="C"){
                        PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.95);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=PlayerOveralls[i];
                        }
                        else if ( MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E") ) {
                            PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        }
                        else PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                    }
                    else {
                        PlayerOveralls[i] = (int) (MatchUp[i].getOverall() * 0.9);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i] = PlayerOveralls[i];
                        } else if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask() == "E" || MatchUp[i].getPosition().getTask() == "E")) {
                            PlayerOveralls[i] = (int) (PlayerOveralls[i] * 0.9);
                        } else PlayerOveralls[i] = (int) (PlayerOveralls[i] * 0.9);
                    }
                }
                else if (MatchUp[i].getPosition().getLine()=="M" || formation.getPosition(i).getLine()=="M") {
                    PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.9);
                    if (MatchUp[i].getPosition().getWing() == formation.getPosition(i).getWing()) {
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=PlayerOveralls[i];
                        }
                        else if ( MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E") ) {
                            PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        }
                        else PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                    }
                    else if(MatchUp[i].getPosition().getWing() !="C" && formation.getPosition(i).getWing() !="C"){
                        PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.9);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=PlayerOveralls[i];
                        }
                        else if ( MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E") ) {
                            PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        }
                        else PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                    }
                    else {
                        PlayerOveralls[i] = (int) (MatchUp[i].getOverall() * 0.9);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i] = PlayerOveralls[i];
                        } else if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask() == "E" || MatchUp[i].getPosition().getTask() == "E")) {
                            PlayerOveralls[i] = (int) (PlayerOveralls[i] * 0.9);
                        } else PlayerOveralls[i] = (int) (PlayerOveralls[i] * 0.9);
                    }
                }
                else {
                    PlayerOveralls[i]=(int)(MatchUp[i].getOverall()*0.65);
                    if (MatchUp[i].getPosition().getWing() == formation.getPosition(i).getWing()) {
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=PlayerOveralls[i];
                        }
                        else if ( MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E") ) {
                            PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        }
                        else PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                    }
                    else if(MatchUp[i].getPosition().getWing() !="C" && formation.getPosition(i).getWing() !="C"){
                        PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i]=PlayerOveralls[i];
                        }
                        else if ( MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask()=="E" ||MatchUp[i].getPosition().getTask()=="E") ) {
                            PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                        }
                        else PlayerOveralls[i]=(int)(PlayerOveralls[i]*0.9);
                    }
                    else {
                        PlayerOveralls[i] = (int) (MatchUp[i].getOverall() * 0.8);
                        if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask()) {
                            PlayerOveralls[i] = PlayerOveralls[i];
                        } else if (MatchUp[i].getPosition().getTask() == formation.getPosition(i).getTask() && (formation.getPosition(i).getTask() == "E" || MatchUp[i].getPosition().getTask() == "E")) {
                            PlayerOveralls[i] = (int) (PlayerOveralls[i] * 0.9);
                        } else PlayerOveralls[i] = (int) (PlayerOveralls[i]* 0.8);
                    }
                }
            }
            for (int i=0; i<11 ; i++){
                Overalll+=PlayerOveralls[i];
                switch(MatchUp[i].getPosition().getWing()+MatchUp[i].getPosition().getLine()+MatchUp[i].getPosition().getTask()){
                    case "CGKD": TeamOffensiveRatio[i]=1; TeamDefenseRatio+=(PlayerOveralls[i]*15); break;
                    case "CBD": TeamOffensiveRatio[i]=PlayerOveralls[i]*2; TeamDefenseRatio+=(PlayerOveralls[i]*7);break;
                    case "RBD": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*2.5); TeamDefenseRatio+=(PlayerOveralls[i]*6);break;
                    case "LBD": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*2.5); TeamDefenseRatio+=(PlayerOveralls[i]*6);break;
                    case "CMD": TeamOffensiveRatio[i]=PlayerOveralls[i]*4; TeamDefenseRatio+=(PlayerOveralls[i]*5);break;
                    case "CME": TeamOffensiveRatio[i]=PlayerOveralls[i]*5; TeamDefenseRatio+=(PlayerOveralls[i]*4);break;
                    case "CMO": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*6.5); TeamDefenseRatio+=(int)(PlayerOveralls[i]*2.5);break;
                    case "CAO": TeamOffensiveRatio[i]=PlayerOveralls[i]*9; TeamDefenseRatio+=PlayerOveralls[i];break;
                    case "RAO": TeamOffensiveRatio[i]=PlayerOveralls[i]*9; TeamDefenseRatio+=(PlayerOveralls[i]);break;
                    case "LAO": TeamOffensiveRatio[i]=PlayerOveralls[i]*9; TeamDefenseRatio+=(PlayerOveralls[i]);break;
                    case "LBE": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*4.5); TeamDefenseRatio+=(int)(PlayerOveralls[i]*4.5);break;
                    case "RBE": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*4.5); TeamDefenseRatio+=(int)(PlayerOveralls[i]*4.5);break;
                    case "RME": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*5.5); TeamDefenseRatio+=(int)(PlayerOveralls[i]*3.5);break;
                    case "LME": TeamOffensiveRatio[i]=(int)(PlayerOveralls[i]*5.5); TeamDefenseRatio+=(int)(PlayerOveralls[i]*3.5);break;
                }
                if (visibility)
                System.out.println(MatchUp[i].getName()+" "+MatchUp[i].getSurname()+" original overall:  "+MatchUp[i].getPosition().getWing()+MatchUp[i].getPosition().getLine()+MatchUp[i].getOverall()+"    overall after:         "+PlayerOveralls[i]+" "+formation.getPosition(i).getWing()+formation.getPosition(i).getLine());
            }

            return Overalll;
    }
    // szukanie najlepszego możliwego ustawienia
    public void bestSetUp(ArrayList <Formation> s, boolean visibility){
        int best=0;
        int index=0;
        for(int i=0; i<s.size() ; i++) {
            if (visibility)
            System.out.println("Setting formation: "+s.get(i).getName());
            this.setFormation(s.get(i));
            this.setUp(false);
            int ovrl= this.calculateRatio(visibility);
            if (visibility)
            System.out.println("\n\nFormation:"+this.formation.getName()+" overall: "+ovrl+"\n");
            if (ovrl > best){
                best = ovrl;
                index = i;
            }
            if (visibility)
                System.out.println("Current best ratio: "+best+" formation:"+s.get(index).getName());
        }
        this.setFormation(s.get(index));
        this.setUp(false);
        if (visibility)
            System.out.println("The best formation for team: "+this.getName()+"is: "+this.formation.getName());
    }
    // zamiana graczy pozycjami
    public void swap(Player p1, Player p2){
        Player buf;
        int index=0;
        for (int i=0; i<11; i++){
            if(MatchUp[i].hashCode()==p1.hashCode()) {
                index = i;
                break;
            }
        }
        for (int i=index+1; i<11 ; i++){
            if(MatchUp[i].hashCode()==p2.hashCode()) {
                buf=MatchUp[i];
                MatchUp[i]=MatchUp[index];
                MatchUp[index]=buf;
            }
        }
        calculateRatio(false);

    }
    // wyczyszczenie wartości ofensywnych i defensywnych drużyny
    public void clearRatios(){ for (int i=0; i<11; i++) TeamOffensiveRatio[i]=0; TeamDefenseRatio=0;}
    //metoda odpowiedzialna za rozgrywanie meczy między dwoma zespołami, parametr time ustala jak długo trwa mecz
    public int[] PlayMatch(Team Away,int Time){
        clearRatios();
        Away.clearRatios();
        System.out.println("          " + getName() + "           " + Away.getName());
        int GoalsHome = 0;
        int GoalsAway = 0;
        ArrayList<Integer> minutes =new ArrayList<>();
        ArrayList<Integer> teamGoal= new ArrayList<>();
        ArrayList<Integer> scorers=new ArrayList<>();
        getMatchUp();
        Away.getMatchUp();
        calculateRatio(false);
        Away.calculateRatio(false);
        System.out.println(Away.getTeamDefenseRatio()+""+getTeamDefenseRatio());
        int goal;
        for (int i = 1; i < Time + 1; i++) {
            goal = (int) (Math.random() * (Away.getTeamDefenseRatio()));
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < (int) (getTeamOffensiveRatio(j) / 40); k++) {
                    int shot = (int) (Math.random() * (1000 + Away.getTeamDefenseRatio()));
                    if (shot == goal) {
                        GoalsHome++;
                        minutes.add(i);
                        teamGoal.add(1);
                        scorers.add(j);
                        MatchUp[j].GoalScored();
                        System.out.println(i + "'       " + MatchUp[j].getNumber() + "." + MatchUp[j].getName() + " " + MatchUp[j].getSurname());
                        i++;
                    }
                }
            }
            goal = (int) (Math.random() * (getTeamDefenseRatio() + 1500));
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < (int) (getTeamOffensiveRatio(j) / 50); k++) {
                    int shot = (int) (Math.random() * (1500 + getTeamDefenseRatio()));
                    if (shot == goal) {
                        minutes.add(i);
                        teamGoal.add(2);
                        scorers.add(j);
                        GoalsAway++;
                        Away.MatchUp[j].GoalScored();
                        System.out.println(i + "'                                 " + Away.MatchUp[j].getNumber() + "." + Away.MatchUp[j].getName() + " " + Away.MatchUp[j].getSurname());
                        i++;
                    }
                }
            }


        }

        System.out.println(GoalsHome + ":" + GoalsAway + "\n ");


        addPlayed();
        Away.addPlayed();
        addConceded(GoalsAway);
        Away.addScored(GoalsAway);
        Away.addConceded(GoalsHome);
        addScored(GoalsHome);
        if (GoalsHome > GoalsAway) {
            addPoints(3);
            addWon();
            Away.addLost();
        }
        if (GoalsAway == GoalsHome) {
            addPoints(1);
            Away.addPoints(1);
            addDrawn();
            Away.addDrawn();
        }
        if (GoalsAway > GoalsHome) {
            Away.addPoints(3);
            addLost();
            Away.addWon();
        }

        Integer[] mins=minutes.toArray(new Integer[minutes.size()]);
        Integer[] players=scorers.toArray(new Integer[scorers.size()]);
        Integer[] teamscored=teamGoal.toArray(new Integer[teamGoal.size()]);
        int score[]=new int[3*mins.length];
        for (int i=0; i<mins.length; i++){
            score[i*3]=mins[i];
            score[i*3+1]=players[i];
            score[i*3+2]=teamscored[i];
        }
        return score;
    }
    // metoda pozwalająca na rozegranie konkursu rzutów karnych
    public void PenaltyShootout(Team t1){
        System.out.println("Penalty shootout");

        getMatchUp();
        t1.getMatchUp();

        int HomeTeamGoalkeeperPenaltySkill=MatchUp[0].getOverall();
        int AwayTeamGoalkeeperPenaltySkill=t1.MatchUp[0].getOverall();
        int HomeTeamGoals=0;
        int AwayTeamGoals=0;
        int a = (int) (Math.random() * 100);
        int HomeTeamAttempts=0;
        int AwayTeamAttempts=0;
        for (int k=0; k<5; k++) {
                System.out.print("\n"+(k+1)+"."+MatchUp[10-k].getName()+" "+MatchUp[10-k].getSurname());
                int power = 110 + (MatchUp[10-k].getOverall() - AwayTeamGoalkeeperPenaltySkill);
                int shot = 0;
                int j = 0;
                while (shot != a && j < power) {
                    shot = (int) (Math.random()* 100);
                    if (shot == a) {
                        HomeTeamGoals++;
                        System.out.print(" (Scored)");
                        shot=140;
                        a=140;
                    }
                    j++;
                }
                if(shot!=140){
                    System.out.print(" (Missed)");
                }
                HomeTeamAttempts++;
                if((HomeTeamGoals-AwayTeamGoals)>5-AwayTeamAttempts) break;
                if((AwayTeamGoals-HomeTeamGoals)>5-HomeTeamAttempts) break;

            System.out.print("\n                          "+(k+1)+"."+t1.MatchUp[10-k].getName()+" "+t1.MatchUp[10-k].getSurname());
            power = 110 + (t1.MatchUp[10-k].getOverall() - HomeTeamGoalkeeperPenaltySkill);
            a = (int) (Math.random() * 100);
            shot = 0;
            j = 0;

            while (shot != a && j < power) {
                shot = (int) (Math.random() * 100);

                if (shot == a) {
                    AwayTeamGoals++;
                    System.out.print(" (Scored)");
                    shot=140;
                    a=140;
                }
                j++;
            }
            if(shot!=140){
                System.out.print(" (Missed)");
            }
            AwayTeamAttempts++;
            if((AwayTeamGoals-HomeTeamGoals)>5-HomeTeamAttempts) break;
            if((HomeTeamGoals-AwayTeamGoals)>5-AwayTeamAttempts) break;
        }
        int k=5;
        while(AwayTeamGoals==HomeTeamGoals){
            if (k>10){
                k=0;
            }
            System.out.print("\n"+(k+1)+"."+MatchUp[10-k].getName()+" "+MatchUp[10-k].getSurname());
            int power = 110 + (MatchUp[10-k].getOverall() - AwayTeamGoalkeeperPenaltySkill);
            a = (int) (Math.random() * 100);
            int shot = 0;
            int j = 0;

            while (shot != a && j < power) {
                shot = (int) (Math.random()* 100);

                if (shot == a) {
                    HomeTeamGoals++;
                    System.out.print(" (Scored)");
                    shot=140;
                    a=140;
                }
                j++;
            }
        if(shot!=140){
            System.out.print(" (Missed)");
        }
            System.out.print("\n                          "+(k+1)+"."+t1.MatchUp[10-k].getName()+" "+t1.MatchUp[10-k].getSurname());
            power = 110 + (t1.MatchUp[10-k].getOverall() - HomeTeamGoalkeeperPenaltySkill);
            a = (int) (Math.random() * 100);
            shot = 0;
            j = 0;

            while (shot != a && j < power) {
                shot = (int) (Math.random() * 100);

                if (shot == a) {
                    AwayTeamGoals++;
                    System.out.print(" (Scored)");
                    shot=140;
                    a=140;
                }
                j++;
            }
            if (shot!=140){
                System.out.print(" (Missed)");
            }
            k++;
        }
        System.out.println();
        addScored(HomeTeamGoals);
        t1.addScored(AwayTeamGoals);
    }
    // metoda pozwalająca rozegrać dwumecz fazy pucharową turnieju z zachowaniem oficjalnych zasad awansu
    public void BracketStageMatch(Team t1[], int i, int k) {
        int P[] = new int[4];
        t1[i+k].PlayMatch(t1[i], 90);
        P[1] = t1[i].getScored();
        P[0] = t1[i + k].getScored();
        t1[i].PlayMatch(t1[i + k], 90);
        P[3] = t1[i].getScored() - P[0];
        P[2] = t1[i + k].getScored() - P[1];

        if (t1[i].getScored() > t1[i + k].getScored()) {
            System.out.println("Team " + t1[i].getName().trim() + " won on aggregate " + t1[i].getScored() + ":" + t1[i].getConceded());
            t1[i] = t1[i];
        }
        else
        if (t1[i].getScored() < t1[i + k].getScored()) {
            System.out.println("Team " + t1[i + k].getName().trim() + " won on aggregate " + t1[i + k].getScored() + ":" + t1[i + k].getConceded());
            t1[i] = t1[i + k];
        }
        else
        if (t1[i].getScored() == t1[i + k].getScored()) {
            if (P[1] > P[3]) {
                System.out.println("Team " + t1[i].getName().trim() + " won on away goals " + t1[i].getScored() + ":" + t1[i].getConceded());
                t1[i] = t1[i];
            }
            else
            if (P[1] < P[3]) {
                System.out.println("Team " + t1[i + k].getName().trim() + " won on away goals " + t1[i + k].getScored() + ":" + t1[i + k].getConceded());
                t1[i] = t1[i + k];
            }
            else
            if (P[1] == P[3]) {

                P[0] = t1[i].getScored();
                System.out.println("Extra time \n ");
                t1[i].PlayMatch(t1[i + k], 30);

                if (t1[i].getScored() > t1[i + k].getScored()) {
                    System.out.println("Team " + t1[i].getName().trim() + " won on aggregate " + t1[i].getScored() + ":" + t1[i].getConceded() + " after extra time.");
                    t1[i] = t1[i];
                }
                else
                if (t1[i].getScored() < t1[i + k].getScored()) {
                    System.out.println("Team " + t1[i + k].getName().trim() + " won on aggregate " + t1[i + k].getScored() + ":" + t1[i + k].getConceded() + " after extra time.");
                    t1[i] = t1[i + k];
                }
                else
                if (t1[i].getScored() == t1[i + k].getScored()) {
                    if (P[0]!=t1[i].getScored()) {
                        System.out.println("Team " + t1[i+k].getName().trim() + " won on away goals " + t1[i+k].getScored() + ":" + t1[i+k].getConceded()+" after extra time.");
                        t1[i] = t1[i];
                    }
                    else
                    if (P[0]==t1[i].getScored()) {
                        P[2] = t1[i+k].getScored();
                        P[1] = t1[i].getScored();
                        t1[i].PenaltyShootout(t1[i+k]);
                        P[2]=t1[i+k].getScored()-P[2];
                        P[1]=t1[i].getScored()-P[1];
                        if (t1[i].getScored()>t1[i+k].getScored()){
                            System.out.println("Team " + t1[i].getName().trim() + " won on penalties " + P[1] + ":" +P[2]);
                            t1[i]= t1[i];
                        }
                        else
                        if (t1[i].getScored()<t1[i+k].getScored()){
                            System.out.println("Team " + t1[i + k].getName().trim() + " won on penalties " + P[2] + ":" + P[1]);
                            t1[i] = t1[i + k];
                        }
                    }


                }
            }
        }
        System.out.println("\n \n");
    }
    // metoda pozwalająca rozegrać finał (różni się od metody PlayMatch, tym że, w przypadku remisu rozgrywana jest dogrywka, a jeśli w dogrywce wciąż będzie remis - rozegranie konkursu rzutów karnych)
    public void Final(Team t1[]){
        t1[0].PlayMatch(t1[1],90);
        if (t1[0].getScored()>t1[1].getScored()){
            System.out.println("Team " + t1[0].getName().trim() + " won " + t1[0].getScored() + ":" + t1[0].getConceded()+" and won the Champions League");
        }
        if (t1[0].getScored()<t1[1].getScored()){
            System.out.println("Team " + t1[1].getName().trim()+ " won " + t1[1].getScored() + ":" + t1[1].getConceded()+" and won the Champions League");
        }
        if(t1[0].getScored()==t1[1].getScored()){
            System.out.println("Extra time \n ");
            t1[0].PlayMatch(t1[1],30);
            if (t1[0].getScored()>t1[1].getScored()){
                System.out.println("Team " + t1[0].getName().trim() + " won after extra time " + t1[0].getScored() + ":" + t1[0].getConceded()+" and won the Champions League");
            }
            if (t1[0].getScored()<t1[1].getScored()){
                System.out.println("Team " + t1[1].getName().trim() + " won after extra time" + t1[1].getScored() + ":" + t1[1].getConceded()+" and won the Champions League");
            }
            int ScoreET[]= new int[2];
            ScoreET[0]=t1[0].getScored();
            ScoreET[1]=t1[1].getScored();
            if (t1[1].getScored()==t1[0].getScored()){
                t1[0].PenaltyShootout(t1[1]);
                if (t1[0].getScored()>t1[1].getScored())
                    System.out.println("Team " + t1[0].getName().trim() + " won after penalties " + (t1[0].getScored()-ScoreET[0]) + ":" + (t1[1].getScored()-ScoreET[1])+" and won the Champions League");

                else
                    System.out.println("Team " + t1[1].getName().trim() + " won after penalties " + (t1[1].getScored()-ScoreET[1]) + ":" + (t1[0].getScored()-ScoreET[0])+" and won the Champions League");

            }
        }
    }
    // rozegranie rozgrywek fazy pucharowej
    public void BracketStage(Team t1[]) {

        for (int i = 0; i < 8; i++) {
            t1[i].Clear();
        }
        for (int i = 0; i < 4; i++) {
            int a = (int) (Math.random() * 4);
            t1[8] = t1[i];
            t1[i] = t1[a];
            t1[a] = t1[8];
        }
        for (int i = 4; i < 8; i++) {
            int a = (int) ((Math.random() * 4) + 4);
            t1[8] = t1[i];
            t1[i] = t1[a];
            t1[a] = t1[8];
        }
        System.out.println("Quarter-final competitors: \n ");
        for (int i=0; i<8; i++){

            System.out.println(t1[i].getName());
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            t1[1].BracketStageMatch(t1,i,4);
            t1[i].Clear();
        }

        System.out.println("Semi-final competitors:\n ");

        for (int i=0; i<4; i++){
            System.out.println(t1[i].getName());
        }

        System.out.println();

        for (int i = 0; i <2; i++) {
            t1[1].BracketStageMatch(t1,i,2);
            t1[i].Clear();
        }


        System.out.println("Great final: \n ");
        t1[1].Final(t1);
    }

}
