package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.AdminMenu;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.ui.MenuPage;
import com.calmen.pracgrader.ui.country_recycler.CountryView;

import java.io.Serializable;
import java.util.ArrayList;

public class ConfirmRegistration extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_registration_page);

        Button yesRegBtn = findViewById(R.id.yesRegBtn);
        Button noRegBtn = findViewById(R.id.noRegBtn);
        String name = getIntent().getStringExtra("Name");
        String username = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        int pin = Integer.parseInt(getIntent().getStringExtra("Pin"));
        Country country = (Country) getIntent().getSerializableExtra("Country");

        yesRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login.getUser() instanceof Admin) {
                    // Only admin can add new instructor
                    if (CountryView.userRoles.equals(Login.INSTRUCTOR)) {
                        InstructorList instructorList = new InstructorList();
                        instructorList.load(view.getContext());
                        ArrayList<User> instructors = instructorList.getInstructors();

                        if (Validation.checkDuplicateName(instructors, username)) {
                            Toast.makeText(view.getContext(), "Username has already been taken!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            instructorList.add(new Instructor(name, username, pin,
                                    email, country.getName(), country.getFlag()));
                            // only admin can add a new instructor, therefore we do not need to check for other user menu
                            Intent intent = new Intent(ConfirmRegistration.this, MenuPage.class);
                            // finish() all the parent activities
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Toast.makeText(view.getContext(), "Instructor has been created!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    } else {
                        // Admin register new Student
                        StudentList studentList = new StudentList();
                        studentList.load(view.getContext());
                        ArrayList<User> students = studentList.getStudents();

                        if (Validation.checkDuplicateName(students, username)) {
                            Toast.makeText(view.getContext(), "Username has already been taken!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            studentList.add(new Student(name, username, pin,
                                    email, "", 0.0, country.getName(), country.getFlag()));
                            // only admin can add a new instructor, therefore we do not need to check for other user menu
                            Intent intent = new Intent(ConfirmRegistration.this, MenuPage.class);
                            // finish() all the parent activities
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Toast.makeText(view.getContext(), "Student has been created!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }
                } else if (Login.getUser() instanceof Instructor) {
                    // Instructor register new Student
                }
            }
        });

        noRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}