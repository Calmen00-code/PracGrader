package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.recyler_edit.EditData;
import com.calmen.pracgrader.shared.recyler_edit.RecyclerEditAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class EditEntity extends AppCompatActivity implements Serializable {
    // Menu for editing User Type
    public static final String EDIT_NAME = "Name: ";
    public static final String EDIT_USERNAME = "Username: ";
    public static final String EDIT_PIN = "PIN: ";
    public static final String EDIT_EMAIL = "Email: ";
    public static final String EDIT_PRACTICAL_LIST = "Practical List";
    public static final String EDIT_COUNTRY = "Country: ";

    // Menu for editing Practical Type
    public static final String EDIT_TITLE = "Title: ";
    public static final String EDIT_DESCRIPTION = "Description: ";
    public static final String EDIT_MARK = "Mark: ";

    ArrayList<EditData> edits;

    // will be used for detection of user in further activity
    public static User user;
    public static Practical practical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_page);

        // Determine edit for practical or edit for user
        user = (User) getIntent().getSerializableExtra("User");
        practical = (Practical) getIntent().getSerializableExtra("Practical");
        createEditData();

        RecyclerView rv = findViewById(R.id.recyclerEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerEditAdapter recyclerEditAdapter = new RecyclerEditAdapter(this, edits);
        rv.setAdapter(recyclerEditAdapter);
    }

    public void createEditData() {
        edits = new ArrayList<>();

        if (user == null && practical != null) {
            // Admin edit for practical type
            edits.add(new EditData(EDIT_TITLE, practical.getTitle()));
            edits.add(new EditData(EDIT_DESCRIPTION, practical.getDesc()));
            edits.add(new EditData(EDIT_MARK, String.valueOf(practical.getMark())));
        } else {
            // Admin edit for user type (Instructor/Student)
            if (user instanceof Instructor) {
                // Admin edit for Instructor
                edits.add(new EditData(EDIT_NAME, ((Instructor) user).getName()));
                edits.add(new EditData(EDIT_USERNAME, user.getUsername()));
                edits.add(new EditData(EDIT_PIN, String.valueOf(user.getPin())));
                edits.add(new EditData(EDIT_EMAIL, ((Instructor) user).getEmail()));
                edits.add(new EditData(EDIT_COUNTRY, ((Instructor) user).getCountryName()));
            } else {
                // Admin edit for Student
                assert user != null;
                edits.add(new EditData(EDIT_NAME, ((Student) user).getName()));
                edits.add(new EditData(EDIT_USERNAME, user.getUsername()));
                edits.add(new EditData(EDIT_PIN, String.valueOf(user.getPin())));
                edits.add(new EditData(EDIT_PRACTICAL_LIST, ""));
                edits.add(new EditData(EDIT_EMAIL, ((Student) user).getEmail()));
                edits.add(new EditData(EDIT_COUNTRY, ((Student) user).getCountryName()));
            }
        }
    }
}