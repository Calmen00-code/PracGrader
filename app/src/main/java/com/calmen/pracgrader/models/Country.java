package com.calmen.pracgrader.models;

public class Country {
    private String name;
    private int flagID;

    public Country(String inName, int inFlagID) {
        this.name = inName;
        this.flagID = inFlagID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag(int flag) {
        this.flagID = flag;
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flagID;
    }
}
