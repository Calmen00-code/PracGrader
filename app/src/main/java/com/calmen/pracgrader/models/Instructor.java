package com.calmen.pracgrader.models;

public class Instructor extends User {
    // features associated to instructor
    public static final int STUDENT_SETTINGS = 0;
    public static final int MARKING_SETTINGS = 1;
    public static final int VIEW_STUDENT_LIST = 2;
    public static final int SEARCH_STUDENT_LIST = 3;

    private String name;
    private String email;
    private String countryName;
    private int countryFlag;

    public Instructor(String inName, String inUsername, int inPin,
                      String inEmail, String inCountryName, int inCountryFlag) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
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
