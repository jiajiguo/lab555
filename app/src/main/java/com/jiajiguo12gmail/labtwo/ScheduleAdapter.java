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

class ScheduleAdapter extends ArrayAdapter<Team2> {
    ScheduleAdapter (Context context,int layoutResourceId, ArrayList<Team2> schedule) {
        super(context, layoutResourceId, schedule);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        Team2 matchItem = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.teamName);
        TextView scheduleText = (TextView) scheduleView.findViewById(R.id.scheduleText);

        teamName.setText(matchItem.getTeamName());
        scheduleText.setText(matchItem.getTime());

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem.getTeamLogo();
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID );

//        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
//        TextView date = (TextView) scheduleView.findViewById(R.id.timeText);
//        teamName.setText(team.getTeamName());
//        date.setText(team.getTime());
//
//        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
//        String mDrawableName = team.getTeamLogo();
//        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
//        teamLogo.setImageResource(resID );
        return scheduleView;
    }
}


