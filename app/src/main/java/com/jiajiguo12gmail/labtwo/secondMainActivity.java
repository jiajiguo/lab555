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


public class secondMainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);







        final ArrayList<String[]> information = new ArrayList();
        information.add(new String[] {"northcarolinalogo","North Carolina", "Feb 5","Sunday, February 5, 13:00","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Demon Deacons","(14-10)","(18-7)","81-88","Final"});
        information.add(new String[] {"wakeforestlogo","Wake Forest", "Feb 7","Tuesday, February 7, 19:00","Greensboro Coliseum, Greensboro, North Carolina","Tar Heels","(21-4)","(17-7)","83-76","Final"});
        information.add(new String[] {"floridastatelogo","Florida State", "Feb 11","Saturday，February 11th, 18:00","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Seminoles","(21-5)","(19-7)","72-84","Final"});
        information.add(new String[] {"bostoncollegelogo","Bostoncollege", "Feb 14","Tuesday, February 14th, 19:00","Silvio O. Conte Forum, Chestnut Hill, Massachusetts","Eagles","(9-18)","(20-7)","76-84","Final"});
        information.add(new String[] {"northcarolinastatelogo","North Carolina State", "Feb 18","Saturday, February 18, 12:00","PNC Arena, Raleigh, North Carolina","Wolfpack","(14-14)","(21-7)","72-81","Final"});
        information.add(new String[] {"georgiatechlogo","Georgia Tech", "Feb 26","Sunday, February 26, 18:30 on ESPU","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Yellow Jackets","(15-11)","(21-7)","0-0","Not Start yet"});
        information.add(new String[] {"bostoncollegelogo","Boston College", "March 1","Wednesday, March 1, 20:00 on ESFC","Purcell Pavilion at the Joyce Center, Notre Dame, Indiana","Eagles","(9-18)","(21-7)","0-0","Not Start yet"});
        information.add(new String[] {"louisvillelogo","Louisville", "March 4","Saturday, March 4, 14:00 on CBS","KFC Yum! Center, Louisville, Kentucky","Cardinals","(22-5)","(21-7)","0-0","Not Start yet"});
//        information.add(new String[] {"medallogo","ACC Tournament", "March 7"});
//        information.add(new String[] {"medallogo","ACC Tournament", "March 16"});
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, information);
        final ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);


        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent = new Intent(secondMainActivity.this, DetailActivity.class);
                String[] aa= information.get(position);
                intent.putExtra("team", aa);
                startActivity(intent);




            }

        };

        scheduleListView.setOnItemClickListener(clickListener);





    }
}
