package com.jiajiguo12gmail.labtwo;

/**
 * Created by Jiaji Guo on 2017-03-26.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addGameContent(GameContent gamecontent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, gamecontent.getName()); // Contact Name
        values.put(KEY_PH_NO, gamecontent.getGameDate()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    GameContent getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        GameContent contact = new GameContent(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<GameContent> getAllGameContent() {
        List<GameContent> contactList = new ArrayList<GameContent>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GameContent contact = new GameContent();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setGameDate(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(GameContent contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getGameDate());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact, in delete process, we only need to figure out the id we want to delete, and the 2 strings following id are arbitory
    //example db.deleteContact(new Contact(2,"We want to have bife next year", "Are there anyone playing dota2 on campus?"));  is also ok....
    public void deleteGameContent(GameContent contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void insertData(GameContent gameContent) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, gameContent.getID());
        contentValues.put(KEY_NAME, gameContent.getName());
        contentValues.put(KEY_PH_NO, gameContent.getGameDate());
        long ret = db.insert(TABLE_CONTACTS, null, contentValues );


        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }


    public ArrayList<GameContent> selectData() {
        ArrayList<GameContent> teams = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                GameContent team = new GameContent();
                team._id = cursor.getInt(cursor.getColumnIndex("id"));
                team._name = cursor.getString(cursor.getColumnIndex("name"));
                team._game_date = cursor.getString(cursor.getColumnIndex("phone_number"));

                teams.add(team);

            }return teams;


        }catch (Exception ex) {

        } finally {
            cursor.close();
        }

        return new ArrayList<>();
    }

    public String getString() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, new String[]{});
        StringBuilder stringbuilder = new StringBuilder();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    stringbuilder.append(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    stringbuilder.append("\n");
                }while (cursor.moveToNext());
            }
        }

        cursor.close();
        return stringbuilder.toString();
    }

//    public String getCurrentString() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, new String[]{});
//        StringBuilder stringbuilder = new StringBuilder();
//
//        if (cursor != null ) {
//            if  (cursor.moveToFirst()) {
//                do {
//                    stringbuilder.append(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
//                }while (cursor.moveToNext());
//            }
//        }
//
//        cursor.close();
//        return stringbuilder.toString();
//    }
}