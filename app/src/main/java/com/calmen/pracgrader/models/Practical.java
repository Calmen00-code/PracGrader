package com.calmen.pracgrader.models;

public class Practical {
    private String title;
    private String desc;
    private double studentMark;
    private double mark;
    private int uniqueRefID;

    public Practical(String inTitle, String inDesc, double inMark, double inStudentMark, int inUniqueRefID) {
        this.title = inTitle;
        this.desc = inDesc;
        this.mark = inMark;
        this.studentMark = inStudentMark;
        this.uniqueRefID = inUniqueRefID;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getStudentMark() {
        return studentMark;
    }

    public String getDesc() {
        return desc;
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
