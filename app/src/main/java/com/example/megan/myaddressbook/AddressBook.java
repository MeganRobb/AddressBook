package com.example.megan.myaddressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.megan.myaddressbook.domain.myContacts;
import com.example.megan.myaddressbook.repository.DatasourceDAOImpl;

import java.util.ArrayList;
import java.util.List;


public class AddressBook extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button addButton;
    ListView contacts;
    ContactAdapter adapt;

    DatasourceDAOImpl dBase = new DatasourceDAOImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        addButton = (Button) findViewById(R.id.main_button);
        addButton.setOnClickListener(this);

        contacts = (ListView) findViewById(R.id.main_listview);

        List<myContacts> myContact = dBase.getAllContacts();

        adapt = new ContactAdapter(this, myContact);

        contacts.setAdapter(adapt);
        contacts.setOnItemClickListener(this);
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
    public void onClick (View vw) {
        //starts next activity
        Intent nextIntent = new Intent (this, AddActivity.class);
        startActivity(nextIntent);
        this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id ){
        //gets object from list view
        myContacts con = adapt.getItem(position);



        Toast.makeText(this, "index: "+ con.getId() , Toast.LENGTH_LONG).show();
        Intent contactIntent = new Intent(this, ContactActivity.class);
        contactIntent.putExtra("ContactId", con.getId());
        startActivity(contactIntent);
    }

    @Override
    public void onRestart(){
        //Refreshes list view
        super.onRestart();

        adapt.notifyDataSetChanged();
    }
}
