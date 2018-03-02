package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 28.02.2018.
 */

public class User {
    private String email;
    private String password;

    public User() {
    }

    public User(String Pemail, String Ppassword) {
        this.email = Pemail;
        this.password = Ppassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Pemail) {
        this.email = Pemail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Ppassword) {
        this.password = Ppassword;
    }
}

