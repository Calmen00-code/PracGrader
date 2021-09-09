package com.calmen.pracgrader.shared;

import android.widget.EditText;

import com.calmen.pracgrader.models.User;

import java.util.ArrayList;

public class Validation {
    public static String checkValidAttributes(EditText nameTxt, EditText pinTxt,
                                              EditText confirmPinTxt) {
        if (!((nameTxt.getText().toString()).matches(".*\\d.*"))) {
            // ensures that the name is unique
            return "Username must consist of integer value!";
        } else if (!((pinTxt.getText().toString()).matches("[0-9]+"))) {
            return "PIN must consist ONLY integer!";
        } else {
            if (pinTxt.getText().toString().length() > 4) {
                return "PIN exceeded 4 digits!";
            } else {
                if (!pinTxt.getText().toString().
                        equals(confirmPinTxt.getText().toString())) {
                    return "PIN does not match!";
                } else {
                    return "";
                }
            }
        }
    }

    public static boolean checkDuplicateName(ArrayList<User> users, String name) {
        for (User user: users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
