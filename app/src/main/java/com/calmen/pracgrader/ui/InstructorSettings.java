package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorSettings.this,
                        InstructorRegistration.class);
                startActivity(intent);
            }
        });
    }
}