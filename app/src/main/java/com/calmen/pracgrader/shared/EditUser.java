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
    ArrayList<EditData> edits;
    String userStr;
    User user;

    public EditUser() {
        edits = new ArrayList<EditData>();
        if (userStr.equals(UserQuery.USER_TYPE_INSTRUCTOR)) {
            edits.add(new EditData(((Instructor) user).getName()));
            edits.add(new EditData(user.getUsername()));
            edits.add(new EditData(String.valueOf(user.getPin())));
            edits.add(new EditData(((Instructor) user).getEmail()));
            edits.add(new EditData(((Instructor) user).getCountryName()));
            edits.add(new EditData(String.valueOf(((Instructor) user).getCountryFlag())));
        } else {
            edits.add(new EditData(((Student) user).getName()));
            edits.add(new EditData(user.getUsername()));
            edits.add(new EditData(String.valueOf(user.getPin())));
            edits.add(new EditData(((Student) user).getEmail()));
            edits.add(new EditData(((Student) user).getCountryName()));
            edits.add(new EditData(String.valueOf(((Student) user).getCountryFlag())));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_page);

        userStr = getIntent().getStringExtra("UserStr");
        user = (User) getIntent().getSerializableExtra("User");

        RecyclerView rv = findViewById(R.id.recyclerEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerEditAdapter recyclerEditAdapter = new RecyclerEditAdapter(edits);
        rv.setAdapter(recyclerEditAdapter);
    }
}