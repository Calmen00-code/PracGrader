package com.calmen.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Run the registration if the app is being run on the first time
 */
public class Registration extends AppCompatActivity {
    private EditText nameTxt, pinTxt, confirmPinTxt;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

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
                if (msg = "") {
                    msg = checkValidAttributes();
                    if (msg == "") {
                        Toast.makeText(Registration.this, "Account created!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, Login.class);
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
        if (nameTxt.getText().toString() == "") {
            return "Username is empty!";
        } else if (pinTxt.getText().toString() == "") {
            return "PIN is empty!";
        } else if (confirmPinTxt.getText().toString() == "") {
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
            if (!pinTxt.getText().toString().
                    equals(confirmPinTxt.getText().toString())) {
                return "PIN does not match!";
            } else {
                return "";
            }
        }
    }
}