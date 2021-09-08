package com.calmen.pracgrader.users;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int pin;

    public User(String inName, int inPin) {
        this.name = inName;
        this.pin = inPin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
