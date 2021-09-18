package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Student;

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
        }
    }
}