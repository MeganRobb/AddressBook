package com.example.megan.myaddressbook.domain;

/**
 * Created by MeganRobb on 8/22/2014.
 */
public class myContacts {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddy;
    private String cellNumber;
    private String homeNumber;

    public myContacts(){

    }

    public myContacts(String firstName,String lastName,String emailAddy,String cellNumber, String homeNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.lastName = emailAddy;
        this.lastName = cellNumber;
        this.lastName = homeNumber;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getEmailAddy(){return emailAddy;}

    public void setEmailAddy(String emailAddy) {this.emailAddy = emailAddy;}

    public String getCellNumber(){return cellNumber;}

    public void setCellNumber(String cellNumber) { this.cellNumber = cellNumber;}

    public String getHomeNumber(){return homeNumber;}

    public void setHomeNumber(String homeNumber) {this.homeNumber = homeNumber;}

}
