package com.calmen.pracgrader.models;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    // features associated to admin
    public static final int INSTRUCTOR_SETTINGS = 0;
    public static final int STUDENT_SETTINGS = 1;
    public static final int PRACTICAL_SETTINGS = 2;
    public static final int MARKING_SETTINGS = 3;
    public static final int VIEW_INSTRUCTOR_LIST = 4;
    public static final int VIEW_PRACTICAL_LIST = 5;
    public static final int VIEW_STUDENT_LIST = 6;
    public static final int SEARCH_STUDENT_LIST = 7;

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
