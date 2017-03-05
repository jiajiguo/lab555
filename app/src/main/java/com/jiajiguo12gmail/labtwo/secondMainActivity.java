package com.jiajiguo12gmail.labtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.Serializable;


public class secondMainActivity extends AppCompatActivity {

//   ArrayList <String[]> information = new ArrayList();
    ArrayList<Team> teams = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);


        ArrayList<String[]> txtteams;
        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(this);
        txtteams=myCsvFileReader.readCsvFile(R.raw.schedule);



// 2017 03 01
        Team floridastate       = new Team(txtteams.get(0)[0], txtteams.get(0)[1],txtteams.get(0)[2],txtteams.get(0)[3],txtteams.get(0)[4],txtteams.get(0)[5],txtteams.get(0)[6],txtteams.get(0)[7],txtteams.get(0)[8],txtteams.get(0)[9],txtteams.get(0)[0]);
        Team northcarolina =      new Team(txtteams.get(1)[0], txtteams.get(1)[1],txtteams.get(1)[2],txtteams.get(1)[3],txtteams.get(1)[4],txtteams.get(1)[5],txtteams.get(1)[6],txtteams.get(1)[7],txtteams.get(1)[8],txtteams.get(1)[9],txtteams.get(1)[0]);
        Team wakeforest         = new Team(txtteams.get(2)[0], txtteams.get(2)[1],txtteams.get(2)[2],txtteams.get(2)[3],txtteams.get(2)[4],txtteams.get(2)[5],txtteams.get(2)[6],txtteams.get(2)[7],txtteams.get(2)[8],txtteams.get(2)[9],txtteams.get(2)[0]);
        Team bostoncollege      = new Team(txtteams.get(3)[0], txtteams.get(3)[1],txtteams.get(3)[2],txtteams.get(3)[3],txtteams.get(3)[4],txtteams.get(3)[5],txtteams.get(3)[6],txtteams.get(3)[7],txtteams.get(3)[8],txtteams.get(3)[9],txtteams.get(3)[0]);
        Team northcarolinastate = new Team(txtteams.get(4)[0], txtteams.get(4)[1],txtteams.get(4)[2],txtteams.get(4)[3],txtteams.get(4)[4],txtteams.get(4)[5],txtteams.get(4)[6],txtteams.get(4)[7],txtteams.get(4)[8],txtteams.get(4)[9],txtteams.get(4)[0]);
        Team geogiatech =         new Team(txtteams.get(5)[0], txtteams.get(5)[1],txtteams.get(5)[2],txtteams.get(5)[3],txtteams.get(5)[4],txtteams.get(5)[5],txtteams.get(5)[6],txtteams.get(5)[7],txtteams.get(5)[8],txtteams.get(5)[9],txtteams.get(5)[0]);
        Team bostoncollege2 =     new Team(txtteams.get(6)[0], txtteams.get(6)[1],txtteams.get(6)[2],txtteams.get(6)[3],txtteams.get(6)[4],txtteams.get(6)[5],txtteams.get(6)[6],txtteams.get(6)[7],txtteams.get(6)[8],txtteams.get(6)[9],txtteams.get(6)[0]);
        Team louisville =         new Team(txtteams.get(7)[0], txtteams.get(7)[1],txtteams.get(7)[2],txtteams.get(7)[3],txtteams.get(7)[4],txtteams.get(7)[5],txtteams.get(7)[6],txtteams.get(7)[7],txtteams.get(7)[8],txtteams.get(7)[9],txtteams.get(7)[0]);

        teams.add(northcarolina);
        teams.add(wakeforest);
        teams.add(floridastate);
        teams.add(bostoncollege);
        teams.add(northcarolinastate);
        teams.add(geogiatech);
        teams.add(bostoncollege2);
        teams.add(louisville);


//        information.add(new String[] {"northcarolinalogo","North Carolina", "Feb 5","Sunday, February 5, 13:00","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Demon Deacons","(14-10)","(18-7)","83-76","Final"});
//        information.add(new String[] {"wakeforestlogo","Wake Forest", "Feb 7","Tuesday, February 7, 19:00","Greensboro Coliseum, Greensboro, North Carolina","Tar Heels","(21-4)","(17-7)","81-88","Final"});
//        information.add(new String[] {"floridastatelogo","Florida State", "Feb 11","Saturday，February 11th, 18:00","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Seminoles","(21-5)","(19-7)","72-84","Final"});
//        information.add(new String[] {"bostoncollegelogo","Bostoncollege", "Feb 14","Tuesday, February 14th, 19:00","Silvio O. Conte Forum, Chestnut Hill, Massachusetts","Eagles","(9-18)","(20-7)","76-84","Final"});
//        information.add(new String[] {"northcarolinastatelogo","North Carolina State", "Feb 18","Saturday, February 18, 12:00","PNC Arena, Raleigh, North Carolina","Wolfpack","(14-14)","(21-7)","72-81","Final"});
//        information.add(new String[] {"georgiatechlogo","Georgia Tech", "Feb 26","Sunday, February 26, 18:30 on ESPU","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Yellow Jackets","(15-11)","(21-7)","0-0","Not Start yet"});
//        information.add(new String[] {"bostoncollegelogo","Boston College", "March 1","Wednesday, March 1, 20:00 on ESFC","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Eagles","(9-18)","(21-7)","0-0","Not Start yet"});
//        information.add(new String[] {"louisvillelogo","Louisville", "March 4","Saturday, March 4, 14:00 on CBS","KFC Yum! Center, Louisville, Kentucky","Cardinals","(22-5)","(21-7)","0-0","Not Start yet"});
//        information.add(new String[] {"medallogo","ACC Tournament", "March 7"});
//        information.add(new String[] {"medallogo","ACC Tournament", "March 16"});
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);


        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent = new Intent(secondMainActivity.this, DetailActivity.class);
           //     String[] aa= information.get(position);
                Team aa= teams.get(position);
                intent.putExtra("team", aa);
                startActivity(intent);




            }

        });

    }
}
