package com.calmen.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MY_PREF_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // check if the app was first installed
        if (settings.getBoolean("my_first_time", true)) {
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            setContentView(R.layout.login_page);
        }
    }
}