package com.calmen.pracgrader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.calmen.pracgrader.database.DBSchema.*;

import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.User;

import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase db;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addAdmin(Admin admin) {
        ContentValues cv = new ContentValues();
        cv.put(AdminTable.Cols.NAME, admin.getUsername());
        cv.put(AdminTable.Cols.PIN, admin.getPin());
        db.insert(AdminTable.NAME, null, cv);
    }

    public void addInstructor(Instructor instructor) {
        ContentValues cv = new ContentValues();
        cv.put(InstructorTable.Cols.NAME, instructor.getName());
        cv.put(InstructorTable.Cols.USERNAME, instructor.getUsername());
        cv.put(InstructorTable.Cols.PIN, instructor.getPin());
        cv.put(InstructorTable.Cols.EMAIL, instructor.getEmail());
        cv.put(InstructorTable.Cols.COUNTRY, instructor.getCountryName());
        cv.put(InstructorTable.Cols.COUNTRY_FLAG, instructor.getCountryFlag());
        db.insert(InstructorTable.NAME, null, cv);
    }

    public void removeInstructor(Instructor instructor) {
        String[] whereVal = {String.valueOf(instructor.getUsername())};
        for (String val : whereVal) {
            System.out.println("val: " + val);
        }
        int deleted = db.delete(InstructorTable.NAME, InstructorTable.Cols.USERNAME + " =?", whereVal);
        if (deleted > 0 ) {
            System.out.println("Instructor deleted");
        } else {
            System.out.println("Instructor NOT DELETED");
        }
    }

    public void updateInstructor(Instructor instructor) {
        String[] whereVal = {String.valueOf(instructor.getUsername())};
        ContentValues cv = new ContentValues();
        cv.put(InstructorTable.Cols.NAME, instructor.getName());
        cv.put(InstructorTable.Cols.USERNAME, instructor.getUsername());
        cv.put(InstructorTable.Cols.PIN, instructor.getPin());
        cv.put(InstructorTable.Cols.EMAIL, instructor.getEmail());
        cv.put(InstructorTable.Cols.COUNTRY, instructor.getCountryName());
        cv.put(InstructorTable.Cols.COUNTRY_FLAG, instructor.getCountryFlag());

        db.update(InstructorTable.NAME, cv, InstructorTable.Cols.USERNAME + " =?", whereVal);
    }

    public ArrayList<User> getAllAdmins() {
        ArrayList<User> admins = new ArrayList<>();
        Cursor cursor = db.query(AdminTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                admins.add(dbCursor.getAdmin());
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return admins;
    }

    public ArrayList<User> getAllInstructors() {
        ArrayList<User> instructors = new ArrayList<>();
        Cursor cursor = db.query(InstructorTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                instructors.add(dbCursor.getInstructor());
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return instructors;
    }
}
