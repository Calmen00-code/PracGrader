package com.calmen.pracgrader.users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String name, int pin) {
        super(name, pin);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
