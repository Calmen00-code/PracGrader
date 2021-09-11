package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.recyler_edit.EditData;
import com.calmen.pracgrader.shared.recyler_edit.RecyclerEditAdapter;
import com.calmen.pracgrader.ui.user_settings.UserQuery;

import java.io.Serializable;
import java.util.ArrayList;

public class EditUser extends AppCompatActivity implements Serializable {
    public static final String EDIT_NAME = "Name: ";
    public static final String EDIT_USERNAME = "Username: ";
    public static final String EDIT_PIN = "PIN: ";
    public static final String EDIT_EMAIL = "Email: ";
    public static final String EDIT_COUNTRY = "Country: ";

    ArrayList<EditData> edits;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_page);

        user = (User) getIntent().getSerializableExtra("User");
        createEditData();

        RecyclerView rv = findViewById(R.id.recyclerEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerEditAdapter recyclerEditAdapter = new RecyclerEditAdapter(this, edits);
        rv.setAdapter(recyclerEditAdapter);
    }

    public void createEditData() {
        edits = new ArrayList<>();

        if (user instanceof Instructor) {
            edits.add(new EditData(EDIT_NAME, ((Instructor) user).getName()));
            edits.add(new EditData(EDIT_USERNAME, user.getUsername()));
            edits.add(new EditData(EDIT_PIN, String.valueOf(user.getPin())));
            edits.add(new EditData(EDIT_EMAIL, ((Instructor) user).getEmail()));
            edits.add(new EditData(EDIT_COUNTRY, ((Instructor) user).getCountryName()));
        } else {
            edits.add(new EditData(EDIT_NAME, ((Student) user).getName()));
            edits.add(new EditData(EDIT_USERNAME, user.getUsername()));
            edits.add(new EditData(EDIT_PIN, String.valueOf(user.getPin())));
            edits.add(new EditData(EDIT_EMAIL, ((Student) user).getEmail()));
            edits.add(new EditData(EDIT_COUNTRY, ((Student) user).getCountryName()));
        }

    }
}