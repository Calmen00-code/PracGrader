package com.calmen.pracgrader.models;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private int pin;

    public User(String inUsername, int inPin) {
        this.username = inUsername;
        this.pin = inPin;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
