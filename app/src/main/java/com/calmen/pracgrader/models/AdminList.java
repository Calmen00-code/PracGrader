package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * Communicate with DB to modify Admin Table
 */
public class AdminList implements Serializable {
    ArrayList<User> admins;
    DBModel dbModel;

    public AdminList() {
        admins = new ArrayList<>();
    }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        admins = dbModel.getAllAdmins();
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addAdmin(admin);
        }
    }

    public ArrayList<User> getAdmins() {
        return this.admins;
    }

    public boolean isEmpty() {
        if (admins.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
