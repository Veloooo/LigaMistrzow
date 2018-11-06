package javaapplication3;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class LigaMistrzowGUI extends javax.swing.JFrame {

    public String druzyna;
    public Team[] teams;
    public Group GroupA;
    public Group GroupB;
    public Group GroupC;
    public Group GroupD;
    private ArrayList <Formation> formations;
    public int day=1;
    /**
     * Creates new form LigaMistrzowGUI
     */
    public LigaMistrzowGUI(Team t1[], ArrayList<Formation> f) {
        druzyna=t1[0].getName();
        this.formations=f;
        teams=t1;
        setGroups(t1);
        initComponents();
        this.initResults();
        this.setTeamList(t1);
        this.pack();
    }
    public void setTeamList(Team t1[]){
        String TeamList[]= new String[16];
        for (int i=0; i<16 ; i++) {
            TeamList[i] = t1[i].getName();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(TeamList));
    }
    public void refreshTable(Group g1){

        DefaultTableModel model= new DefaultTableModel(new Object [][] {
                {g1.getGroupTeams(0).getName(), g1.getGroupTeams(0).getPlayed(), g1.getGroupTeams(0).getWon(), g1.getGroupTeams(0).getDrawn(), g1.getGroupTeams(0).getLost(), g1.getGroupTeams(0).getScored(), g1.getGroupTeams(0).getConceded(), g1.getGroupTeams(0).getPoints()},
                {g1.getGroupTeams(1).getName(), g1.getGroupTeams(1).getPlayed(), g1.getGroupTeams(1).getWon(), g1.getGroupTeams(1).getDrawn(), g1.getGroupTeams(1).getLost(), g1.getGroupTeams(1).getScored(), g1.getGroupTeams(1).getConceded(), g1.getGroupTeams(1).getPoints()},
                {g1.getGroupTeams(2).getName(), g1.getGroupTeams(2).getPlayed(), g1.getGroupTeams(2).getWon(), g1.getGroupTeams(2).getDrawn(), g1.getGroupTeams(2).getLost(), g1.getGroupTeams(2).getScored(), g1.getGroupTeams(2).getConceded(), g1.getGroupTeams(2).getPoints()},
                {g1.getGroupTeams(3).getName(), g1.getGroupTeams(3).getPlayed(), g1.getGroupTeams(3).getWon(), g1.getGroupTeams(3).getDrawn(), g1.getGroupTeams(3).getLost(), g1.getGroupTeams(3).getScored(), g1.getGroupTeams(3).getConceded(), g1.getGroupTeams(3).getPoints()}
        },
                new String [] {
                        "Name", "Played", "Won", "Drawn", "Lost", "Scored", "Conceded", "Points"
                }
        ) {



            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };


            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        jTable1.setModel(model);
        jTable1.getColumnModel().getColumn(0).setMinWidth(150);

    }
    public void setGroups(Team [] ChampionsLeagueCompetitors){
        this.GroupA = new Group("A",ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[4],ChampionsLeagueCompetitors[10],ChampionsLeagueCompetitors[14]);
        this.GroupB = new Group("B",ChampionsLeagueCompetitors[1],ChampionsLeagueCompetitors[8],ChampionsLeagueCompetitors[13],ChampionsLeagueCompetitors[6]);
        this.GroupC = new Group("C",ChampionsLeagueCompetitors[2],ChampionsLeagueCompetitors[7],ChampionsLeagueCompetitors[15],ChampionsLeagueCompetitors[11]);
        this.GroupD = new Group("D",ChampionsLeagueCompetitors[3],ChampionsLeagueCompetitors[9],ChampionsLeagueCompetitors[12],ChampionsLeagueCompetitors[5]);
    }
    public Group findTeam(String s1){
        Group g1=GroupB;
        for(int i=0; i<16 ; i++){
            if(teams[i].getName().equals(s1)){
                int index=i;
                if (index==0 || index==4 || index==10 || index==14) { g1=GroupA ;}
                else if (index==1 || index==8 || index==13 || index==6) { g1=GroupB ;}
                else if (index==2 || index==7 || index==15 || index==11) { g1=GroupC ;}
                else  { g1=GroupD ;}
            }
        }
        return g1;
    }
    public Team findTeam2(String t1){
        for (int i=0; i<16; i++){
            if (teams[i].getName().equals(t1))
                return teams[i];
        }
        return teams[0];
    }
    public int findteam3(String s1){
        for (int i=0; i<16; i++){
            if (teams[i].getName().equals(s1)) {
                if (i == 0 || i == 1 || i == 2 || i == 3) return 0;
                if (i == 4 || i == 7 || i == 8 || i == 9) return 1;
                if (i == 12 || i == 15 || i == 13 || i == 10) return 2;
                else return 3;
            }
        }
        return 1;
    }
    public Team[] order(int index, Group g1){
        Team[] x=new Team[]{teams[0],teams[0]};
        int order[]=new int[]{0,2,1,3,2,1,3,0,0,1,2,3,1,0,3,2,2,0,3,1,1,2,0,3};
        for(int i=4*(day-1); i<24; i++){
            if(order[i]==index){
                System.out.println("sssssss");
                if(order[i]%2==0){
                   x[0]=g1.getGroupTeams(order[i]);
                   x[1]=g1.getGroupTeams(order[i+1]);
                }
                else{
                    x[1]=g1.getGroupTeams(order[i-1]);
                    x[0]=g1.getGroupTeams(order[i]);
                }
               return x;
            }
        }

        return x;
    }
    public void setTeamMatchUp(Team t1){
        String[] strings = new String[11];
        for (int i=0; i<11; i++){
            strings[i]=t1.getMatchUp2(i).getNumber()+"."+t1.getMatchUp2(i).getName()+" "+t1.getMatchUp2(i).getSurname()+" "+t1.getMatchUp2(i).getPosition().getWing()+t1.getMatchUp2(i).getPosition().getLine();
        }
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        String[] bench=new String [7];
        for (int i=0; i<7; i++){
            bench[i]=t1.getSub(i).getNumber()+"."+t1.getSub(i).getName()+" "+t1.getSub(i).getSurname()+" "+t1.getSub(i).getPosition().getWing()+t1.getSub(i).getPosition().getLine();
        }
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return bench.length; }
            public String getElementAt(int i) { return bench[i]; }
        });
    }
    public void setTable(Group g1){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {g1.getGroupTeams(0).getName(), g1.getGroupTeams(0).getPlayed(), g1.getGroupTeams(0).getWon(), g1.getGroupTeams(0).getDrawn(), g1.getGroupTeams(0).getLost(), g1.getGroupTeams(0).getScored(), g1.getGroupTeams(0).getConceded(), g1.getGroupTeams(0).getPoints()},
                        {g1.getGroupTeams(1).getName(), g1.getGroupTeams(1).getPlayed(), g1.getGroupTeams(1).getWon(), g1.getGroupTeams(1).getDrawn(), g1.getGroupTeams(1).getLost(), g1.getGroupTeams(1).getScored(), g1.getGroupTeams(1).getConceded(), g1.getGroupTeams(1).getPoints()},
                        {g1.getGroupTeams(2).getName(), g1.getGroupTeams(2).getPlayed(), g1.getGroupTeams(2).getWon(), g1.getGroupTeams(2).getDrawn(), g1.getGroupTeams(2).getLost(), g1.getGroupTeams(2).getScored(), g1.getGroupTeams(2).getConceded(), g1.getGroupTeams(2).getPoints()},
                        {g1.getGroupTeams(3).getName(), g1.getGroupTeams(3).getPlayed(), g1.getGroupTeams(3).getWon(), g1.getGroupTeams(3).getDrawn(), g1.getGroupTeams(3).getLost(), g1.getGroupTeams(3).getScored(), g1.getGroupTeams(3).getConceded(), g1.getGroupTeams(3).getPoints()}
                },
                new String [] {
                        "Name", "Played", "Won", "Drawn", "Lost", "Scored", "Conceded", "Points"
                }
        ) {



            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };


            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setMinWidth(150);
    }
    public void initResults()
    {
        score1.setText("-");
        score2.setText("-");
        score3.setText("-");
        score4.setText("-");
        score5.setText("-");
        score6.setText("-");
        score7.setText("-");
        score8.setText("-");
        score9.setText("-");
        score10.setText("-");
        score11.setText("-");
        score12.setText("-");
        score13.setText("-");
        score14.setText("-");
        score15.setText("-");
        score16.setText("-");
        score17.setText("-");
        score18.setText("-");
        score19.setText("-");
        score20.setText("-");
        score21.setText("-");
        score22.setText("-");
        score23.setText("-");
        score24.setText("-");
    }

    public void setLabels(Group g1){

        team01.setText(g1.getGroupTeams(0).getName());
        team21.setText(g1.getGroupTeams(2).getName());
        team11.setText(g1.getGroupTeams(1).getName());
        team31.setText(g1.getGroupTeams(3).getName());

        team22.setText(g1.getGroupTeams(2).getName());
        team12.setText(g1.getGroupTeams(1).getName());
        team32.setText(g1.getGroupTeams(3).getName());
        team02.setText(g1.getGroupTeams(0).getName());

        team03.setText(g1.getGroupTeams(0).getName());
        team13.setText(g1.getGroupTeams(1).getName());
        team23.setText(g1.getGroupTeams(2).getName());
        team33.setText(g1.getGroupTeams(3).getName());

        team14.setText(g1.getGroupTeams(1).getName());
        team04.setText(g1.getGroupTeams(0).getName());
        team34.setText(g1.getGroupTeams(3).getName());
        team24.setText(g1.getGroupTeams(2).getName());

        team25.setText(g1.getGroupTeams(2).getName());
        team05.setText(g1.getGroupTeams(0).getName());
        team35.setText(g1.getGroupTeams(3).getName());
        team15.setText(g1.getGroupTeams(1).getName());

        team16.setText(g1.getGroupTeams(1).getName());
        team26.setText(g1.getGroupTeams(2).getName());
        team06.setText(g1.getGroupTeams(0).getName());
        team36.setText(g1.getGroupTeams(3).getName());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 0), new java.awt.Dimension(1, 0), new java.awt.Dimension(1, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 1), new java.awt.Dimension(0, 1), new java.awt.Dimension(32767, 1));
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        team0 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        team01 = new javax.swing.JLabel();
        team21 = new javax.swing.JLabel();
        score1 = new javax.swing.JLabel();
        score2 = new javax.swing.JLabel();
        team11 = new javax.swing.JLabel();
        score3 = new javax.swing.JLabel();
        score4 = new javax.swing.JLabel();
        team31 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        team22 = new javax.swing.JLabel();
        team12 = new javax.swing.JLabel();
        score5 = new javax.swing.JLabel();
        score6 = new javax.swing.JLabel();
        team32 = new javax.swing.JLabel();
        score7 = new javax.swing.JLabel();
        score8 = new javax.swing.JLabel();
        team02 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        team03 = new javax.swing.JLabel();
        team13 = new javax.swing.JLabel();
        score9 = new javax.swing.JLabel();
        score10 = new javax.swing.JLabel();
        team23 = new javax.swing.JLabel();
        score11 = new javax.swing.JLabel();
        score12 = new javax.swing.JLabel();
        team33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        team14 = new javax.swing.JLabel();
        team04 = new javax.swing.JLabel();
        score13 = new javax.swing.JLabel();
        score14 = new javax.swing.JLabel();
        team34 = new javax.swing.JLabel();
        score15 = new javax.swing.JLabel();
        score16 = new javax.swing.JLabel();
        team24 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        team25 = new javax.swing.JLabel();
        team05 = new javax.swing.JLabel();
        score17 = new javax.swing.JLabel();
        score18 = new javax.swing.JLabel();
        team35 = new javax.swing.JLabel();
        score19 = new javax.swing.JLabel();
        score20 = new javax.swing.JLabel();
        team15 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        team16 = new javax.swing.JLabel();
        team26 = new javax.swing.JLabel();
        score21 = new javax.swing.JLabel();
        score22 = new javax.swing.JLabel();
        team06 = new javax.swing.JLabel();
        score23 = new javax.swing.JLabel();
        score24 = new javax.swing.JLabel();
        team36 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labele = new JLabel[]{score1,score2,score3,score4,score5,score6,score7,score8,score9,score10,score11,score12,score13,score14,score15,score16,score17,score18,score19,score20,score21,score22,score23,score24};

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Team");


        jScrollPane1.setViewportView(jList1);


        jScrollPane2.setViewportView(jList2);



        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Group name");
        jButton2.setVisible(false);
        jScrollPane3.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Matches");
        jLabel16.setText("Gameweek 1");
        jLabel17.setText("Gameweek 2");
        jLabel26.setText("Gameweek 3");
        jLabel35.setText("Gameweek 4");
        jLabel44.setText("Gameweek 5");
        jLabel53.setText("Gameweek 6");



        javax.swing.GroupLayout team0Layout = new javax.swing.GroupLayout(team0);
        team0.setLayout(team0Layout);
        team0Layout.setHorizontalGroup(
                team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(team0Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team0Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team0Layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(173, 173, 173))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team0Layout.createSequentialGroup()
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(164, 164, 164))))))
                        .addGroup(team0Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team0Layout.createSequentialGroup()
                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team11)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score3)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score4)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team31))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team01)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score1)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score2)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team21))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team23)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score11)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score12)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team33))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team03)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score9)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score10)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team13))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(jLabel26))))
                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                .addGap(62, 62, 62)
                                                                .addComponent(jLabel16))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team0Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team35)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score19)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score20)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team15))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addComponent(team25)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score17)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(score18)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(team05))
                                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(jLabel44)))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                .addComponent(team32)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(score7)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(score8)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(team02))
                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                .addComponent(team22)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(score5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(score6)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(team12))
                                                        .addGroup(team0Layout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(jLabel17))))
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addComponent(team34)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score15)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score16)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(team24))
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addComponent(team14)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score13)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score14)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(team04))
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addGap(39, 39, 39)
                                                                        .addComponent(jLabel35)))
                                                        .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addComponent(team06)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score23)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score24)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(team36))
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addComponent(team16)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score21)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(score22)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(team26))
                                                                .addGroup(team0Layout.createSequentialGroup()
                                                                        .addGap(39, 39, 39)
                                                                        .addComponent(jLabel53))))))
                                .addGap(81, 81, 81))
        );
        team0Layout.setVerticalGroup(
                team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(team0Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team01)
                                                        .addComponent(score1)
                                                        .addComponent(score2)
                                                        .addComponent(team21))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team11)
                                                        .addComponent(score3)
                                                        .addComponent(score4)
                                                        .addComponent(team31)))
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team22)
                                                        .addComponent(score5)
                                                        .addComponent(score6)
                                                        .addComponent(team12))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team32)
                                                        .addComponent(score7)
                                                        .addComponent(score8)
                                                        .addComponent(team02))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team03)
                                                        .addComponent(score9)
                                                        .addComponent(score10)
                                                        .addComponent(team13))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team23)
                                                        .addComponent(score11)
                                                        .addComponent(score12)
                                                        .addComponent(team33)))
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addComponent(jLabel35)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team14)
                                                        .addComponent(score13)
                                                        .addComponent(score14)
                                                        .addComponent(team04))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team34)
                                                        .addComponent(score15)
                                                        .addComponent(score16)
                                                        .addComponent(team24))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team25)
                                                        .addComponent(score17)
                                                        .addComponent(score18)
                                                        .addComponent(team05))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team35)
                                                        .addComponent(score19)
                                                        .addComponent(score20)
                                                        .addComponent(team15)))
                                        .addGroup(team0Layout.createSequentialGroup()
                                                .addComponent(jLabel53)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team16)
                                                        .addComponent(score21)
                                                        .addComponent(score22)
                                                        .addComponent(team26))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(team0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(team06)
                                                        .addComponent(score23)
                                                        .addComponent(score24)
                                                        .addComponent(team36))))
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(team0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(785, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(team0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Champions League simulator v 1.0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Chose your team:");


        jComboBox1.addActionListener(event -> druzyna=jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Play GW"+day);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(31, 31, 31)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );


    }// </editor-fold>



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println(findTeam(druzyna).getName());
        Group g1=findTeam(druzyna);
        System.out.println();
        setGroups(teams);
        jLabel5.setText(druzyna);
        setTeamMatchUp(findTeam2(druzyna));
        setLabels(g1);
        setTable(g1);
        jButton2.setVisible(true);
        jButton1.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel4.setVisible(false);
    }
    private void  jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Group g1=findTeam(druzyna);
        Team[] t=order(findteam3(druzyna),g1);
        jButton2.setText("Play GW"+day);
        LigaMistrzowGUI2 window2= new LigaMistrzowGUI2(t[0],t[1],formations,this);
        window2.setVisible(true);
        day++;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        Position GK = new Position("GK","C","D");
        Position CB = new Position("B","C","D");
        Position RB = new Position("B","R","D");
        Position LB = new Position("B","L","D");
        Position CM = new Position("M","C","E");
        Position CDM = new Position("M","C","D");
        Position CAM = new Position("M","C","O");
        Position ST = new Position("A","C","O");
        Position RW = new Position("A","R","O");
        Position LW = new Position("A","L","O");
        Position LWB = new Position("B","L","E");
        Position RWB = new Position("B","R","E");
        Position RM = new Position("M","R","E");
        Position LM = new Position("M","L","E");


        ArrayList<Formation> formations= new ArrayList<>();
        formations.add(new Formation("4-4-2",GK,LB,CB,CB,RB,LM,CM,CM,RM,ST,ST));
        formations.add(new Formation("4-3-3",GK,LB,CB,CB,RB,LM,CAM,RM,LW,RW,ST));
        formations.add(new Formation("4-2-3-1",GK,LB,CB,CB,RB,CM,CM,RM,LM,CAM,ST));
        formations.add(new Formation("4-3-2-1",GK,LB,CB,CB,RB,CM,CM,CM,RW,LW,ST));
        formations.add(new Formation("4-5-1",GK,LB,CB,CB,RB,LM,CM,CM,RM,CM,ST));
        formations.add(new Formation("3-4-3",GK,CB,CB,CB,CM,LM,CM,RW,RM,LW,ST));
        formations.add(new Formation("3-5-2",GK,CB,CB,CB,RM,CM,CM,CAM,LM,ST,ST));
        formations.add(new Formation("5-4-1",GK,LB,CB,CB,RB,CB,CM,CM,RM,LM,ST));
        formations.add(new Formation("5-3-2",GK,LB,CB,CB,RB,CB,CM,CM,CM,ST,ST));
        formations.add(new Formation("5-2-2-1",GK,LWB,CB,CB,RWB,CB,CM,CM,RW,LW,ST));



        // tworzenie zawodników
        Player zero= new Player ("","",GK,0,0);
        //Real Madryt
        Player KNavas=new Player("Keylor", "Navas", GK ,1, 84);
        Player Marcelo=new Player("Marcelo", " ", LB, 3, 84);
        Player SRamos=new Player("Sergio", "Ramos", CB, 4, 89);
        Player Pepe=new Player("Pepe", " ", CB, 5, 84);
        Player DCarvajal=new Player("Dani", "Carvajal", RB, 63, 83);
        Player TKroos=new Player("Toni", "Kroos", CM,6, 86);
        Player LModric=new Player("Luca", "Modric", CM,18, 90);

        Player CRonaldo=new Player("Cristiano", "Ronaldo", ST, 7, 96);
        Player GBale=new Player("Gareth", "Bale", RW,11, 92);

        // Lawka
        Player KBenzema = new Player("Karim","Benzema",ST,9,87);
        Player Isco = new Player("Isco","",CAM,22,86);
        Player Casemiro = new Player("Casemiro","",CM,21,82);
        Player MAsensio= new Player("Marco","Asensio",CAM,20,83);
        Player LVasquez= new Player("Lucas","Vasquez",LW,18,82);
        Player RVarane= new Player("Raphael", "Varane",CB, 14,84);
        Player KCas = new Player ("Kiko","Casilla",GK, 93,78);
        // FC Barcelona
        Player TStegen=new Player("Ter", "Stegen", GK, 1, 80);
        Player JAlba=new Player("Jordi", "Alba", LB,18, 83);
        Player GPique=new Player("Gerrard", "Pique", CB, 4, 84);
        Player SUmtiti=new Player("Samuel","Umtiti",CB,23,85);
        Player SRoberto=new Player("Sergi", "Roberto", RB, 3, 80);
        Player SBusquets=new Player("Sergio", "Busquets", CDM, 6, 86);
        Player IRakitic=new Player("Ivan", "Rakitic", CM, 8, 85);
        Player AIniesta=new Player("Andres", "Iniesta", CAM, 12, 88);
        Player LMessi=new Player("Lionel", "Messi", ST,10, 95);
        Player LSuarez=new Player("Luis", "Suarez", ST, 9, 92);
        Player ODembele=new Player("Osama","Dembele",RW,11,84);
        //Lawka
        Player JMascherano=new Player("Javier", "Mascherano", CB,5, 83);
        Player AGomes= new Player("Andre","Gomes",CM,14,79);
        Player PAlcacer = new Player("Paco","Alcacer",ST,19,82);
        Player Paulinho= new Player("Paulinho","",CM,15,82);
        Player JCilessen= new Player("Jasper","Cillesen",GK,13,77);
        Player GDeufoleu= new Player("Gerard","Deulofeu",ST,16,79);
        Player NSemedo= new Player ("Nelson", "Semedo",RB,2,79);
        //Manchester City
        Player Ederson=new Player("Ederson", "", GK, 31, 84);
        Player Danilo=new Player("Danilo", "", RB, 3, 84);
        Player JStones=new Player("John", "Stones", CB,5,81 );
        Player NOtamendi=new Player("Nicolas", "Otamendi", CB,30, 84);
        Player DSilva=new Player("David", "Silva", CAM,21, 88);
        Player Fernandinho=new Player("", "Fernandinho", CDM,25, 83);
        Player BMendy=new Player("Benjamin","Mendy",LB,22,85);
        Player KDBruyne=new Player("Kevin", "De Bruyne", CAM, 17, 90);
        Player SAguero=new Player("Sergio", "Aguero", ST, 9, 90);
        Player GJesus=new Player("Gabriel","Jesus",ST, 33,88);
        Player KWalker=new Player("Kyle","Walker",RB,2,81);

        Player CBravo=new Player("Claudio", "Bravo", GK, 1, 82);
        Player RSterling=new Player("Raheem", "Sterling", ST, 7, 87);
        Player Fernando=new Player("Fernando", "", CDM, 6, 83);
        Player BSilva=new Player("Bernardo","Silva",CAM,20,86);
        Player VKompany = new Player("Vincent","Kompany",CB,4,84);
        Player LSane = new Player("Leroy","Sane",RW,19,85);
        Player IGundogan=new Player("Illkay","Gundogan",CM,8,85);
        //Arsenal
        Player PCech=new Player("Petr", "Čech", GK, 33, 88);
        Player NMonreal=new Player("Nacho", "Monreal", LB, 18, 81);
        Player Mustafi= new Player("","Mustafi",CB,15,83);
        Player LKoscielny=new Player("Laurent", "Koscielny", CB, 6, 85);
        Player Kolasinac=new Player("Kolasinac","",LB,31,83);
        Player HBellerín=new Player("Hector", "Bellerín	", RB, 24, 85);
        Player MÖzil=new Player("Mesut", "Özil", CAM, 11, 89);
        Player ARamsey = new Player("Aaron","Ramsey",CM,8,83);
        Player GXhaka=new Player("Granit", "Xhaka", CM, 29, 83);
        Player ASánchez=new Player("Alexis", "Sánchez", ST, 7, 90);
        Player ALacazette=new Player("Alexandre","Lacazette",ST,9,87);
        //Lawka
        Player AIwobi=new Player("Alex", "Iwobi", CM, 17, 83);
        Player DOspina = new Player("David","Ospina",GK,13,82);
        Player FCoquelin=new Player("Francis", "Coquelin", CDM, 34, 84);
        Player OGiroud=  new Player("Oliver","Giroud",ST,12,84);
        Player TWalcott=new Player("Theo", "Walcott", ST, 14, 86);
        Player DWelbeck= new Player("Danny","Welbeck",ST,23,82);
        Player PMertesacker=new Player("Per","Mertesacker",CB,4,74);

        //Chelsea
        Player TCourtois=new Player("Thibout", "Courtois", GK, 13, 89);
        Player CAzpilicueta=new Player("Cesar", "Azpilicueta", CB, 28, 85);
        Player GCahill=new Player("Gary", "Cahill", CB,24, 83);
        Player DLuiz=new Player("David", "Luiz", CB, 30, 84);
        Player VMoses=new Player("Victor", "Moses", LB, 20, 83);
        Player MAlonso=new Player("Marcos", "Alonso", RB,3, 83);
        Player NKante=new Player("N'golo", "Kante", CDM, 7, 86);
        Player Bakayoko=new Player("Tiemenue","Bakayoko",CDM,14,85);
        Player PRodriguez=new Player("Pedro", "Rodriguez", RW, 11, 86);
        Player EHazard=new Player("Eden", "Hazard", LW,10, 90);
        Player AMorata=new Player("Alvaro", "Morata", ST, 9,85);

        //Lawka
        Player WCaballero=new Player("Willy","Caballero",GK,1,80);
        Player DZapacosta=new Player("Davide","Zapacosta",RB,21,82);
        Player Christensen=new Player("Andreas","Christensen",CB,27,81);
        Player DDrinkwater=new Player ("Danny","Drinkwater",CM,6,82);
        Player Willian=new Player ("Willian","",RW,22,85);
        Player Batshuay=new Player("Michy","Batshuayi",ST,23,82);
        Player Fabregas=new Player("Cesc","Fabregas",CM,4,84);

        // Bayern
        Player MNeuer=new Player("Manuel", "Neuer", GK, 1, 89);
        Player DAlaba=new Player("David", "Alaba", LB, 2, 84);
        Player MHummels=new Player("Mats", "Hummels", CB, 3, 86);
        Player JBoateng=new Player("Jerome", "Boateng", CB, 17, 86);
        Player JKimmich=new Player("Joshua", "Kimmich", RB, 4, 83);
        Player XAlonso=new Player("Xabi", "Alonso", CDM, 5, 83);
        Player AVidal=new Player("Arturo", "Vidal", CM, 6, 86);
        Player TMuller=new Player("Thomas", "Muller", CAM, 10, 85);
        Player Tolisso=new Player("Tolisso", "", CM, 24, 84);
        Player ARobben=new Player("Arjen", "Robben", RW, 11, 85);
        Player RLewandowski=new Player("Robert", "Lewandowski", ST, 9, 90);
        // Lawka
        Player Ulreich = new Player("","Ulreich",GK,26,75);
        Player JRodriguez=new Player("James", "Rodriguez", CAM, 11, 85);
        Player KComan= new Player("Kingsley","Coman",RW,19,83);
        Player JMartinez= new Player("Javi","Martinez",CDM,24,82);
        Player Friedl= new Player("Friedl","",CM,34,77);
        Player Pantovic= new Player("Pantovic","",CB,41,73);
        Player Rafinha=new Player ("Rafinha","",LB,13,82);
        // PSG
        Player Trapp=new Player("Trapp", "", GK, 1, 85);
        Player TSilva=new Player("Thiago", "Silva", CB, 2, 88);
        Player Marquinios=new Player("Marquinios", "", CB,5, 83);
        Player DAlver=new Player("Dani", "Alves", RB,32, 85);
        Player Kurzawa=new Player("Kurzawa", "", LB, 20, 83);
        Player MVeratti=new Player("Marco", "Veratti", CAM, 6, 86);
        Player Motta=new Player("Thiago","Motta",CM,8,83);
        Player Rabiot=new Player("Adrien","Rabiot",CM,25,83);
        Player ECavani=new Player("Edinson", "Cavani", ST, 9, 88);
        Player Neymar=new Player("Neymar", "Jr", ST, 11, 93);
        Player KMbappe=new Player("Kyllian","Mbappe",ST,11,86);

        Player LMoura=new Player("Lucas", "Moura", RW, 7, 85);
        Player Areola=new Player("Alvaro","Areola",GK,16,84);
        Player Draxler=new Player("Julian","Draxler", CAM,23,84);
        Player Kimpembe= new Player("Presnel","Kimpember",CB,3,80);
        Player Meunier=new Player("Thomas","Meunier",RB,12,83);
        Player DiMaria=new Player("Angel","Di Maria",RW,22,84);
        Player Beriche= new Player("Yuri","Beriche",LB,17,81);

        Player DSubasic=new Player("Danjel","Subasic",GK,1,82);
        Player KGlik=new Player("Kamil","Glik",CB,4,86);
        Player Jemerson=new Player("Jemerson","",RB,2,81);
        Player Sidibe=new Player("Djibril","Sidibe",RB,19, 82);
        Player Jorge=new Player("Jorge","",LB,6,77);
        Player JMoutinho=new Player("Joao","Moutinho",CM,8,84);
        Player Fabinho=new Player("Fabinho","",CM,2,84);
        Player Lemar= new Player("Thomas","Lemar",LM,27,85);
        Player RLopes= new Player("Rony","Lopes",RM,20,79);
        Player RFalcao=new Player("Radamel","Falcao",ST,9,86);
        Player Diakhaby= new Player("Adama","Diakhaby",ST,15,80);


        Player Benaglio= new Player("Diego","Benaglio",GK,16,78);
        Player Tielemans= new Player("Youri","Tielemans",ST,17,84);
        Player ARaggi= new Player("Andrea","Raggi",CB,24,81);
        Player Keita= new Player ("Balde","Keita",ST,14,80);
        Player Jovetic= new Player("Stefan","Jovetic",ST,10,81);
        Player Ghezzal= new Player ("Rahid","Ghezzal",CM,7,79);
        Player Carillo= new Player("Guido","Carillo",RW,11,81);

        //Tottenham
        Player HLloris=new Player("Hugo","Lloris",GK,1,88);
        Player TAlderweireld=new Player("Toby","Alderweireld",CB,4,85);
        Player JVertonghen=new Player("Jan","Vertonghen",CB,5,83);
        Player DRose=new Player("Danny","Rose",LB,2,83);
        Player Davies=new Player("Ben","Davies",RB,33,81);
        Player MDembele=new Player("Moussa","Dembele",CM,7,85);
        Player VWanyama=new Player("Victor","Wanyama",CM,8,86);
        Player DAlli=new Player("Delle","Alli",CAM,11,84);
        Player SHueng=new Player("Son","Hueng",ST,10,83);
        Player HKane=new Player("Harry","Kane",ST,9,85);
        Player CEriksen=new Player("Cristian","Eriksen",CAM,10,84);

        Player Vorm= new Player("Michel","Vorm",GK,13,79);
        Player Aurier=new Player ("Serge","Aurier",RB,24,82);
        Player Dier= new Player("Eric","Dier",CM,15,83);
        Player Trippier= new Player("Kieran","Trippier",RB,2,80);
        Player Winks= new Player("Harry","Winks",CM,29,78);
        Player Llorente= new Player("Fernando","Llorente",ST,18,82);
        Player Sissoko= new Player("Sissoko","",CM,17,82);

        Player Burki=new Player("Burki","",GK,1,81);
        Player SPapasdopulos=new Player("Sokratis","Papasdopulos",CB,4,84);
        Player LPiszczek=new Player("Lukasz","Piszczek",RB,3,82);
        Player MBartra=new Player("Marc","Bartra",CB,5,81);
        Player Castro=new Player("Gonzalo","Castro",CM,27,82);
        Player CPulisic=new Player("Christian","Pulisic",RW,22,80);
        Player SKagawa=new Player("Shinji","Kagawa",CM,8,83);
        Player MGoetze=new Player("Mario","Goetze",CAM,7,84);
        Player ASchurrle=new Player("Andre","Schurrle",CAM,8,81);
        Player Reus=new Player("Marco","Reus",LW,11,88);
        Player PAubameyang=new Player("Pierre-Emerick","Aubameyang",ST,17,87);

        Player Dahoud= new Player("Mahmoud","Dahoud",CM,19,79);
        Player Toprak=new Player("Omer","Toprak",CB,36,80);
        Player Weidenfeller=new Player("Roman","Weidenfeller",GK,1,75);
        Player Yarmolenko=new Player("Andryi","Yarmolenko",ST,9,84);
        Player Guerreiro=new Player("Raphael","Guerreiro",LM,42,81);
        Player Schmelzer=new Player("Marcel","Schmelzer",LB,29,79);
        Player Maximilian=new Player("Maximilian","Philipp",LW,20,79);


        Player JOblak=new Player("Jan","Oblak",GK,13,87);
        Player DGodin=new Player("Diego","Godin",CB,3,88);
        Player FLuis=new Player("Felipe","Luis",LB,4,85);
        Player Savic=new Player("Savic","",CB,5,83);
        Player Juanfran=new Player("Juanfran","",RB,2,83);
        Player MGaitan=new Player("Marcos","Gaitan",CM,7,85);
        Player YCarrasco=new Player("Yannick","Carrasco",CM,10,82);
        Player Gabi=new Player("Gabi","",CM,14,82);
        Player Koke=new Player("Koke","",CM,6,84);
        Player AGriezmann=new Player("Antoine","Griezmann",ST,11,88);
        Player FTorres=new Player("Fernando","Torres",ST,9,81);

        Player Vietto=new Player("Luciano","Vietto",ST,17,80);
        Player Thomas=new Player("Thomas","",CM,5,80);
        Player Correa= new Player("Angel","Correa",CM,11,80);
        Player Gimenez=new Player("Jose","Jimenez",CB,24,80);
        Player Lucas=new Player("Lucas","Hernandes",CB,19,78);
        Player Saul=new Player ("Saul","Niguez",CAM,8,83);
        Player Moya=new Player("Miguel Angel","Moya",GK,1,79);

        Player TShinhan=new Player("Tien","Shinhan ",GK,1,82);
        Player Yamcha=new Player("Yamcha"," ",CB,5,81);
        Player Krillin=new Player("Krillin"," ",CB,4,82);
        Player Roshi=new Player("Master","Roshi ",LB,3,81);
        Player Whis=new Player("Whis"," ",RB,2,99);
        Player Piccolo=new Player("Piccolo"," ",CM,6,84);
        Player Trunks=new Player("Trunks"," ",CAM,7,83);
        Player Goten=new Player("Goten"," ",CDM,8,83);
        Player Vegeta=new Player("Vegeta"," ",LW,9,95);
        Player Goku=new Player("Goku"," ",ST,10,94);
        Player Beerus=new Player("Beerus"," ",RW,11,97);
        Player Jamemba=new Player("Jamemba"," ",GK,1,89);
        Player Turles=new Player("Turles"," ",CB,2,70);
        Player Brolly=new Player("Brolly"," ",CB,3,93);
        Player Nappa=new Player("Nappa"," ",CB,4,75);
        Player Cell=new Player("Cell"," ",RB,5,89);
        Player Buu=new Player("Buu"," ",LB,6,93);
        Player Frieza=new Player("Frieza"," ",CM,7,87);
        Player Android=new Player("Android","17 ",CM,17,87);
        Player Black=new Player("Black","",CAM,9,94);
        Player Zamasu=new Player("Zamasu"," ",CAM,11,89);
        Player Ginyu=new Player("Ginyu"," ",ST,10,80);
        //Superstarr
        Player HBajda=new Player("Henryk","Bajda",GK,1,84);
        Player SJach=new Player("Stanisław 'Stajku'","Jach",CB,2,90);
        Player APastuszek=new Player("Adam 'Chomik'","Pastuszek",CB,4,90);
        Player HMinus=new Player("Herkules","Minus",CB,3,83);
        Player BBolek=new Player("Bolek","",LB,5,83);
        Player FGerula=new Player("Florian","Gerula",RB,6,86);
        Player MGerula=new Player("Marian","Gerula",CM,7,86);
        Player CGerula=new Player("Czesław","Gerula",CM,8,86);
        Player JSzpytman=new Player("Jarosław 'Pitas'","Szpytman",CM,23,89);
        Player KWojtowicz=new Player("Kamil 'Kombajn'","Wojtowicz",ST,10,87);
        Player ŁJach=new Player("Łukasz Kryminał","Jach",ST,9,87);
        // Huragan
        Player MDaraz=new Player("Maciej", "Daraz", GK, 12, 87);
        Player MZatawrdnicki=new Player("Maciej", "Zatawrdnicki", CB, 2, 83);
        Player BZatwardnicki=new Player("Bartosz", "Zatwardnicki", CB, 4, 90);
        Player DMazur=new Player("Damian", "Mazur", LB, 3, 89);
        Player KLewiarz=new Player("Krzysztof", "Lewiarz", RB, 11, 88);
        Player DFudali=new Player("Daniel", "Fudali", CDM,6, 90);
        Player BFrosztega=new Player("Bartosz", "Frosztega", CM,13, 83);
        Player LPawlowski=new Player("Łukasz", "Pawłowski", RW, 19, 90);
        Player DPawlowski=new Player("Daniel", "Pawłowski", CAM, 10, 90);
        Player BDzimira=new Player("Bogumił", "Dzimira", ST,9, 91);
        Player APawlowski=new Player("Arkadiusz", "Pawłowski", LW, 7, 94);
        // Legends
        Player GBednarczyk=new Player("Grzegorz","Bednarczyk",GK,12,87);
        Player MGłuszko=new Player("Mieczysław","Głuszko",LB,3,86);
        Player MPawłowski=new Player("Marian","Pawłowski",CB,2,85);
        Player PDzidek=new Player("Paweł","Dzidek",CB,3,87);
        Player ZPawłowski=new Player("Zenon","Pawłowski",RB,5,84);
        Player EFudali=new Player("Edward","Fudali",CDM,7,84);
        Player DSzpytman=new Player("Dawid","Szpytman",CDM,8,85);
        Player STomaszewski=new Player("Szymon ","Tomaszewski",CAM,10,94);
        Player LChorbowy=new Player("Leon","Chorbowy",CAM,4,88);
        Player MSzpytman=new Player("Marek","Szpytman",CAM,88,85);
        Player WBajda=new Player("Wiesław","Bajda",ST,11,87);
        Player LDaraż=new Player("Łukasz","Daraż",CB,70,86);
        Player DGłowacz=new Player("Dawid","Głowacz",RM,8,85);
        Player AWanat= new Player("Arkadiusz","Wanat",CM,96,84);
        Player MPawlowski= new Player("Marcin","Pawłowski",LB,25,87);
        Player MBus= new Player("Mateusz","Buś",CB,74,85);
        Player JKwasny=new Player("Józef","Kwaśny",GK,1,87);
        Player KPastuszek= new Player("Kacper","Pastuszek",ST,69,84);
        Player BBajda=new Player("Bartosz","Bajda",CB,22,79);
        Player JGerula=new Player("Jacek","Gerula",ST,9,86);
        Player PGerula=new Player("Piotr","Gerula",ST,19,88);
        Player JBajda=new Player("Jarosław","Bajda",CB,26,84);
        Player DBochenek= new Player("Damian","Bochenek",CB,14,89);
        Player PMikita= new Player("Piotr","Mikita",CDM,30,79);
        Player EKowalinski= new Player("Eugeniusz","Kowaliński",RB,32,80);
        Player JGluszko=new Player("Jarosław","Głuszko",CM,18,87);
        Player KZabawski= new Player("Krystian","Zabawski",CM,42,87);
        Player DZabawski= new Player("Damian","Zabawski",CB,41,80);
        Player SFednar= new Player("Sylwester","Fednar",RM,21,80);
        Player MFednar= new Player("Marcin","Fednar",CM,17,82);
        Player DSwist= new Player("Dominik","Swist",RW,27,85);
        Player MSwist= new Player("Mateusz","Swist",LW,28,86);



        // stworzenie tablicy zespołów i przypisanie do niej zespołów (pozycja [16] jest pozycją buforową aby w późniejszym etapie możliwe były zmiany pozycji zespołów w tablicy)
        Team ChampionsLeagueCompetitors[]=new Team[17];
        ChampionsLeagueCompetitors[0]=new Team("Real Madrid","Spain", formations.get(0),KNavas,	Marcelo,SRamos,Pepe,DCarvajal,TKroos,LModric,JRodriguez,CRonaldo,GBale,AMorata);
        ChampionsLeagueCompetitors[1]=new Team( "FC Barcelona", "Spain",formations.get(0), TStegen,JAlba,GPique,SUmtiti,SRoberto,SBusquets,IRakitic,AIniesta,LMessi,LSuarez,ODembele);
        ChampionsLeagueCompetitors[2]=new Team( "Huragan Reczpol", "Poland",formations.get(2),  MDaraz,MZatawrdnicki,	BZatwardnicki,DMazur,KLewiarz,DFudali,BFrosztega,LPawlowski,DPawlowski,BDzimira,APawlowski);
        ChampionsLeagueCompetitors[3]=new Team( "Manchester City", "England",formations.get(0),  Ederson,	Danilo,BMendy,JStones,NOtamendi,DSilva,Fernandinho,KWalker,KDBruyne,GJesus,SAguero);
        ChampionsLeagueCompetitors[4]=new Team( "Arsenal FC", "England",formations.get(0),  PCech,NMonreal,LKoscielny,Kolasinac,	HBellerín,ALacazette,GXhaka,Mustafi,ASánchez,ARamsey,MÖzil);
        ChampionsLeagueCompetitors[5]=new Team( "Chelsea FC", "England",formations.get(0),  TCourtois,CAzpilicueta,GCahill,DLuiz,VMoses,MAlonso,NKante,Bakayoko,PRodriguez,EHazard,AMorata);
        ChampionsLeagueCompetitors[6]=new Team("Bayern Munich", "Germany",formations.get(0), MNeuer,	DAlaba,	MHummels,JBoateng,JKimmich,XAlonso,	AVidal,TMuller,Tolisso,ARobben,RLewandowski);
        ChampionsLeagueCompetitors[7]=new Team("PSG","France",formations.get(0), Trapp,	TSilva,	Marquinios,DAlver,Kurzawa,Motta,Rabiot,MVeratti,Neymar,	ECavani,KMbappe);
        ChampionsLeagueCompetitors[8]=new Team("AS Monaco","France",formations.get(0),DSubasic,Diakhaby,KGlik,Jemerson,Sidibe,Jorge,JMoutinho,Fabinho,Lemar,RLopes,RFalcao);
        ChampionsLeagueCompetitors[9]=new Team("Tottenham Hotspur","England",formations.get(0), HLloris,TAlderweireld,JVertonghen,Davies,DRose,MDembele,VWanyama,DAlli,SHueng,	HKane,CEriksen);
        ChampionsLeagueCompetitors[10]=new Team("Borussia Dortmund","Germany",formations.get(0), Burki,SPapasdopulos,LPiszczek,	MBartra,CPulisic,SKagawa,MGoetze,ASchurrle,Castro,Reus,PAubameyang);
        ChampionsLeagueCompetitors[11]=new Team("Atletico Madrid", "Spain",formations.get(0), JOblak,	DGodin,	FLuis,Savic,Juanfran,	MGaitan,YCarrasco,Gabi,Koke,AGriezmann,FTorres) ;
        ChampionsLeagueCompetitors[12]=new Team("DragunboluGut","Japan",formations.get(0), TShinhan,Yamcha,Krillin,Roshi,Whis,Piccolo,Trunks,Goten,Vegeta,Goku,Beerus);
        ChampionsLeagueCompetitors[13]=new Team("DragunboluBat","Japan",formations.get(0),Jamemba,Turles,Brolly,Nappa,Cell,Buu,Frieza,Android,Black,Zamasu,Ginyu);
        ChampionsLeagueCompetitors[14]=new Team("Reczpol Superstar","Poland",formations.get(0), HBajda,SJach,APastuszek,HMinus,BBolek,FGerula,MGerula,CGerula,JSzpytman,KWojtowicz,ŁJach);
        ChampionsLeagueCompetitors[15]=new Team("Reczpol Legends","Poland",formations.get(0),GBednarczyk,MGłuszko,MPawłowski,PDzidek,ZPawłowski,	EFudali,DSzpytman,STomaszewski,LChorbowy,MSzpytman,WBajda);

        ChampionsLeagueCompetitors[0].addPlayer(KCas);
        ChampionsLeagueCompetitors[0].addPlayer(Isco);
        ChampionsLeagueCompetitors[0].addPlayer(Casemiro);
        ChampionsLeagueCompetitors[0].addPlayer(KBenzema);
        ChampionsLeagueCompetitors[0].addPlayer(MAsensio);
        ChampionsLeagueCompetitors[0].addPlayer(RVarane);
        ChampionsLeagueCompetitors[0].addPlayer(LVasquez);

        ChampionsLeagueCompetitors[1].addPlayer(JMascherano);
        ChampionsLeagueCompetitors[1].addPlayer(AGomes);
        ChampionsLeagueCompetitors[1].addPlayer(PAlcacer);
        ChampionsLeagueCompetitors[1].addPlayer(Paulinho);
        ChampionsLeagueCompetitors[1].addPlayer(JCilessen);
        ChampionsLeagueCompetitors[1].addPlayer(GDeufoleu);
        ChampionsLeagueCompetitors[1].addPlayer(NSemedo);

        ChampionsLeagueCompetitors[2].addPlayer(LDaraż);
        ChampionsLeagueCompetitors[2].addPlayer(DGłowacz);
        ChampionsLeagueCompetitors[2].addPlayer(AWanat);
        ChampionsLeagueCompetitors[2].addPlayer(MPawlowski);
        ChampionsLeagueCompetitors[2].addPlayer(MBus);
        ChampionsLeagueCompetitors[2].addPlayer(JKwasny);
        ChampionsLeagueCompetitors[2].addPlayer(KPastuszek);

        ChampionsLeagueCompetitors[3].addPlayer(CBravo);
        ChampionsLeagueCompetitors[3].addPlayer(IGundogan);
        ChampionsLeagueCompetitors[3].addPlayer(BSilva);
        ChampionsLeagueCompetitors[3].addPlayer(LSane);
        ChampionsLeagueCompetitors[3].addPlayer(RSterling);
        ChampionsLeagueCompetitors[3].addPlayer(VKompany);
        ChampionsLeagueCompetitors[3].addPlayer(GJesus);

        ChampionsLeagueCompetitors[4].addPlayer(DOspina);
        ChampionsLeagueCompetitors[4].addPlayer(DWelbeck);
        ChampionsLeagueCompetitors[4].addPlayer(AIwobi);
        ChampionsLeagueCompetitors[4].addPlayer(PMertesacker);
        ChampionsLeagueCompetitors[4].addPlayer(OGiroud);
        ChampionsLeagueCompetitors[4].addPlayer(TWalcott);
        ChampionsLeagueCompetitors[4].addPlayer(FCoquelin);


        ChampionsLeagueCompetitors[5].addPlayer(WCaballero);
        ChampionsLeagueCompetitors[5].addPlayer(Willian);
        ChampionsLeagueCompetitors[5].addPlayer(Christensen);
        ChampionsLeagueCompetitors[5].addPlayer(DDrinkwater);
        ChampionsLeagueCompetitors[5].addPlayer(Fabregas);
        ChampionsLeagueCompetitors[5].addPlayer(Batshuay);
        ChampionsLeagueCompetitors[5].addPlayer(DZapacosta);

        ChampionsLeagueCompetitors[6].addPlayer(Ulreich);
        ChampionsLeagueCompetitors[6].addPlayer(JRodriguez);
        ChampionsLeagueCompetitors[6].addPlayer(KComan);
        ChampionsLeagueCompetitors[6].addPlayer(JMartinez);
        ChampionsLeagueCompetitors[6].addPlayer(Friedl);
        ChampionsLeagueCompetitors[6].addPlayer(Pantovic);
        ChampionsLeagueCompetitors[6].addPlayer(Rafinha);

        ChampionsLeagueCompetitors[7].addPlayer(LMoura);
        ChampionsLeagueCompetitors[7].addPlayer(Areola);
        ChampionsLeagueCompetitors[7].addPlayer(Meunier);
        ChampionsLeagueCompetitors[7].addPlayer(DiMaria);
        ChampionsLeagueCompetitors[7].addPlayer(Beriche);
        ChampionsLeagueCompetitors[7].addPlayer(Kimpembe);
        ChampionsLeagueCompetitors[7].addPlayer(Draxler);

        ChampionsLeagueCompetitors[8].addPlayer(Benaglio);
        ChampionsLeagueCompetitors[8].addPlayer(Carillo);
        ChampionsLeagueCompetitors[8].addPlayer(Ghezzal);
        ChampionsLeagueCompetitors[8].addPlayer(Jovetic);
        ChampionsLeagueCompetitors[8].addPlayer(Keita);
        ChampionsLeagueCompetitors[8].addPlayer(ARaggi);
        ChampionsLeagueCompetitors[8].addPlayer(Tielemans);

        ChampionsLeagueCompetitors[9].addPlayer(Vorm);
        ChampionsLeagueCompetitors[9].addPlayer(Aurier);
        ChampionsLeagueCompetitors[9].addPlayer(Dier);
        ChampionsLeagueCompetitors[9].addPlayer(Trippier);
        ChampionsLeagueCompetitors[9].addPlayer(Winks);
        ChampionsLeagueCompetitors[9].addPlayer(Llorente);
        ChampionsLeagueCompetitors[9].addPlayer(Sissoko);

        ChampionsLeagueCompetitors[10].addPlayer(Dahoud);
        ChampionsLeagueCompetitors[10].addPlayer(Toprak);
        ChampionsLeagueCompetitors[10].addPlayer(Weidenfeller);
        ChampionsLeagueCompetitors[10].addPlayer(Yarmolenko);
        ChampionsLeagueCompetitors[10].addPlayer(Guerreiro);
        ChampionsLeagueCompetitors[10].addPlayer(Schmelzer);
        ChampionsLeagueCompetitors[10].addPlayer(Maximilian);

        ChampionsLeagueCompetitors[11].addPlayer(Vietto);
        ChampionsLeagueCompetitors[11].addPlayer(Thomas);
        ChampionsLeagueCompetitors[11].addPlayer(Correa);
        ChampionsLeagueCompetitors[11].addPlayer(Gimenez);
        ChampionsLeagueCompetitors[11].addPlayer(Lucas);
        ChampionsLeagueCompetitors[11].addPlayer(Saul);
        ChampionsLeagueCompetitors[11].addPlayer(Moya);


        ChampionsLeagueCompetitors[12].addPlayer(LMoura);
        ChampionsLeagueCompetitors[12].addPlayer(Areola);
        ChampionsLeagueCompetitors[12].addPlayer(Meunier);
        ChampionsLeagueCompetitors[12].addPlayer(DiMaria);
        ChampionsLeagueCompetitors[12].addPlayer(Beriche);
        ChampionsLeagueCompetitors[12].addPlayer(Kimpembe);
        ChampionsLeagueCompetitors[12].addPlayer(Draxler);

        ChampionsLeagueCompetitors[13].addPlayer(LMoura);
        ChampionsLeagueCompetitors[13].addPlayer(Areola);
        ChampionsLeagueCompetitors[13].addPlayer(Meunier);
        ChampionsLeagueCompetitors[13].addPlayer(DiMaria);
        ChampionsLeagueCompetitors[13].addPlayer(Beriche);
        ChampionsLeagueCompetitors[13].addPlayer(Kimpembe);
        ChampionsLeagueCompetitors[13].addPlayer(Draxler);

        ChampionsLeagueCompetitors[14].addPlayer(BBajda);
        ChampionsLeagueCompetitors[14].addPlayer(JGerula);
        ChampionsLeagueCompetitors[14].addPlayer(PGerula);
        ChampionsLeagueCompetitors[14].addPlayer(JBajda);
        ChampionsLeagueCompetitors[14].addPlayer(DBochenek);
        ChampionsLeagueCompetitors[14].addPlayer(PMikita);
        ChampionsLeagueCompetitors[14].addPlayer(EKowalinski);

        ChampionsLeagueCompetitors[15].addPlayer(JGluszko);
        ChampionsLeagueCompetitors[15].addPlayer(KZabawski);
        ChampionsLeagueCompetitors[15].addPlayer(DZabawski);
        ChampionsLeagueCompetitors[15].addPlayer(SFednar);
        ChampionsLeagueCompetitors[15].addPlayer(MFednar);
        ChampionsLeagueCompetitors[15].addPlayer(DSwist);
        ChampionsLeagueCompetitors[15].addPlayer(MSwist);


        //ustawienie najlepszych formacji dla każdej z drużyn
        for(int i=0; i<ChampionsLeagueCompetitors.length-1; i++) ChampionsLeagueCompetitors[i].bestSetUp(formations,false);
        Group GroupsA = new Group("A",ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0]);
        GroupsA.Randomo(ChampionsLeagueCompetitors);
        // stworzenie grup w których rozgrywane będą mecze (program działa tak, aby w jednej grupie nie spotkały się dwie drużyny z tego samego państwa)


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LigaMistrzowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LigaMistrzowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LigaMistrzowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LigaMistrzowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LigaMistrzowGUI(ChampionsLeagueCompetitors,formations).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel score1;
    private javax.swing.JLabel score2;
    private javax.swing.JLabel score3;
    private javax.swing.JLabel score4;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel score5;
    private javax.swing.JLabel score6;
    private javax.swing.JLabel score7;
    private javax.swing.JLabel score8;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel score9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel score10;
    private javax.swing.JLabel score11;
    private javax.swing.JLabel score12;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel score13;
    private javax.swing.JLabel score14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel score15;
    private javax.swing.JLabel score16;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel score17;
    private javax.swing.JLabel score18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel score19;
    private javax.swing.JLabel score20;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel score21;
    private javax.swing.JLabel score22;
    private javax.swing.JLabel score23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel score24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel team01;
    private javax.swing.JPanel team0;
    private javax.swing.JLabel team02;
    private javax.swing.JLabel team03;
    private javax.swing.JLabel team04;
    private javax.swing.JLabel team05;
    private javax.swing.JLabel team06;
    private javax.swing.JLabel team11;
    private javax.swing.JLabel team12;
    private javax.swing.JLabel team13;
    private javax.swing.JLabel team14;
    private javax.swing.JLabel team15;
    private javax.swing.JLabel team16;
    private javax.swing.JLabel team21;
    private javax.swing.JLabel team22;
    private javax.swing.JLabel team23;
    private javax.swing.JLabel team24;
    private javax.swing.JLabel team25;
    private javax.swing.JLabel team26;
    private javax.swing.JLabel team31;
    private javax.swing.JLabel team32;
    private javax.swing.JLabel team33;
    private javax.swing.JLabel team34;
    private javax.swing.JLabel team35;
    private javax.swing.JLabel team36;
    private javax.swing.JLabel[] labele = {score1,score2,score3,score4,score5,score6,score7,score8,score9,score10,score11,score12,score13,score14,score15,score16,score17,score18,score19,score20,score21,score22,score23,score24};

    // End of variables declaration
}
