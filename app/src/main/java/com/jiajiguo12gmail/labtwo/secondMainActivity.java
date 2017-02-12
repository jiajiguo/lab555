package com.jiajiguo12gmail.labtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.*;



public class secondMainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);

        //String[] str = {"aaa","bbb","ccc","ddd","eee","fff","ggg","hhh"};
        //ListAdapter scheduleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        //github test
        List<String[]> lst = new ArrayList<String[]>();
        ArrayList information = new ArrayList();
        information.add(new String[] {"floridastatelogo","Florida State", "Feb 11"});
        information.add(new String[] {"bostoncollegelogo","Boston College", "Feb 14"});
        information.add(new String[] {"northcarolinastatelogo","North Carolina State", "Feb 18"});
        information.add(new String[] {"georgiatechlogo","Georgia Tech", "Feb 26"});
        information.add(new String[] {"bostoncollegelogo","Boston College", "March 1"});
        information.add(new String[] {"louisvillelogo","Louisville", "March 4"});
        information.add(new String[] {"medallogo","ACC Tournament", "March 7"});
        information.add(new String[] {"medallogo","ACC Tournament", "March 16"});




        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, information);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
    }
}
