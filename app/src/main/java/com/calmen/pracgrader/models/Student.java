package com.calmen.pracgrader.models;

public class Student extends User {

    private String name;
    private String email;
    private String countryName;
    private String labUnit;
    private double mark;
    private int countryFlag;

    public Student(String inName, String inUsername, int inPin, String inEmail,
                   String inLabUnit, double inMark, String inCountryName, int inCountryFlag) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
        this.labUnit = inLabUnit;
        this.mark = inMark;
        this.countryName = inCountryName;
        this.countryFlag = inCountryFlag;
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
}
