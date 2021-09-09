package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.calmen.pracgrader.R;

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

    }
}