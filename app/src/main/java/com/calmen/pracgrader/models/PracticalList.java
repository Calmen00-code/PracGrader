package com.calmen.pracgrader.models;

import com.calmen.pracgrader.database.DBModel;

import java.util.ArrayList;

public class PracticalList {
    DBModel dbModel;
    private ArrayList<Practical> practicals;

    public PracticalList() {
        practicals = new ArrayList<>();
    }

    public ArrayList<Practical> getPracticals() {
        return practicals;
    }

    public void setPracticals(ArrayList<Practical> practicals) {
        this.practicals = practicals;
    }

    public boolean isEmpty() {
        if (practicals.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(String pracTitle) {
        if (isEmpty()) {
            return false;
        } else {
            for (Practical practical : practicals) {
                if (practical.getTitle().equals(pracTitle)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Practical getPracByTitle(String pracTitle) {
        if (!isEmpty()) {
            for (Practical practical : practicals) {
                if (practical.getTitle().equals(pracTitle)) {
                    return practical;
                }
            }
        }
        return null;
    }
}
