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


public class secondMainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);







        final ArrayList<String[]> information = new ArrayList();
        information.add(new String[] {"floridastatelogo","Florida State", "Feb 11","Saturday，February 11th, 18:00"});
        information.add(new String[] {"bostoncollegelogo","Bostoncollege", "Feb 14","Tuesday, February 14th, 19:00"});

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
