package com.calmen.pracgrader.models;

public class Practical {
    private String title;
    private double mark;
    private int uniqueRefID;

    public Practical(String inTitle, double inMark, int inUniqueRefID) {
        this.title = inTitle;
        this.mark = inMark;
        this.uniqueRefID = inUniqueRefID;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMark() {
        return mark;
    }

    public String getTitle() {
        return title;
    }

    public int getUniqueRefID() {
        return uniqueRefID;
    }
}
