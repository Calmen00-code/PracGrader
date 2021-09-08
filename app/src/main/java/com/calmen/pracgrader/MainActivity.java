package com.calmen.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.calmen.pracgrader.users.AdminList;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.ui.Registration;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private AdminList adminList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adminList = new AdminList();
        adminList.load(this);

        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);

        /*
        if (adminList.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }*/

        /*
        // check if the app was first installed
        public static final String PREFS_NAME = "MY_PREF_FILE";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            setContentView(R.layout.login_page);
        } */
    }
}