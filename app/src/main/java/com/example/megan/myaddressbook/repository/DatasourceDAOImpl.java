package com.example.megan.myaddressbook.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.megan.myaddressbook.domain.myContacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public class DatasourceDAOImpl implements DatasourceDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;

    public DatasourceDAOImpl(Context context) {dbHelper = new DatabaseHandler (context);}

    public void open() { db = dbHelper.getWritableDatabase();}

    public void close(){ dbHelper.close();}

    @Override
    public void addContact(myContacts contacts){
        try{
            open();
            ContentValues values = new ContentValues();
            values.put(DatabaseHandler.KEY_FIRSTNAME,contacts.getFirstName());
            values.put(DatabaseHandler.KEY_LASTNAME, contacts.getLastName());
            values.put(DatabaseHandler.KEY_EMAIL, contacts.getEmailAddy());
            values.put(DatabaseHandler.KEY_CELL, contacts.getCellNumber());
            values.put(DatabaseHandler.KEY_HOME, contacts.getHomeNumber());

            db.insert(DatabaseHandler.TABLE_CONTACTS, null, values);
            close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public myContacts getContact(int id) {
        try{
            open();
            Cursor cur = db.query(DatabaseHandler.TABLE_CONTACTS, new String []{
                            DatabaseHandler.KEY_ID, DatabaseHandler.KEY_FIRSTNAME, DatabaseHandler.KEY_LASTNAME,
                            DatabaseHandler.KEY_EMAIL, DatabaseHandler.KEY_CELL, DatabaseHandler.KEY_HOME},
                            DatabaseHandler.KEY_ID + " =? ", new String [] {String.valueOf(id)}, null, null, null, null);

            if(cur != null)
                cur.moveToFirst();

            myContacts con = new myContacts();
            con.setId(cur.getInt(0));
            con.setFirstName(cur.getString(1));
            con.setLastName(cur.getString(2));
            con.setEmailAddy(cur.getString(3));
            con.setCellNumber(cur.getString(4));
            con.setHomeNumber(cur.getString(5));

            close();

            return con;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<myContacts> getAllContacts() {

        List<myContacts> conList = new ArrayList<myContacts>();

        String selectALL = "SELECT * FROM " + DatabaseHandler.TABLE_CONTACTS;

        try{
            open();
            Cursor cur = db.rawQuery(selectALL, null);

            if(cur.moveToFirst()){
                do{
                    myContacts con = new myContacts();
                    con.setId(cur.getInt(0));
                    con.setFirstName(cur.getString(1));
                    con.setLastName(cur.getString(2));
                    con.setEmailAddy(cur.getString(3));
                    con.setCellNumber(cur.getString(4));
                    con.setHomeNumber(cur.getString(5));

                    conList.add(con);
                }while(cur.moveToNext());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            return conList;
        }
    }
       // return null;
    }

