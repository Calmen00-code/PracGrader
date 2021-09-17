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
    private static int nextId = 0;

    public Student(String inName, String inUsername, int inPin, String inEmail,
                   String inCountryName, int inCountryFlag, ) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
        this.countryName = inCountryName;
        this.countryFlag = inCountryFlag;
        if (nextId + 2)
        this.uniqueID = nextId + 2;
        nextId = this.uniqueID;
        this.studentPracticalList = new StudentPracticalList(this.uniqueID);
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
