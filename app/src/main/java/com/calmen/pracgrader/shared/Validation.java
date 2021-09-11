package com.calmen.pracgrader.shared;

import android.widget.EditText;

import com.calmen.pracgrader.models.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String VALID = "VALID";

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

    public static String checkValidEmail(String email) {
        Pattern pattern = Pattern.compile(User.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        // check email if username and password is valid
        if (matcher.matches()) {
            return VALID;
        } else {
            return "Email is invalid!";
        }
    }

    public static String checkValidPIN(String pin) {
        if (!pin.matches("[0-9]+")) {
            return "PIN must consist ONLY integer!";
        } else {
            if (pin.length() > 4) {
                return "PIN exceeded 4 digits!";
            } else {
                return VALID;
            }
        }
    }

    public static boolean checkDuplicateName(ArrayList<User> users, String name) {
        for (User user: users) {
            if (user.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
