package com.jiajiguo12gmail.labtwo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Jiaji Guo on 2017/2/15.
 */

public class DetailActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView detailText1 = (TextView) findViewById(R.id.detailText1);
        TextView stadiumLocation = (TextView) findViewById(R.id.stadiumLocation);
        ImageView detailImage1 = (ImageView) findViewById(R.id.detailImage1);
        ImageView detailImage2 = (ImageView) findViewById(R.id.detailImage2);

//        TextView teamName = (TextView) scheduleView.findViewById(R.id.teamName);

        String sss = "fdsfd";





//        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
 //       startActivity(intent);
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        startActivity(intent);
//
        Button b = (Button) findViewById(R.id.btnOk);
        b.setOnClickListener(new View.OnClickListener() {
           // int CAMERA_PIC_REQUEST = 1337;

            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
              //   startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

           }
        });


        String[] stringInfo = getIntent().getStringArrayExtra("team");
        detailText1.setText(stringInfo[3]);
        String picname = stringInfo[0];
        int resID = getResources().getIdentifier(picname,"drawable",getPackageName());

        detailImage1.setImageResource(resID);
        detailImage2.setImageResource(R.drawable.notredamelogo);


    }


}
