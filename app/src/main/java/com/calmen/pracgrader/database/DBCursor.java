package com.calmen.pracgrader.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.database.DBSchema.*;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Student;

public class DBCursor extends CursorWrapper {
    public DBCursor (Cursor cursor) { super(cursor); }

    public Admin getAdmin() {
        String name = getString(getColumnIndex(AdminTable.Cols.NAME));
        int pin = getInt(getColumnIndex(AdminTable.Cols.PIN));

        return new Admin(name, pin);
    }

    public Instructor getInstructor() {
        String name = getString(getColumnIndex(InstructorTable.Cols.NAME));
        String username = getString(getColumnIndex(InstructorTable.Cols.USERNAME));
        String email = getString(getColumnIndex(InstructorTable.Cols.EMAIL));
        int pin = getInt(getColumnIndex(InstructorTable.Cols.PIN));
        int countryFlag = getInt(getColumnIndex(InstructorTable.Cols.COUNTRY_FLAG));
        String countryName = getString(getColumnIndex(InstructorTable.Cols.COUNTRY));

        return new Instructor(name, username, pin, email, countryName, countryFlag);
    }

    public Student getStudent() {
        String name = getString(getColumnIndex(StudentTable.Cols.NAME));
        String username = getString(getColumnIndex(StudentTable.Cols.USERNAME));
        String email = getString(getColumnIndex(StudentTable.Cols.EMAIL));
        String labUnit = getString(getColumnIndex(StudentTable.Cols.LAB_UNIT));
        double mark = Math.round(getDouble(getColumnIndex(StudentTable.Cols.MARK))*100)/100;
        int pin = getInt(getColumnIndex(StudentTable.Cols.PIN));
        int countryFlag = getInt(getColumnIndex(StudentTable.Cols.COUNTRY_FLAG));
        String countryName = getString(getColumnIndex(StudentTable.Cols.COUNTRY));

        return new Student(name, username, pin, email, labUnit, mark, countryName, countryFlag);
    }
}
