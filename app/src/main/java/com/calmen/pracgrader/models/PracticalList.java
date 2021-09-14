package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

public class PracticalList implements Serializable {
    public static final int DEFAULT_PRACTICAL_LIST_ID = -1;

    DBModel dbModel;
    private ArrayList<Practical> practicals;

    public PracticalList() {
        practicals = new ArrayList<>();
    }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        practicals = dbModel.getAllPracticals();
    }

    public void add(Practical practical) {
        practicals.add(practical);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addPractical(practical);
        }
    }

    public void remove(Practical practical) {
        practicals.remove(practical);
        dbModel.removePractical(practical);
    }

    public void edit(Practical oldPrac, Practical newPrac) {
        System.out.println("oldPractical title: " + oldPrac.getTitle());
        if (practicals.isEmpty()) {
            System.out.println("Student is empty");
        } else {
            System.out.println("Student is NOT EMPTY");
        }
        for (Practical practical: practicals) {
            if (practical.getTitle().equals(oldPrac.getTitle()) &&
                    practical.getUniqueRefID() == DEFAULT_PRACTICAL_LIST_ID) {
                practical = newPrac;
                dbModel.updatePractical(practical, oldPrac.getTitle());
            }
        }
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
