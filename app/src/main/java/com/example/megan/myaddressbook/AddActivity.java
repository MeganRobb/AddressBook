package com.example.megan.myaddressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.megan.myaddressbook.domain.myContacts;
import com.example.megan.myaddressbook.repository.DatasourceDAOImpl;

import java.util.List;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public class AddActivity extends Activity implements View.OnClickListener {

    Button addButton;
    EditText firstTxt;
    EditText lastTxt;
    EditText emailTxt;
    EditText cellTxt;
    EditText homeTxt;

    String fname;
    String lname;
    String email;
    String cell;
    String home;

    DatasourceDAOImpl dBase = new DatasourceDAOImpl(this);

    @Override
    protected  void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add);

        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        firstTxt = (EditText)findViewById(R.id.first_edittext);
        lastTxt = (EditText)findViewById(R.id.last_edittext);
        emailTxt = (EditText)findViewById(R.id.email_edittext);
        cellTxt = (EditText)findViewById(R.id.cell_edittext);
        homeTxt = (EditText)findViewById(R.id.home_edittext);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.address_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Log.d("CREATE:  ", dbs.CREATE_CONTACTS_TABLE);
        //Add to db
        fname = firstTxt.getText().toString();
        lname = lastTxt.getText().toString();
        email = emailTxt.getText().toString();
        cell = cellTxt.getText().toString();
        home = homeTxt.getText().toString();

        Log.d("Inserting", "Inserting");
        dBase.addContact(new myContacts(fname, lname, email, cell, home));
        List<myContacts> contacts = dBase.getAllContacts();

        for (myContacts cn : contacts) {
            String log = "ID: "+cn.getId()+", Name: " + cn.getFirstName() + ", Number: " + cn.getCellNumber();
            Log.d("log   ", log);
        }

        firstTxt.setText("");
        lastTxt.setText("");
        emailTxt.setText("");
        cellTxt.setText("");
        homeTxt.setText("");

        Toast.makeText(this, "Contact was Added Successfully!", Toast.LENGTH_LONG).show();

        Intent mainIntent = new Intent(this, AddressBook.class);
        startActivity(mainIntent);
        this.finish();
    }

}
