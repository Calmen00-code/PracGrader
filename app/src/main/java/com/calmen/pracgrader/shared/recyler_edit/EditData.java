package com.calmen.pracgrader.shared.recyler_edit;

public class EditData {
    private String editTitle;
    private String editVal;

    public EditData(String inEditTitle, String inEditVal) {
        this.editTitle = inEditTitle;
        this.editVal = inEditVal;
    }

    public String getEditTitle() {
        return editTitle;
    }

    public String getEditVal() {
        return editVal;
    }
}
