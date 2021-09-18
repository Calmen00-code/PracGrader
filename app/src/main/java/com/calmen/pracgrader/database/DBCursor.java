package com.calmen.pracgrader.database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;

import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.database.DBSchema.*;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;

import java.util.ArrayList;

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

    /**
     * @param context is needed to load DB and retrieve the PracticalList
     */
    public Student getStudent(Context context) {
        String name = getString(getColumnIndex(StudentTable.Cols.NAME));
        String username = getString(getColumnIndex(StudentTable.Cols.USERNAME));
        String email = getString(getColumnIndex(StudentTable.Cols.EMAIL));
        int uniqueRefID = getInt(getColumnIndex(StudentTable.Cols.REF_ID));
        int pin = getInt(getColumnIndex(StudentTable.Cols.PIN));
        int countryFlag = getInt(getColumnIndex(StudentTable.Cols.COUNTRY_FLAG));
        String countryName = getString(getColumnIndex(StudentTable.Cols.COUNTRY));
        int isRegByInstructor = getInt(getColumnIndex(StudentTable.Cols.IS_INSTRUCTOR_REG));
        int studentImage = getInt(getColumnIndex(StudentTable.Cols.IMAGE));

        PracticalList existingPracticalList = new PracticalList();
        existingPracticalList.load(context);
        ArrayList<Practical> existingPracticals = existingPracticalList.getPracticals();

        PracticalList practicalList = new PracticalList();
        for (Practical practical: existingPracticals) {
            if (practical.getUniqueRefID() == uniqueRefID) {
                practicalList.add(practical);
            }
        }

        return new Student(name, username, pin, email, countryName, countryFlag, studentImage, isRegByInstructor, uniqueRefID);
    }

    public Practical getPractical() {
        String title = getString(getColumnIndex(PracticalTable.Cols.TITLE));
        String desc = getString(getColumnIndex(PracticalTable.Cols.DESC));
        double mark = Math.round(getDouble(getColumnIndex(PracticalTable.Cols.MARK))*100)/100;
        double studentMark = Math.round(getDouble(getColumnIndex(PracticalTable.Cols.STUDENT_MARK))*100)/100;
        int uniqueRefID = getInt(getColumnIndex(PracticalTable.Cols.REF_ID));

        return new Practical(title, desc, mark, studentMark, uniqueRefID);
    }
}
