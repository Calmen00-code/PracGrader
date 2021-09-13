package com.calmen.pracgrader.models;

/***
 * Has-A Relationship with PracticalList
 */
public class Student extends User {

    private String name;
    private String email;
    private String countryName;
    private PracticalList practicalList;
    private int countryFlag;
    private int uniqueID;
    private static int nextId = 0;

    public Student(String inName, String inUsername, int inPin, String inEmail,
                   PracticalList inPracticalList, String inCountryName, int inCountryFlag) {
        super(inUsername, inPin);
        this.name = inName;
        this.email = inEmail;
        this.practicalList = inPracticalList;
        this.countryName = inCountryName;
        this.countryFlag = inCountryFlag;
        this.uniqueID = nextId + 2;
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

    public PracticalList getPracticalList() {
        return practicalList;
    }

    public int getUniqueID() {
        return uniqueID;
    }
}
