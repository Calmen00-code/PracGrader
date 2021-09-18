package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.ui.entity_settings.StudentMenu;

import java.util.ArrayList;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        // String roles = (String) getIntent().getSerializableExtra("Roles");
        FragmentManager fm = getSupportFragmentManager();

        if (Login.getUser() instanceof Admin) {
            // Load menu list for admin
            AdminMenu adminMenu = (AdminMenu) fm.findFragmentById(R.id.frag_menu);
            if (adminMenu == null) {
                adminMenu = new AdminMenu();
                fm.beginTransaction()
                        .add(R.id.frag_menu, adminMenu).commit();
            }
        } else if (Login.getUser() instanceof Instructor) {
            // Load menu list for instructor
            InstructorMenu instructorMenu = (InstructorMenu) fm.findFragmentById(R.id.frag_menu);
            if (instructorMenu == null) {
                instructorMenu = new InstructorMenu();
                fm.beginTransaction()
                        .add(R.id.frag_menu, instructorMenu).commit();
            }
        } else if (Login.getUser() instanceof Student) {
            // Load practical list for student
            Student student = (Student) Login.getUser();
            student.getStudentPracticalList().load(this);
            ArrayList<Practical> practicals = student
                    .getStudentPracticalList().getStudentPracticals(student.getUniqueID());
            Intent intent = new Intent(MenuPage.this, StudentMenu.class);
            intent.putExtra("StudentPractical", practicals);
            startActivity(intent);
            finish();
        }
    }
}