package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentPracticalList implements Serializable {
    DBModel dbModel;
    private ArrayList<Practical> practicals;
    private int uniqueID;

    public StudentPracticalList(int inUniqueID) {
        this.practicals = new ArrayList<>();
        this.uniqueID = inUniqueID;
    }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
    }

    public void add(Practical practical) {
        practicals.add(practical);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addPractical(practical);
        }
    }

    public ArrayList<Practical> getStudentPracticals(int findID) {
        practicals = dbModel.getAllStudentPracticals(findID);
        return practicals;
    }

    public int getUniqueID() {
        return uniqueID;
    }
}
