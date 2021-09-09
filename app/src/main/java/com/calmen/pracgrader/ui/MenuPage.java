package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.calmen.pracgrader.R;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        String roles = (String) getIntent().getSerializableExtra("Roles");
        FragmentManager fm = getSupportFragmentManager();

        if (roles.equals(Login.ADMIN)) {
            // Load menu list for admin
            AdminMenu adminMenu = (AdminMenu) fm.findFragmentById(R.id.frag_menu);
            if (adminMenu == null) {
                adminMenu = new AdminMenu();
                fm.beginTransaction()
                        .add(R.id.frag_menu, adminMenu).commit();
            }
        }
    }
}