package com.jiajiguo12gmail.labtwo;

import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.ContextMenu.ContextMenuInfo;


import java.io.File;
import java.net.URL;
import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;


public class secondMainActivity extends AppCompatActivity {

    String string1 = "freeenergy";
    Button buttontogallery;
    int num = 1;   //please do not
    TextView opponent_name_show;
    EditText opponent_name_edit;

    DatabaseHandler db ;

//    public void fn_insert(View view) {
//        String opponentname = String.valueOf(opponent_name_edit.getText());
//        int gameid = num++;
//        db.insertData(new GameContent(gameid, opponentname,"3456"));
//        opponent_name_edit.setText("");
//        opponent_name_show.setText(db.getString());
//        String1 = db.getString();
//    }

    public void fn_insert(View view) {
        String opponentname = String.valueOf(opponent_name_edit.getText());
        int gameid = num++;
        db.addGameContent(new GameContent(gameid, opponentname,"3456"));
        opponent_name_edit.setText("");
        opponent_name_show.setText(db.getString());
        string1 = opponentname;

        Intent intent = new Intent(secondMainActivity.this, secondMainActivity.class);

        intent.putExtra("string1", string1);
        //// TODO: 2017-04-08
//
        finish();
        startActivity(intent);
    }


    private CoordinatorLayout coordinatorLayout;
    public GameContent testContent;

    //   ArrayList <String[]> information = new ArrayList();
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<String[]> txtteams;

    //2017 03 17

    private ShareActionProvider miShareAction;

    @Override//homework requirement
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.share);
        // Fetch and store ShareActionProvider
        return super.onCreateOptionsMenu(menu);
    }

//    Image image = new Image(file.toURI().toString());
//    ImageView.setImage(image);
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();


        if (res_id == R.id.share) {
// code for sharing the schedule
            //ShareActionProvider miShare = (ShareActionProvider) MenuItemCompat.getActionProvider(item); //share via text
//            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.setType("text/plain");
//            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
//            shareIntent.putExtra("android.content.EXTRA_TEXT","breakfast" );
//            startActivity(Intent.createChooser(shareIntent,"Share via"));

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
//           sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Guess my weight?");
            sendIntent.putExtra(Intent.EXTRA_TEXT, gameSchedule(txtteams));//TODO arraylist
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
//        Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);    //this is method to share fotos
//        picMessageIntent.setType("image/jpeg");
//        File downloadedPic =  new File("turtle");
//        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
//        startActivity(Intent.createChooser(picMessageIntent, "Send Notre Dame basketball schedule using:"));

//        Intent intent2 = new Intent();   //share by email
//        intent2.setAction(Intent.ACTION_SEND);
//        intent2.setType("message/rfc822");
//        intent2.putExtra(Intent.EXTRA_EMAIL, new String[]{"jiajiguo12@gmail.com", "jguo1@nd.edu"});
//        intent2.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
//        intent2.putExtra(Intent.EXTRA_TEXT, "Your text here" );
//        startActivity(intent2);

        } else if (res_id == R.id.sync) {

            coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
            final Snackbar snackbar = Snackbar.make(coordinatorLayout, "It is Sunday today", Snackbar.LENGTH_LONG);
            snackbar.setAction("Try Again", new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();
                }

            });

            snackbar.show();
            snackbar.setDuration(10000);
// Snackbar with Try Again action
        } else if (res_id == R.id.settings) {

// Floating Contextual Menu with options
            // registerForContextMenu(turtle);
            //registerForContextMenu((View) item);
//            View menuItemView = findViewById(R.id.settings);// SAME ID AS MENU ID
//            PopupMenu popupMenu = new PopupMenu(this, menuItemView);
//            popupMenu.inflate(R.menu.menu_two);
//            // ...
//            popupMenu.show();
//            // ...
        }
        return true;
    }      //process of setting toolbar's share sync and settings


    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
       // menu.add(0, v.getId(), 0, "Action 1");
      //  menu.add(0, v.getId(), 0, "Action 2");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.floating_contextual_menu, menu);// here write code to inflate floating_contextual_menu xml file
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Action 1"){function1(item.getItemId());}
        else if(item.getTitle()=="Action 2"){function2(item.getItemId());}
        else {return false;}
        return true;
    }

    public void function1(int id){
        Toast.makeText(this, "function 1 called", Toast.LENGTH_SHORT).show();
    }
    public void function2(int id){
        Toast.makeText(this, "function 2 called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //db = new DatabaseHandler(this.getApplicationContext());
        db = new DatabaseHandler(this);
        setTheme(R.style.MyMaterialTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);


        if (getIntent().getExtras() != null)
        {
            string1 = getIntent().getExtras().getString("string1").toString();

            Log.d("fffffffffffffffff",string1);
            //  TODO: 2017-04-08
        }

        registerForContextMenu((View) findViewById(R.id.button8));

        buttontogallery = (Button) findViewById(R.id.buttontogallery);

        opponent_name_show = (TextView) findViewById(R.id.opponent_team_show);
        opponent_name_edit = (EditText) findViewById(R.id.opponent_name_edit);

        opponent_name_edit.setText("");
        if (db.getString()!=null) {
            opponent_name_show.setText(db.getString());
        }else             opponent_name_show.setText("jishen");


        buttontogallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(secondMainActivity.this,
                        GalleryActivity.class);
                startActivity(myIntent);
            }
        });


      //  2017 03 17
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitle("Notre Dame Atheletics");

        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(this);
        txtteams = myCsvFileReader.readCsvFile(R.raw.schedule);


//2017 03 17 22:30
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(android.content.Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
//        shareIntent.putExtra("android.content.Intent.EXTRA_TEXT","testtexthere" );
//        startActivity(Intent.createChooser(shareIntent,"Share via"));       //seletion conversation part

        //gameSchedule(txtteams)//// TODO: 2017-04-08


        Team floridastate = new Team(txtteams.get(0)[0], txtteams.get(0)[1], txtteams.get(0)[2], txtteams.get(0)[3], txtteams.get(0)[4], txtteams.get(0)[5], txtteams.get(0)[6], txtteams.get(0)[7], txtteams.get(0)[8], txtteams.get(0)[9], txtteams.get(0)[0]);
        Team northcarolina = new Team(txtteams.get(1)[0], string1, txtteams.get(1)[2], txtteams.get(1)[3], txtteams.get(1)[4], txtteams.get(1)[5], txtteams.get(1)[6], txtteams.get(1)[7], txtteams.get(1)[8], txtteams.get(1)[9], txtteams.get(1)[0]);
        Team wakeforest = new Team(txtteams.get(2)[0], txtteams.get(2)[1], txtteams.get(2)[2], txtteams.get(2)[3], txtteams.get(2)[4], txtteams.get(2)[5], txtteams.get(2)[6], txtteams.get(2)[7], txtteams.get(2)[8], txtteams.get(2)[9], txtteams.get(2)[0]);
//        Team bostoncollege = new Team(String1, txtteams.get(3)[1], txtteams.get(3)[2], txtteams.get(3)[3], txtteams.get(3)[4], txtteams.get(3)[5], txtteams.get(3)[6], txtteams.get(3)[7], txtteams.get(3)[8], txtteams.get(3)[9], txtteams.get(3)[0]);
//        Team northcarolinastate = new Team(txtteams.get(4)[0], txtteams.get(4)[1], txtteams.get(4)[2], txtteams.get(4)[3], txtteams.get(4)[4], txtteams.get(4)[5], txtteams.get(4)[6], txtteams.get(4)[7], txtteams.get(4)[8], txtteams.get(4)[9], txtteams.get(4)[0]);
//        Team geogiatech = new Team(txtteams.get(5)[0], txtteams.get(5)[1], txtteams.get(5)[2], txtteams.get(5)[3], txtteams.get(5)[4], txtteams.get(5)[5], txtteams.get(5)[6], txtteams.get(5)[7], txtteams.get(5)[8], txtteams.get(5)[9], txtteams.get(5)[0]);
//        Team bostoncollege2 = new Team(txtteams.get(6)[0], txtteams.get(6)[1], txtteams.get(6)[2], txtteams.get(6)[3], txtteams.get(6)[4], txtteams.get(6)[5], txtteams.get(6)[6], txtteams.get(6)[7], txtteams.get(6)[8], txtteams.get(6)[9], txtteams.get(6)[0]);
//        Team louisville = new Team(txtteams.get(7)[0], txtteams.get(7)[1], txtteams.get(7)[2], txtteams.get(7)[3], txtteams.get(7)[4], txtteams.get(7)[5], txtteams.get(7)[6], txtteams.get(7)[7], txtteams.get(7)[8], txtteams.get(7)[9], txtteams.get(7)[0]);



        teams.add(northcarolina);
        teams.add(wakeforest);
        teams.add(floridastate);
//        teams.add(bostoncollege);
//        teams.add(northcarolinastate);
//        teams.add(geogiatech);
//        teams.add(bostoncollege2);
//        teams.add(louisville);




        // DatabaseHandler db = new DatabaseHandler(this);     //what is the difference?

        Log.d("insert or delete here","C'est l'equipe");
     //   Log.d(txtteams.get(0)[3],"chifan");

//        db.addGameContent(new GameContent(txtteams.get(0)[3], txtteams.get(0)[3]));
//        db.addGameContent(new GameContent("geogiatech", "45Mar"));
//        db.deleteGameContent(new GameContent(1,"floridastate","22Feb"));
//        db.deleteGameContent(new GameContent(2,"floridastate","22Feb"));

       // testContent = db.getContact(1);

        Log.d("Reading: ", "Reading all contacts..");
        Log.d("test","test");
        //Log.d("Test:",testContent.getGameDate());
        Log.d("test","test");

        Log.d("Reading: ", "Reading all contacts..");
        List<GameContent> contacts = db.getAllGameContent();

        for (GameContent cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getGameDate();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

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
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teams);//
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent intent = new Intent(secondMainActivity.this, DetailActivity.class);
                //     String[] aa= information.get(position);
                Team aa = teams.get(position);//   Modify your onClickListener method to transfer the id of the team/game to the detail activity instead of the object in ArrayList.
                intent.putExtra("team", aa);
                startActivity(intent);
            }

        });



    }  // listview part



    @NonNull
    private String gameSchedule(ArrayList<String[]> txtteams) {

        // Create a new StringBuilder.
        StringBuilder gameSchedule = new StringBuilder();
        // Loop and append values.
        for (int i = 0; i < 3; i++) {
            gameSchedule.append(txtteams.get(i)[1]);
            gameSchedule.append(txtteams.get(i)[2]);
            gameSchedule.append(txtteams.get(i)[3]);
        }
        // Convert to string.
//        String result = gameSchedule.toString();
//
//        // Print result.
//        System.out.println(result);
        return gameSchedule.toString();
    }




}

