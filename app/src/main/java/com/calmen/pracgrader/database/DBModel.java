package com.calmen.pracgrader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.calmen.pracgrader.database.DBSchema.*;

import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;

import java.io.Serializable;
import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase db;
    Context activityContext;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
        this.activityContext = context;
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

    /***
     * @param oldUsername because it is still the unique key for DB before update
     */
    public void updateInstructor(Instructor instructor, String oldUsername) {
        String[] whereVal = {String.valueOf(oldUsername)};
        ContentValues cv = new ContentValues();
        cv.put(InstructorTable.Cols.NAME, instructor.getName());
        cv.put(InstructorTable.Cols.USERNAME, instructor.getUsername());
        cv.put(InstructorTable.Cols.PIN, instructor.getPin());
        cv.put(InstructorTable.Cols.EMAIL, instructor.getEmail());
        cv.put(InstructorTable.Cols.COUNTRY, instructor.getCountryName());
        cv.put(InstructorTable.Cols.COUNTRY_FLAG, instructor.getCountryFlag());

        int updated = db.update(InstructorTable.NAME, cv, InstructorTable.Cols.USERNAME + " =?", whereVal);
        if (updated > 0 ) {
            System.out.println("Instructor updated");
        } else {
            System.out.println("Instructor NOT UPDATED");
        }
    }

    public void addStudent(Student student) {
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.NAME, student.getName());
        cv.put(StudentTable.Cols.USERNAME, student.getUsername());
        cv.put(StudentTable.Cols.PIN, student.getPin());
        cv.put(StudentTable.Cols.EMAIL, student.getEmail());
        cv.put(StudentTable.Cols.REF_ID, student.getUniqueID());
        cv.put(StudentTable.Cols.COUNTRY, student.getCountryName());
        cv.put(StudentTable.Cols.COUNTRY_FLAG, student.getCountryFlag());
        cv.put(StudentTable.Cols.IS_INSTRUCTOR_REG, student.isRegByInstructor());
        cv.put(StudentTable.Cols.IMAGE, student.getStudentImg());
        db.insert(StudentTable.NAME, null, cv);
    }

    public void removeStudent(Student student) {
        // username is unique
        String[] whereVal = {String.valueOf(student.getUsername())};
        for (String val : whereVal) {
            System.out.println("val: " + val);
        }
        int deleted = db.delete(StudentTable.NAME, StudentTable.Cols.USERNAME + " =?", whereVal);
        if (deleted > 0 ) {
            System.out.println("Student deleted");
        } else {
            System.out.println("Student NOT DELETED");
        }
    }

    /***
     * @param oldUsername because it is still the unique key for DB before update
     */
    public void updateStudent(Student student, String oldUsername) {
        String[] whereVal = {String.valueOf(oldUsername)};
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.Cols.NAME, student.getName());
        cv.put(StudentTable.Cols.USERNAME, student.getUsername());
        cv.put(StudentTable.Cols.PIN, student.getPin());
        cv.put(StudentTable.Cols.EMAIL, student.getEmail());
        cv.put(StudentTable.Cols.REF_ID, student.getUniqueID());
        cv.put(StudentTable.Cols.COUNTRY, student.getCountryName());
        cv.put(StudentTable.Cols.COUNTRY_FLAG, student.getCountryFlag());
        cv.put(StudentTable.Cols.IMAGE, student.getStudentImg());

        int updated = db.update(StudentTable.NAME, cv, StudentTable.Cols.USERNAME + " =?", whereVal);
        if (updated > 0 ) {
            System.out.println("Student updated");
        } else {
            System.out.println("Student NOT UPDATED");
        }
    }

    public void addPractical(Practical practical) {
        ContentValues cv = new ContentValues();
        cv.put(PracticalTable.Cols.TITLE, practical.getTitle());
        cv.put(PracticalTable.Cols.DESC, practical.getDesc());
        cv.put(PracticalTable.Cols.MARK, practical.getMark());
        cv.put(PracticalTable.Cols.STUDENT_MARK, practical.getStudentMark());
        cv.put(PracticalTable.Cols.REF_ID, practical.getUniqueRefID());

        long added = db.insert(PracticalTable.NAME, null, cv);
        if (added > 0) {
            System.out.println("Practical added");
        } else {
            System.out.println("Practical NOT ADDED");
        }
    }

    public void removePractical(Practical practical) {
        // title is unique
        String[] whereVal = {String.valueOf(practical.getTitle())};
        for (String val : whereVal) {
            System.out.println("val: " + val);
        }
        int deleted = db.delete(PracticalTable.NAME, PracticalTable.Cols.TITLE + " =?", whereVal);
        if (deleted > 0 ) {
            System.out.println("Practical deleted");
        } else {
            System.out.println("Practical NOT DELETED");
        }
    }

    /***
     * @param oldPracTitle because it is still the unique key for DB before update
     */
    public void updatePractical(Practical practical, String oldPracTitle) {
        String[] whereVal = {String.valueOf(oldPracTitle)};
        ContentValues cv = new ContentValues();
        cv.put(PracticalTable.Cols.TITLE, practical.getTitle());
        cv.put(PracticalTable.Cols.DESC, practical.getDesc());
        cv.put(PracticalTable.Cols.MARK, practical.getMark());
        cv.put(PracticalTable.Cols.STUDENT_MARK, practical.getStudentMark());
        cv.put(PracticalTable.Cols.REF_ID, practical.getUniqueRefID());

        int updated = db.update(PracticalTable.NAME, cv, PracticalTable.Cols.TITLE + " =?", whereVal);
        if (updated > 0 ) {
            System.out.println("Practical updated");
        } else {
            System.out.println("Practical NOT UPDATED");
        }
    }

    /***
     * @param uniqueID to search for practical that belongs to the student
     */
    public void updatePracticalByID(Practical practical, int uniqueID, String title) {
        String[] whereVal = {String.valueOf(uniqueID), String.valueOf(title)};
        ContentValues cv = new ContentValues();
        cv.put(PracticalTable.Cols.TITLE, practical.getTitle());
        cv.put(PracticalTable.Cols.DESC, practical.getDesc());
        cv.put(PracticalTable.Cols.MARK, practical.getMark());
        cv.put(PracticalTable.Cols.STUDENT_MARK, practical.getStudentMark());
        cv.put(PracticalTable.Cols.REF_ID, practical.getUniqueRefID());

        int updated = db.update(PracticalTable.NAME, cv, PracticalTable.Cols.REF_ID +
                " =? AND " + PracticalTable.Cols.TITLE + " =?", whereVal);
        if (updated > 0 ) {
            System.out.println("Practical updated");
        } else {
            System.out.println("Practical NOT UPDATED");
        }
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

    public ArrayList<User> getAllStudents() {
        ArrayList<User> students = new ArrayList<>();
        Cursor cursor = db.query(StudentTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                students.add(dbCursor.getStudent(activityContext));
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return students;
    }

    public ArrayList<Practical> getAllPracticals() {
        ArrayList<Practical> practicals = new ArrayList<>();
        Cursor cursor = db.query(PracticalTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                if (dbCursor.getPractical().getUniqueRefID() ==
                        PracticalList.DEFAULT_PRACTICAL_LIST_ID) {
                    practicals.add(dbCursor.getPractical());
                }
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return practicals;
    }

    public ArrayList<Practical> getAllStudentPracticals(int findID) {
        ArrayList<Practical> practicals = new ArrayList<>();
        Cursor cursor = db.query(PracticalTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                if (dbCursor.getPractical().getUniqueRefID() == findID) {
                    practicals.add(dbCursor.getPractical());
                }
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }

        System.out.println("in DB");
        System.out.println("ID: " + findID);
        for (Practical practical: practicals) {
            System.out.println(practical.getTitle());
        }
        return practicals;
    }
}
