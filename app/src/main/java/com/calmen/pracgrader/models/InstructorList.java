package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.util.ArrayList;

public class InstructorList {
    ArrayList<User> instructors;
    DBModel dbModel;

    public InstructorList() { instructors = new ArrayList<>(); }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        instructors = dbModel.getAllInstructors();
    }
}
