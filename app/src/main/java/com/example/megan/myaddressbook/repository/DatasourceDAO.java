package com.example.megan.myaddressbook.repository;

import com.example.megan.myaddressbook.domain.myContacts;

import java.util.List;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public interface DatasourceDAO {

    public void addContact(myContacts contact);
    public myContacts getContact (int id);
    public List<myContacts> getAllContacts();
}
