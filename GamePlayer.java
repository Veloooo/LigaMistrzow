/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

public class GamePlayer {
    private String nickname;
    private Team team;

    public GamePlayer(String nickname, Team team) {
        this.nickname = nickname;
        this.team = team;
    }



    public String getNickname() {
        return nickname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
