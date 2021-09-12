package com.calmen.pracgrader.models;

public class Practical {
    private String title;
    private double mark;

    public Practical(String inTitle, double inMark) {
        this.title = inTitle;
        this.mark = inMark;
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
}
