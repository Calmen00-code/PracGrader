package com.calmen.pracgrader.users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public static final int INSTRUCTOR_SETTINGS = 0;
    public static final int PRACTICAL_SETTINGS = 1;
    public static final int MARKING_SETTINGS = 2;
    public static final int VIEW_INSTRUCTOR_LIST = 3;
    public static final int VIEW_PRACTICAL_LIST = 4;
    public static final int VIEW_STUDENT_LIST = 5;
    public static final int SEARCH_STUDENT_LIST = 6;

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
