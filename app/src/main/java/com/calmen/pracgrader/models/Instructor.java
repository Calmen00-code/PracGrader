package com.calmen.pracgrader.models;

public class Instructor extends User {
    // features associated to instructor
    public static final int

    private String username;
    private String email;

    public Instructor(String inName, String inUsername,
                      int inPin, String inEmail) {
        super(inName, inPin);
        this.username = inUsername;
        this.email = inEmail;
    }
}
