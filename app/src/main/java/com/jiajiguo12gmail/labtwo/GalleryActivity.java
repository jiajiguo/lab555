package com.jiajiguo12gmail.labtwo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jiaji Guo on 2017-03-26.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {


    Button addImage;
    FloatingActionButton prendrePhoto;
    ArrayList<Foto> imageArry = new ArrayList<Foto>();
    ContactImageAdapter imageAdapter;

    private static final int CAMERA_REQUEST = 1;

    GridView dataList;
    byte[] imageName;
    int imageId;
    Bitmap theImage;
    DataBaseHandler2 db2;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivity";

    CoordinatorLayout coordinatorLayout;
    FloatingActionButton btnSelectImage;
    AppCompatImageView imgView;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the views...
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        btnSelectImage = (FloatingActionButton) findViewById(R.id.btnSelectImage);
        imgView = (AppCompatImageView) findViewById(R.id.imgView);

        dataList = (GridView) findViewById(R.id.list);
        /**
         * create DatabaseHandler object
         */
        db2 = new DataBaseHandler2(this);
        /**
         * Reading and getting all records from database
         */
        byte[] bytes = new byte[100];
        Arrays.fill( bytes, (byte) 1 );

//        db.addContact(new Contact("geogiatech",bytes));

        List<Foto> contacts2 = db2.getAllContacts();
        for (Foto cn : contacts2) {
            String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                    + " ,Image: " + cn.getImage();

            // Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            imageArry.add(cn);
        }
        /**
         * Set Data base Item into listview
         */
        imageAdapter = new ContactImageAdapter(this, R.layout.screen_list, imageArry);
        dataList.setAdapter(imageAdapter);

        /**
         * open dialog for choose camera
         */
        final String[] option = new String[] {"Take from Camera"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
                    // callCamera();
                }
            }
        });
        final AlertDialog dialog = builder.create();

        addImage = (Button) findViewById(R.id.btnAdd);
        prendrePhoto = (FloatingActionButton)findViewById(R.id.btnAdd2);
        prendrePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callCamera();
            }
        });

        btnSelectImage.setOnClickListener(this);

        // Create the Database helper object
        dbHelper = new DBHelper(this);

    }


    // Show simple message using SnackBar
    void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                        showMessage("Image Saved in Database...");
                        imgView.setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadImageFromDB()) {
                                showMessage("Image Loaded from Database...");
                            }
                        }
                    }, 3000);
                }

            }
            if (requestCode == CAMERA_REQUEST){
                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();

                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    db2.addContact(new Foto("chifan", imageInByte));
                    Intent i = new Intent(GalleryActivity.this,
                            GalleryActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        }
    }

    /**
     * open camera method
     */
    public void callCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 200);
    }

    @Override
    public void onClick(View v) {
        openImageChooser();
    }

    // Save the
    Boolean saveImageInDB(Uri selectedImageUri) {

        try {
            dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            dbHelper.insertImage(inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }

    }

    Boolean loadImageFromDB() {
        try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB();
            dbHelper.close();
            // Show Image from DB in ImageView
            imgView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
}

