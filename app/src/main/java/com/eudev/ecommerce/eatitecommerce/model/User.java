package com.eudev.ecommerce.eatitecommerce.model;

/**
 * Created by Morshed on 9/15/2017.
 */

public class User {
    private String name;
    private String pass;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.pass = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
