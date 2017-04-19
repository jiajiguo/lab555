package com.jiajiguo12gmail.labtwo;

import java.io.Serializable;

/**
 * Created by Jiaji Guo on 2017/3/1.
 */

public class Team implements Serializable {
    int teamID;
    String teamLogo;
    String teamName;
    String scheduleText;

    String detailText1;
    String stadiumLocation;
    String opponentTeam;
    String opponentRecord;
    String irishRecord;
    String gameScore;
    String gameStatus;
    String detailImage1;
//    String detailImage2;

    //public Team (String team_name, team_logo, schedule_text, detail_text1, stadium_location, opponent_team, opponent_record,irish_record, game_score, game_status, detail_image1, detail_image2){
    public Team(String team_logo, String team_name, String schedule_text,  String detail_text1, String stadium_location, String opponent_team, String opponent_record, String irish_record, String game_score, String game_status, String detail_image1) {
        setTeamName(team_name);      //create the setteamname function here
        setTeamLogo(team_logo);
        setScheduleText(schedule_text);
        setDetailText1(detail_text1);
        setStadiumLocation(stadium_location);
        setOpponentTeam(opponent_team);
        setOpponentRecord(opponent_record);
        setIrishRecord(irish_record);
        setGameScore(game_score);
        setGameStatus(game_status);
        setDetailImage(detail_image1);

    }


    public String setTeamName(String team_name) {
        this.teamName = team_name;
        return this.teamName;
    }
    public String setTeamLogo(String team_logo) {
        this.teamLogo = team_logo;
        return this.teamLogo;
    }
    public String setScheduleText(String schedule_text) {
        this.scheduleText =schedule_text;
        return this.scheduleText;
    }
    public String setStadiumLocation(String stadium_location){
        this.stadiumLocation = stadium_location;
        return this.stadiumLocation;}

    public String setDetailText1(String detail_text1) {
        this.detailText1 = detail_text1;
        return this.detailText1;
    }

    public String setOpponentTeam(String opponent_team) {
        this.opponentTeam =opponent_team;
        return this.opponentTeam;
    }

    public String setOpponentRecord(String opponent_record) {
        this.opponentRecord =opponent_record;
        return this.opponentRecord;
    }

    public String setIrishRecord(String irish_record) {
        this.irishRecord =irish_record;
        return this.irishRecord;
    }

    public String setGameScore(String game_score) {
        this.gameScore = game_score;
        return this.gameScore;
    }

    public String setGameStatus(String game_status) {
        this.gameStatus = game_status;
        return this.gameStatus;
    }

    public String setDetailImage(String detail_image1) {
        this.detailImage1 = detail_image1;
        return this.detailImage1;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getTeamLogo() {
        return this.teamLogo;
    }

    public String getScheduleText() {
        return this.scheduleText;
    }

    public String getStadiumLocation(){
        return this.stadiumLocation;
    }

    public String getDetailText1(){
        return this.detailText1;
    }

    public String getOpponentTeam(){
        return this.opponentTeam;
    }

    public String getOpponentRecord(){
        return this.opponentRecord;
    }

    public String getIrishRecord(){
        return this.irishRecord;
    }
    public String getGameScore(){
        return this.gameScore;
    }
    public String getGameStatus() {
        return this.gameStatus;
    }

    public String getDetailImage1() {
        return this.detailImage1;
    }
}
