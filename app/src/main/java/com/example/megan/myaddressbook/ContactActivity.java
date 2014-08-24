package com.example.megan.myaddressbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megan.myaddressbook.R;
import com.example.megan.myaddressbook.domain.myContacts;
import com.example.megan.myaddressbook.repository.DatasourceDAOImpl;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public class ContactActivity extends Activity {

    TextView firstName;
    TextView lastName;
    TextView email;
    TextView cellNo;
    TextView homeNo;

    DatasourceDAOImpl dBase = new DatasourceDAOImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        int ID = this.getIntent().getExtras().getInt("ContactId");

        myContacts con = dBase.getContact(ID);

        //Toast.makeText(this, "index: "+ ID , Toast.LENGTH_LONG).show();

        firstName = (TextView) findViewById(R.id.first_textview);
        lastName = (TextView) findViewById(R.id.last_textview);
        email = (TextView) findViewById(R.id.email_textview);
        cellNo = (TextView) findViewById(R.id.cell_textview);
        homeNo = (TextView) findViewById(R.id.home_textview);

        firstName.setText(con.getFirstName().toUpperCase());
        lastName.setText(con.getLastName().toUpperCase());
        email.setText(con.getEmailAddy().toUpperCase());
        cellNo.setText(con.getCellNumber());
        homeNo.setText(con.getHomeNumber());

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.address_book, menu);
        return true;
    }
}
