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

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addInstructor(instructor);
        }
    }

    public ArrayList<User> getInstructors() {
        return this.instructors;
    }

    public boolean isEmpty() {
        if (instructors.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(String username) {
        if (isEmpty()) {
            return false;
        } else {
            for (User user : instructors) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        if (!isEmpty()) {
            for (User user : instructors) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }
}
