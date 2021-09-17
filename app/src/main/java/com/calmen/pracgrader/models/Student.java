package com.calmen.pracgrader.models;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * Has-A Relationship with PracticalList
 */
public class Student extends User {

    private String name;
    private String email;
    private String countryName;
    private StudentPracticalList studentPracticalList;
    private int countryFlag;
    private int uniqueID;
    Context context;

    public Student(String inName, String inUsername, int inPin, String inEmail,
                   String inCountryName, int inCountryFlag, Context context) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
        this.countryName = inCountryName;
        this.countryFlag = inCountryFlag;
        // retrieve last ID of student to define new ID
        StudentList studentList = new StudentList();
        studentList.load(context);
        ArrayList<User> students = studentList.getStudents();
        Student lastStudent = (Student) students.get(students.size() - 1);
        int lastID = lastStudent.getUniqueID();

        this.uniqueID = lastID + 2;
        this.studentPracticalList = new StudentPracticalList(uniqueID);
    }

    public Student(String inName, String inUsername, int inPin, String inEmail,
                   String inCountryName, int inCountryFlag, int inUniqueID) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
        this.countryName = inCountryName;
        this.countryFlag = inCountryFlag;
        this.uniqueID = inUniqueID;
        this.studentPracticalList = new StudentPracticalList(this.uniqueID);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPin(int pin) {
        super.setPin(pin);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public int getPin() {
        return super.getPin();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public StudentPracticalList getStudentPracticalList() {
        return studentPracticalList;
    }

    public Practical getStudentPracticalByTitle(ArrayList<Practical> findPracticals, String title) {
        for (Practical practical: findPracticals) {
            if (practical.getTitle().equals(title)) {
                return practical;
            }
        }
        return null;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public double getTotalMark(Context context) {
        double totalMark = 0.0;
        this.studentPracticalList.load(context);
        for (Practical practical: this.studentPracticalList
                .getStudentPracticals(this.uniqueID)) {
            totalMark += practical.getStudentMark();
        }
        return totalMark;
    }
}
