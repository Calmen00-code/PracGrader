package com.calmen.pracgrader.ui;

import static com.calmen.pracgrader.shared.Validation.checkDuplicateName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.AdminList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.Validation;

import java.util.ArrayList;

/***
 * Run the registration if the app is being run on the first time
 */
public class AdminRegistration extends AppCompatActivity implements UserRegistration {
    private EditText nameTxt, pinTxt, confirmPinTxt;
    private Button registerBtn;
    private AdminList adminList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_registration_page);

        adminList = new AdminList();
        adminList.load(this);

        nameTxt = findViewById(R.id.instructorEmailTxt);
        pinTxt = findViewById(R.id.pinInstructorTxt2);
        confirmPinTxt = findViewById(R.id.pinInstructorTxt);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
                msg = checkEmptyAttributes();
                // display error message when either name, pin, confirmPin is empty
                if (msg.equals("")) {
                    msg = Validation.checkValidAttributes(nameTxt, pinTxt, confirmPinTxt);
                    // display error message when there is invalid input
                    if (msg.equals("")) {
                        if (Validation.checkDuplicateName(adminList.getAdmins(),
                            nameTxt.getText().toString())) {
                            Toast.makeText(AdminRegistration.this, "Name has been taken!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminRegistration.this, "Account created!",
                                    Toast.LENGTH_SHORT).show();
                            adminList.addAdmin(new Admin(nameTxt.getText().toString(),
                                    Integer.parseInt(pinTxt.getText().toString())));
                            Intent intent = new Intent(AdminRegistration.this, Login.class);
                            intent.putExtra("Roles", Login.ADMIN);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(AdminRegistration.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminRegistration.this, msg,
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

    @Override
    public EditText getName() {
        return nameTxt;
    }

    @Override
    public EditText getUsername() {
        return nameTxt;
    }

    @Override
    public EditText getEmail() {
        return null;
    }

    @Override
    public EditText getPin() {
        return pinTxt;
    }

    @Override
    public EditText getPinTwo() {
        return confirmPinTxt;
    }
}