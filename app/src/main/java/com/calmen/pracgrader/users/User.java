package com.calmen.pracgrader.users;

public class User {
    private String name;

    public User(String inName) {
        this.name = inName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
