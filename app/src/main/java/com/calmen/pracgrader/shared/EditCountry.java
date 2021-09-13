package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.ui.country_recycler.CountryView;
import com.calmen.pracgrader.ui.entity_settings.EntityQuery;

import java.util.ArrayList;

public class EditCountry extends AppCompatActivity {
    public static final int[] DRAWABLES = {
            0, // No country
            R.drawable.flag_my, R.drawable.flag_us, R.drawable.flag_au,
            R.drawable.flag_ca, R.drawable.flag_hk, R.drawable.flag_dk,
            R.drawable.flag_fr
    };

    public ArrayList<Country> countries;
    public TextView oldCountryTitle, oldCountryVal;

    public EditCountry() {
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
        setContentView(R.layout.edit_country_page);

        oldCountryTitle = findViewById(R.id.oldCountryTitle);
        oldCountryVal = findViewById(R.id.oldCountryVal);

        String title = "Country: ";
        oldCountryTitle.setText(title);
        if (EditEntity.user instanceof Instructor) {
            Instructor instructor = (Instructor) EditEntity.user;
            oldCountryVal.setText(instructor.getCountryName());
        } else {
            Student student = (Student) EditEntity.user;
            oldCountryVal.setText(student.getCountryName());
        }

        FragmentManager fm = getSupportFragmentManager();
        CountryView countryView = (CountryView) fm.findFragmentById(R.id.f_edit_container_flag);

        if (countryView == null) {
            countryView = new CountryView();
            CountryView.operation = EntityQuery.EDIT_OPERATION;
            fm.beginTransaction()
                    .add(R.id.f_edit_container_flag, countryView).commit();
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void updateUserCountry(String newCountry, Context context) {
        if (EditEntity.user instanceof Instructor) {
            Instructor instructor = (Instructor) EditEntity.user;
            Instructor updateInstructor = new Instructor(instructor.getName(),
                    instructor.getUsername(), instructor.getPin(), instructor.getEmail(),
                    newCountry, findCountryID(newCountry));
            InstructorList instructorList = new InstructorList();
            instructorList.load(context);
            instructorList.edit((Instructor) EditEntity.user, updateInstructor);
        } else {
            Student student = (Student) EditEntity.user;
            Student updateStudent = new Student(student.getName(),
                    student.getUsername(), student.getPin(), student.getEmail(),
                    student.getPracticalList(), newCountry, findCountryID(newCountry));
            StudentList studentList = new StudentList();
            studentList.load(context);
            studentList.edit((Student) EditEntity.user, updateStudent);
        }
        Toast.makeText(context, "User country has been updated!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public int findCountryID(String country) {
        if (country.equals("Malaysia")) {
            return R.drawable.flag_my;
        } else if (country.equals("United States")) {
            return R.drawable.flag_us;
        } else if (country.equals("Australia")) {
            return R.drawable.flag_au;
        } else if (country.equals("Canada")) {
            return R.drawable.flag_ca;
        } else if (country.equals("Hong Kong")) {
            return R.drawable.flag_hk;
        } else if (country.equals("Denmark")) {
            return R.drawable.flag_dk;
        } else {
            return R.drawable.flag_fr;
        }
    }
}