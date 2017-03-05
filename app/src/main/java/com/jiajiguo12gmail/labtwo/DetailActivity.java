package com.jiajiguo12gmail.labtwo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.os.Environment.DIRECTORY_PICTURES;
import static com.jiajiguo12gmail.labtwo.R.id.teamLogo;
import static java.security.AccessController.getContext;

/**
 * Created by Jiaji Guo on 2017/2/15.
 */

public class DetailActivity extends AppCompatActivity {

    Context context;
    public static int CAMERA_REQUEST = 1888;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// throws FileNotFoundException {

        super.onCreate(savedInstanceState);
        if (shouldAskPermissions()) {
            askPermissions();
        }
        setContentView(R.layout.activity_detail);
        TextView detailText1 = (TextView) findViewById(R.id.detailText1);
        TextView stadiumLocation = (TextView) findViewById(R.id.stadiumLocation);
        TextView opponentTeam = (TextView) findViewById(R.id.opponentTeam);
        TextView opponentRecord = (TextView) findViewById(R.id.opponentRecord);
        TextView irishRecord = (TextView) findViewById(R.id.irishRecord);
        TextView gameScore = (TextView) findViewById(R.id.gameScore);
        TextView gameStatus = (TextView) findViewById(R.id.gameStatus);

        ImageView detailImage1 = (ImageView) findViewById(R.id.detailImage1);


        ImageView detailImage2 = (ImageView) findViewById(R.id.detailImage2);

        ImageView photoTaken = (ImageView) findViewById(R.id.photoTaken);

//        try  {
//            File dir= context.getDir("dirname", Context.MODE_PRIVATE); //Creates Dir inside internal memory
//            File file0= new File(dir, "filename");  //It has directory details and file name
//            FileOutputStream fos = new FileOutputStream(file0);
//        }   catch(FileNotFoundException e) {
//            };

        //if file0 exists in the directory,  FileOutputStream fos = new FileOutputStream(file0) else throws FileNotFoundException


        Button b = (Button) findViewById(R.id.btnOk);

        b.setOnClickListener(new View.OnClickListener() {
            // int CAMERA_PIC_REQUEST = 1337;
            //int CAMERA_REQUEST = 1888;


            public void onClick(View v) {
                //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //  startActivity(cameraIntent);

                File PictureDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES);//All should be in upper case?
                // setTimeStamp();
                String pictureName = getPictureName();
                File imageFile = new File(PictureDirectory, pictureName);
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);   //value should be here
                System.out.println(imageFile.getAbsoluteFile());
                System.out.println(pictureUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        Team team = (Team) getIntent().getSerializableExtra("team");

        String mDrawableName = team.getTeamLogo();
        int resID = getResources().getIdentifier(mDrawableName,"drawable",getPackageName());

        detailImage1.setImageResource(resID);


        stadiumLocation.setText(team.getStadiumLocation());
        detailText1.setText(team.getDetailText1());
        opponentTeam.setText(team.getOpponentTeam());
        opponentRecord.setText(team.getOpponentRecord());
        irishRecord.setText(team.getIrishRecord());
        gameScore.setText(team.getGameScore());
        gameStatus.setText(team.getGameStatus());


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    String timestamp;

    //
    public void setTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        timestamp = sdf.format(new Date());
    }

    public String getPictureName() {
        return "BestMoments.jpg";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.print("hello" + resultCode);
        if (resultCode == RESULT_OK) {
            System.out.print("hello444");

            if (requestCode == CAMERA_REQUEST) {
                //               Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES);

                String pictureDirectoryPath = pictureDirectory.getAbsolutePath();
                System.out.print(pictureDirectoryPath);
                Uri imageUri = Uri.parse("file://" + pictureDirectoryPath + File.separator + getPictureName());

                System.out.print(imageUri);

                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ImageView imgView = (ImageView) findViewById(R.id.photoTaken);
                    imgView.setImageBitmap(image);
//
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Detail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
//




//        String[] stringInfo = getIntent().getStringArrayExtra("team");

//        detailText1.setText(stringInfo[3]);
//       stadiumLocation.setText(stringInfo[4]);
//        opponentTeam.setText(stringInfo[5]);
//        opponentRecord.setText(stringInfo[6]);
//        irishRecord.setText(stringInfo[7]);
//        gameScore.setText(stringInfo[8]);
//        gameStatus.setText(stringInfo[9]);
//
//
//        String picname = stringInfo[0];
      //  int resID = getResources().getIdentifier(picname,"drawable",getPackageName());

 //       detailImage1.setImageResource(resID);
   //     detailImage2.setImageResource(R.drawable.notredamelogo);

