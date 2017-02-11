package com.jiajiguo12gmail.labtwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jiaji Guo on 2017-02-10.
 */

class ScheduleAdapter extends ArrayAdapter<String[]> {
    ScheduleAdapter (Context context, ArrayList<String[]> schedule) {
        super(context, R.layout.schedule_item, schedule);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        String[] matchItem = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.teamName);
        TextView scheduleText = (TextView) scheduleView.findViewById(R.id.scheduleText);
        teamName.setText(matchItem[1]);
        scheduleText.setText(matchItem[2]);

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem[0];
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID );



        return scheduleView;
    }
}


