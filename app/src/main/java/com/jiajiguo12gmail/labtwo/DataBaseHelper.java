package com.jiajiguo12gmail.labtwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiaji Guo on 2017-04-17.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    private final static String DATABASE_NAME = "TEAMS.db";
    private final static int DATABASE_VERSION = 3;
    private final static String TABLE_NAME = "team_table";
    private final static String TABLE_IMAGES = "images";


    public DataBaseHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                TABLE_NAME +
                " (team_id INTEGER primary key autoincrement, " +
                "team_name text, " +
                "team_logo text, " +
                "scheduleText text, " +
                "stadiumLocation text, " +
                "opponentTeam text, " +
                "gameStatus text, " +
                "t_score integer, " +
                "p_score integer, " +
                "ts1 integer, " +
                "ps1 integer, " +
                "ts2 integer, " +
                "ps2 integer);";

        //修改了time -->scheduleText  gameType --> gameStatus address --> stadiumLocation slogan-->opponentTeam
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertData(Team2 team) {
        ContentValues values = new ContentValues();
        values.put("team_name", team.getTeamName());
        values.put("team_logo", team.getTeamLogo());
        values.put("scheduleText", team.getTime());
        values.put("stadiumLocation", team.getAddress());
        values.put("opponentTeam", team.getSlogan());
        values.put("gameStatus", team.gameStatus);
        values.put("t_score", team.tScore);
        values.put("p_score", team.pScore);
        values.put("ts1", team.ts1);
        values.put("ps1", team.ps1);
        values.put("ts2", team.ts2);
        values.put("ps2", team.ps2);
        getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public void deleteData(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE team_id=" + id;
        getWritableDatabase().execSQL(sql);
    }

    public ArrayList<Team2> selectData() {
        ArrayList<Team2> teams = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                //修改了time -->scheduleText  gameType --> gameStatus address --> stadiumLocation slogan-->opponentTeam
                Team2 team = new Team2();
                team.teamId = cursor.getInt(cursor.getColumnIndex("team_id"));
                team.stadiumLocation = cursor.getString(cursor.getColumnIndex("stadiumLocation"));
                team.gameStatus = cursor.getString(cursor.getColumnIndex("gameStatus"));
                team.teamName = cursor.getString(cursor.getColumnIndex("team_name"));
                team.teamLogo = cursor.getString(cursor.getColumnIndex("team_logo"));
                team.scheduleText = cursor.getString(cursor.getColumnIndex("scheduleText"));
                team.opponentTeam = cursor.getString(cursor.getColumnIndex("opponentTeam"));
                team.pScore = cursor.getInt(cursor.getColumnIndex("t_score"));
                team.tScore = cursor.getInt(cursor.getColumnIndex("t_score"));
                team.ts1 = cursor.getInt(cursor.getColumnIndex("ts1"));
                team.ps1 = cursor.getInt(cursor.getColumnIndex("ps1"));
                team.ts2 = cursor.getInt(cursor.getColumnIndex("ts2"));
                team.ps2 = cursor.getInt(cursor.getColumnIndex("ps2"));

                teams.add(team);
            }

            return teams;
        } catch (Exception ex) {

        } finally {
            cursor.close();
        }

        return new ArrayList<>();
    }

    public Team2 getDataById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        Team2 team = new Team2();
        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                team.teamId = cursor.getInt(cursor.getColumnIndex("team_id"));
                team.stadiumLocation = cursor.getString(cursor.getColumnIndex("stadiumLocation"));
                team.gameStatus = cursor.getString(cursor.getColumnIndex("gameStatus"));
                team.teamName = cursor.getString(cursor.getColumnIndex("team_name"));
                team.teamLogo = cursor.getString(cursor.getColumnIndex("team_logo"));
                team.scheduleText = cursor.getString(cursor.getColumnIndex("scheduleText"));
                team.opponentTeam = cursor.getString(cursor.getColumnIndex("opponentTeam"));
                team.pScore = cursor.getInt(cursor.getColumnIndex("t_score"));
                team.tScore = cursor.getInt(cursor.getColumnIndex("t_score"));
                team.ts1 = cursor.getInt(cursor.getColumnIndex("ts1"));
                team.ps1 = cursor.getInt(cursor.getColumnIndex("ps1"));
                team.ts2 = cursor.getInt(cursor.getColumnIndex("ts2"));
                team.ps2 = cursor.getInt(cursor.getColumnIndex("ps2"));
                return team;
            }
        } catch (Exception ex) {

        } finally {
            cursor.close();
        }
        return null;
    }

}
