package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.util.ArrayList;

public class InstructorList extends UserList {
    DBModel dbModel;

    public InstructorList() { super(); }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        super.setUsers(dbModel.getAllInstructors());
    }

    public void add(Instructor instructor) {
        super.getUsers().add(instructor);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addInstructor(instructor);
        }
    }

    public void remove(Instructor instructor) {
        super.getUsers().remove(instructor);
        dbModel.removeInstructor(instructor);
    }

    public ArrayList<User> getInstructors() {
        return super.getUsers();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean isExist(String username) {
        return super.isExist(username);
    }
}
