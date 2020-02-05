package com.example.myapplication5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    String password;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Mydatabase.db";
    RecyclerviewActivity rcA;
    // Contacts table name
    //private static final String TABLE_REGISTER= "register";
    private static final String TABLE_REGISTERS= "registers";

    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "user_name";
//    public static final String KEY_lAST_NAME = "last_name";
    public static final String KEY_EMAIL_ID="email_id";
//    public static final String KEY_MOB_NO = "mobile_number";
    public static final String KEY_PASSWORD = "password";
    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_REGISTERS + "("
             + KEY_FIRST_NAME + " TEXT NOT NULL,"+KEY_EMAIL_ID + " TEXT PRIMARY KEY NOT NULL,"
             + KEY_PASSWORD + " TEXT NOT NULL " + ")";


    public DatabaseHandler(Context context, Object name, Object factory, int version) {

        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("!@#", "creating a table");
Log.d("!@#",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTERS);

        // Create tables again
        onCreate(db);
    }

    void addregister(RegisterData registerdata)
    // code to add the new register
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME,registerdata.getUsername()); // register first Name
//        values.put(KEY_lAST_NAME, registerdata. getlastName() ); // register last name
        values.put(KEY_EMAIL_ID, registerdata.getEmail());//register email id
//        values.put(KEY_MOB_NO, registerdata.getMobNo());//register mobile no
        values.put(KEY_PASSWORD, registerdata.getPassword());
        // Inserting Row

        db.insert(TABLE_REGISTERS, null, values);
        db.close(); // Closing database connection


    }



    //code to get the register
    String getregister(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        //String selectquery="SELECT * FROM TABLE_REGISTER";
        Cursor cursor=db.query(TABLE_REGISTERS,null,  KEY_FIRST_NAME + "=?",new String[]{username},null, null, null, null);

        if(cursor.getCount()<1){

            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            cursor.close();

        }
        return password;


    }


    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String get() {
        return KEY_ID;
    }

    public static String getTableContacts() {
        return TABLE_REGISTERS;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public List<RegisterData> listContacts(){
        rcA = new RecyclerviewActivity();
        Log.d("sed","do");
        String sql = "select * from " + TABLE_REGISTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<RegisterData> storeContacts = new ArrayList<>();
        Log.d("sed","do1");
        Cursor cursor = db.rawQuery(sql, null);
        Log.d("sed","do2");
        if(cursor.moveToFirst()){
            Log.d("sed","do3");
            do{
                Log.d("sed","do");
                String username = cursor.getString(0);
                String email = cursor.getString(1);
                String password = cursor.getString(2);
                Log.d("sed",username);
                storeContacts.add(new RegisterData(username, email, password));
            }while (cursor.moveToNext());
        }
        cursor.close();
       // Log.d("sed",storeContacts.get(0).password);
        return storeContacts;
    }

    public void addContacts(Movie contacts){
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, contacts.getusername());
        values.put(KEY_EMAIL_ID, contacts.getemail());
        values.put(KEY_PASSWORD, contacts.getpassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DATABASE_NAME, null, values);
    }

    public void updateContacts(Movie contacts){
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, contacts.getusername());
        values.put(KEY_EMAIL_ID, contacts.getemail());
        values.put(KEY_PASSWORD, contacts.getpassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DATABASE_NAME, values, KEY_EMAIL_ID	+ "	= ?", new String[] { String.valueOf(contacts.getemail())});
    }

    public Movie findContacts(String name){
        String query = "Select * FROM "	+ DATABASE_NAME + " WHERE " + KEY_FIRST_NAME + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        Movie contacts = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            String username = cursor.getString(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            contacts = new Movie(username, email, password);
        }
        cursor.close();
        return contacts;
    }

    public void deleteContact(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_NAME, KEY_EMAIL_ID	+ "	= ?", new String[] { String.valueOf(email)});
    }
    public void truncate(){
        SQLiteDatabase db = this.getWritableDatabase();

//        String query = "TRUNCATE TABLE register";
        db.delete(TABLE_REGISTERS,null,null);
        //db.execSQL("delete from "+ TABLE_REGISTER);
//        db.delete(TABLE_REGISTER, KEY_EMAIL_ID	+ "	= ?", new String[] { String.valueOf(email)});

    }
}
