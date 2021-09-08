package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.users.Admin;
import com.calmen.pracgrader.users.AdminList;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * Run the registration if the app is being run on the first time
 */
public class Registration extends AppCompatActivity {
    private EditText nameTxt, pinTxt, confirmPinTxt;
    private Button registerBtn;
    private AdminList adminList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        adminList = new AdminList();
        adminList.load(this);

        nameTxt = findViewById(R.id.usernameTxt);
        pinTxt = findViewById(R.id.pinTxt);
        confirmPinTxt = findViewById(R.id.pinTxt2);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
                msg = checkEmptyAttributes();
                // display error message when either name, pin, confirmPin is empty
                if (msg.equals("")) {
                    msg = checkValidAttributes();
                    // display error message when there is invalid input
                    if (msg.equals("")) {
                        if (checkDuplicateName(adminList.getAdmins(),
                            nameTxt.getText().toString())) {
                            Toast.makeText(Registration.this, "Name has been taken!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registration.this, "Account created!",
                                    Toast.LENGTH_SHORT).show();
                            adminList.addAdmin(new Admin(nameTxt.getText().toString(),
                                    Integer.parseInt(pinTxt.getText().toString())));
                            Intent intent = new Intent(Registration.this, Login.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(Registration.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Registration.this, msg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String checkEmptyAttributes() {
        if (nameTxt.getText().toString().equals("")) {
            return "Username is empty!";
        } else if (pinTxt.getText().toString().equals("")) {
            return "PIN is empty!";
        } else if (confirmPinTxt.getText().toString().equals("")) {
            return "Re-enter PIN is empty!";
        } else {
            return "";
        }
    }

    public String checkValidAttributes() {
        if (!((nameTxt.getText().toString()).matches(".*\\d.*"))) {
            // ensures that the name is unique
            return "Name must consist of integer value!";
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

    public boolean checkDuplicateName(ArrayList<Admin> admins, String name) {
        for (Admin admin: admins) {
            if (admin.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}