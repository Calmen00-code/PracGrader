package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.AdminList;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements Serializable {
    public static final String ADMIN = "ADMIN";
    public static final String INSTRUCTOR = "INSTRUCTOR";
    public static final String STUDENT = "STUDENT";

    private EditText userNameTxt, pinTxt;
    private Button signInBtn;
    private static User user;

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

    /***
     * Check if user exist, if exist, then check the pin
     * @param roles determine which table to query in DB
     */
    public void userLoginHandler(String roles) {
        ArrayList<User> users;
        if (roles.equals(ADMIN)) {
            AdminList adminList = new AdminList();
            adminList.load(this);
            users = adminList.getAdmins();
        } else if (roles.equals(INSTRUCTOR)) {
            InstructorList instructorList = new InstructorList();
            instructorList.load(this);
            users = instructorList.getInstructors();
        } else {
            StudentList studentList = new StudentList();
            // FIXME: have yet to implement load function for StudentList
            users = null;
        }
        user = getUserByName(users);
        if (user == null) {
            Toast.makeText(this, "Username does not exist!",
                    Toast.LENGTH_SHORT).show();
        } else {
            int pin = Integer.parseInt(pinTxt.getText().toString());
            if (pin == user.getPin()) {
                // successfully login
                Intent intent = new Intent(Login.this, MenuPage.class);
                // intent.putExtra("Roles", ADMIN);
                startActivity(intent);
            } else {
                Toast.makeText(Login.this, "PIN is incorrect!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /***
     * @param users is the list of currently checked user type
     * @return will be null if the user does not exist in the DB
     */
    public User getUserByName(List<User> users) {
        for (User user: users) {
            if (user.getUsername().equals(userNameTxt
                    .getText().toString())) {
                return user;
            }
        }
        return null;
    }

    public static User getUser() {
        return user;
    }
}