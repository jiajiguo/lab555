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
 * Created by Jiaji Guo on 2017/2/16.
 */

 class DetailAdapter extends ArrayAdapter<String[]> {
    DetailAdapter(Context context, ArrayList<String[]> schedule) {
        super(context, R.layout.detail_item, schedule);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View detailView = scheduleInflater.inflate(R.layout.detail_item, parent, false);

        String[] matchItem = getItem(position);
        TextView gameLocation = (TextView) detailView.findViewById(R.id.gameLocation);
        TextView gameScore = (TextView) detailView.findViewById(R.id.gameScore);
        gameLocation.setText(matchItem[1]);
        gameScore.setText(matchItem[2]);

        ImageView teamLogo = (ImageView) detailView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem[0];
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID );

        return detailView;


    }

}
