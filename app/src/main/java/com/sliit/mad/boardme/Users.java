package com.sliit.mad.boardme;

public class Users {
      String type,firstName, lastName, email,telephone,address;

    public Users(){

    }

    public Users( String firstName, String lastName, String telephone) {

        this.firstName = firstName;
        this.lastName = lastName;

        this.telephone = telephone;
    }

    public Users(String type, String firstName, String lastName, String email, String telephone, String address) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }
}
