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
import com.calmen.pracgrader.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements Serializable {
    public static final String ADMIN = "ADMIN";
    public static final String INSTRUCTOR = "INSTRUCTOR";
    public static final String STUDENT = "STUDENT";

    private EditText userNameTxt, pinTxt;
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        String roles = (String) getIntent().getSerializableExtra("Roles");

        userNameTxt = findViewById(R.id.userLoginTxt);
        pinTxt = findViewById(R.id.pinLoginTxt);
        signInBtn = findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = checkEmptyAttributes();
                if (msg.equals("")) {
                    userLoginHandler(roles);
                    // check if user exist
                        // if user exist, check the pin
                } else {
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String checkEmptyAttributes() {
        if (userNameTxt.getText().toString().equals("")) {
            return "Username is empty!";
        } else if (pinTxt.getText().toString().equals("")) {
            return "PIN is empty!";
        } else {
            return "";
        }
    }

    public void userLoginHandler(String roles) {
        ArrayList<User> users;
        if (roles.equals(ADMIN)) {
            AdminList adminList = new AdminList();
            adminList.load(this);
            users = adminList.getAdmins();
            User user = getUserByName(users);
            if (user == null) {
                Toast.makeText(this, "Username does not exist!",
                        Toast.LENGTH_SHORT).show();
            } else {
                String pin = pinTxt.getText().toString();
                if (pinTxt.getText().toString().equals(pin)) {
                    // successfully login
                    Intent intent = new Intent(Login.this, MenuPage.class);
                    intent.putExtra("Roles", ADMIN);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "PIN is incorrect!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public User getUserByName(List<User> users) {
        for (User user: users) {
            if (user.getName().equals(userNameTxt
                    .getText().toString())) {
                return user;
            }
        }
        return null;
    }
}