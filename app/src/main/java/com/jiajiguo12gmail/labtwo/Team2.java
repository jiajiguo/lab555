package com.jiajiguo12gmail.labtwo;

import java.io.Serializable;

/**
 * Created by Jiaji Guo on 2017-04-08.
 */

public class Team2 implements Serializable {
    int teamId;
    String teamName;
    String teamLogo;
    String scheduleText;
    String stadiumLocation;
    String opponentTeam;
    String gameStatus;
    int tScore;
    int pScore;
    int ts1;
    int ps1;
    int ts2;
    int ps2;

    //修改了time -->scheduleText  gameType --> gameStatus address --> stadiumLocation slogan-->opponentTeam
    // gettime()

    //{"image101", "Ohio State", "Feb 11", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "31:41", "31:41"};

    // floridastatelogo, Florida State, Feb 11, Sunday February 5 13:00, Purcell Pavilion at the Joyce Center Notre Dame Indiana,Demon Deacons,(14-10),(18-7),83-76,Final
    public Team2() {
    }

    public Team2(String[] teamInfo) {
        teamName = teamInfo[1];
        teamLogo = teamInfo[0];
        scheduleText = teamInfo[2];
        stadiumLocation = teamInfo[3];
        opponentTeam = teamInfo[4];
        ts1 = Integer.parseInt(teamInfo[5].split(":")[0].trim());
        ps1 = Integer.parseInt(teamInfo[5].split(":")[1].trim());
        ts2 = Integer.parseInt(teamInfo[6].split(":")[0].trim());
        ps2 = Integer.parseInt(teamInfo[6].split(":")[1].trim());
        tScore = ts1 + ts2;
        pScore = ps1 + ps2;
        gameStatus = teamInfo[7];
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTime() {
        return scheduleText;
    }

    public String getAddress() {
        return stadiumLocation;
    }

    public String getSlogan() {
        return opponentTeam;
    }

    public int getTs1() {
        return ts1;
    }

    public int getPs1() {
        return ps1;
    }

    public int getTs2() {
        return ts2;
    }

    public int getPs2() {
        return ps2;
    }
}