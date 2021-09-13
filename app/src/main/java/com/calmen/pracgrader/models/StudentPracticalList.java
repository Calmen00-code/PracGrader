package com.calmen.pracgrader.models;

import java.util.ArrayList;

public class StudentPracticalList {
    private ArrayList<Practical> practicals;
    private int uniqueID;

    public StudentPracticalList(int inUniqueID) {
        this.practicals = new ArrayList<>();
        this.uniqueID = inUniqueID;
    }

    public ArrayList<Practical> getPracticals() {
        return practicals;
    }

    public int getUniqueID() {
        return uniqueID;
    }
}
