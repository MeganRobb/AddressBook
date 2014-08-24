package com.example.megan.myaddressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.megan.myaddressbook.R;
import com.example.megan.myaddressbook.domain.myContacts;

import java.util.List;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public class ContactAdapter extends ArrayAdapter<myContacts> {

    List<myContacts> myCons;

    public ContactAdapter(Context context, List<myContacts> con){
        super(context, R.layout.row_detail, con);
        myCons = con;

    }

    public myContacts getItem(int position){

        return myCons.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder  holder;

        myContacts con = (myContacts)getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_detail, parent, false);
        }

        TextView lastName = (TextView) convertView.findViewById(R.id.text_last);
        TextView cell = (TextView) convertView.findViewById(R.id.text_cell);

        lastName.setText(con.getLastName().toUpperCase());
        cell.setText(con.getCellNumber());

        return convertView;
    }

    private static class ViewHolder{
        public TextView lastTextView;
        public TextView cellTextView;
    }
}
