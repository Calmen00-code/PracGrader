package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.EditText;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.ui.country_recycler.CountryView;

import java.util.ArrayList;

public class InstructorRegistration extends AppCompatActivity implements UserRegistration {
    public static final int[] DRAWABLES = {
        0, // No country
        R.drawable.flag_my, R.drawable.flag_us, R.drawable.flag_au,
            R.drawable.flag_ca, R.drawable.flag_hk, R.drawable.flag_dk,
            R.drawable.flag_fr
    };

    public ArrayList<Country> countries;
    public EditText instructorUsername, instructorName, instructorEmail;
    public EditText instructorPin, instructorPinTwo;

    public InstructorRegistration() {
        countries = new ArrayList<Country>();
        countries.add(new Country("Malaysia", R.drawable.flag_my));
        countries.add(new Country("United States", R.drawable.flag_us));
        countries.add(new Country("Australia", R.drawable.flag_au));
        countries.add(new Country("Canada", R.drawable.flag_ca));
        countries.add(new Country("Hong Kong", R.drawable.flag_hk));
        countries.add(new Country("Denmark", R.drawable.flag_dk));
        countries.add(new Country("France", R.drawable.flag_fr));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration_page);

        instructorName = findViewById(R.id.instructorNameTxt);
        instructorUsername = findViewById(R.id.instructorUsernameTxt);
        instructorEmail = findViewById(R.id.instructorEmailTxt);
        instructorPin = findViewById(R.id.pinInstructorTxt);
        instructorPinTwo = findViewById(R.id.pinInstructorTxt2);

        FragmentManager fm = getSupportFragmentManager();
        CountryView countryView = (CountryView) fm.findFragmentById(R.id.f_container_flag);

        if (countryView == null) {
            countryView = new CountryView();
            fm.beginTransaction()
                    .add(R.id.f_container_flag, countryView).commit();
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    @Override
    public EditText getName() {
        return instructorName;
    }

    @Override
    public EditText getUsername() {
        return instructorUsername;
    }

    @Override
    public EditText getEmail() {
        return instructorEmail;
    }

    @Override
    public EditText getPin() {
        return instructorPin;
    }

    @Override
    public EditText getPinTwo() {
        return instructorPinTwo;
    }
}