package com.calmen.pracgrader.users;

public class Admin extends User {
    public Admin(String inName) {
        super(inName);
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
