package com.calmen.pracgrader.models;

public class Instructor extends User {
    public static final String EMAIL_REGEX = "^(.+)@(.+)$";

    // features associated to instructor
    public static final int STUDENT_SETTINGS = 0;

    private String username;
    private String email;

    public Instructor(String inName, String inUsername,
                      int inPin, String inEmail) {
        super(inName, inPin);
        this.username = inUsername;
        this.email = inEmail;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getPin() {
        return super.getPin();
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
