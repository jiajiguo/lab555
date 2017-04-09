package com.jiajiguo12gmail.labtwo;

/**
 * Created by Jiaji Guo on 2017-03-26.
 */

public class GameContent {
    //private variables
    int _id;
    String _name;
    String _game_date;

    // Empty constructor
    public GameContent(){

    }
    // constructor
    public GameContent(int id, String name, String _game_date){
        this._id = id;
        this._name = name;
        this._game_date = _game_date;
    }

    // constructor
    public GameContent(String name, String _game_date){
        this._name = name;
        this._game_date = _game_date;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting game date
    public String getGameDate(){
        return this._game_date;
    }

    //public String getPhoneNumber(){return this._game_date;}

    // setting game date
    public void setGameDate(String game_date){
        this._game_date = game_date;
    }

}
