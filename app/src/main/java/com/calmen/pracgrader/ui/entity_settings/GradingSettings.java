package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.ui.view_list.ViewStudentList;

public class GradingSettings extends AppCompatActivity {
    public static final String GRADING = "GRADING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(GradingSettings.this, ViewStudentList.class);
        intent.putExtra("Grading", GRADING);
        startActivity(intent);
    }
}