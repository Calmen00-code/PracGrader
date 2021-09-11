package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.User;

/***
 * Display all the options for the admin to make on instructors
 */
public class InstructorSettings extends AppCompatActivity {
    private Button addBtn, delBtn, editBtn;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_settings);

        titleView = findViewById(R.id.instructorSettingsView);
        addBtn = findViewById(R.id.addInstructorBtn);
        delBtn = findViewById(R.id.deleteInstructorBtn);
        editBtn = findViewById(R.id.editInstructorBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorSettings.this,
                        InstructorRegistration.class);
                startActivity(intent);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorSettings.this,
                        UserQuery.class);
                intent.putExtra("Operation", UserQuery.EDIT_OPERATION);
                intent.putExtra("UserType", UserQuery.USER_TYPE_INSTRUCTOR);
                startActivity(intent);
                InstructorList instructorList = new InstructorList();
                instructorList.load(InstructorSettings.this);
                finish();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorSettings.this,
                        UserQuery.class);
                intent.putExtra("Operation", UserQuery.DELETE_OPERATION);
                intent.putExtra("UserType", UserQuery.USER_TYPE_INSTRUCTOR);
                startActivity(intent);
                finish();
            }
        });
    }
}