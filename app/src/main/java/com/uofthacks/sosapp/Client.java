package com.uofthacks.sosapp;

import com.parse.ParseObject;

/**
 * Created by Howard on 1/23/2016.
 */
public class Client extends ParseObject{
    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        put("email", email);
    }

    public String getlName() {
        return getString("lName");
    }

    public void setlName(String lName) {
        put("lName", lName);
    }

    public String getfName() {
        return getString("fName");
    }

    public void setfName(String fName) {
        put("fName", fName);
    }

    public String getPhoneNumber() {
        return getString("phoneNumber");
    }

    public void setPhoneNumber(String phoneNumber) {
        put("phoneNumber", phoneNumber);
    }

    public Client() {
        //default constructor
    }

}
