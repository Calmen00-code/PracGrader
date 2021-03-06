package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.StudentPracticalList;
import com.calmen.pracgrader.shared.Validation;

import java.util.ArrayList;

public class PracticalMarkInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practical_mark_input);

        EditText markInput = findViewById(R.id.markInputTxt);
        Button confirmMark = findViewById(R.id.inputMarkBtn);

        Practical studentPractical = (Practical) getIntent().getSerializableExtra("studentPractical");
        int uniqueID = getIntent().getIntExtra("studentUniqueID", -1);
        StudentPracticalList studentPracticalList = (StudentPracticalList) getIntent().getSerializableExtra(
                "studentPracticalList");

        confirmMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (markInput.getText().toString().equals("")) {
                    Toast.makeText(PracticalMarkInput.this, "Mark field is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String mark = markInput.getText().toString();
                    if (Validation.isDouble(mark)) {
                        if (Validation.validMark(mark)) {
                            studentPractical.setStudentMark(Double.parseDouble(mark));
                            studentPracticalList.load(view.getContext());
                            studentPracticalList.edit(studentPractical, uniqueID);
                            Toast.makeText(PracticalMarkInput.this,
                                    "Student mark has been updated!", Toast.LENGTH_SHORT).show();
                            studentPracticalList.load(view.getContext());
                            finish();
                        } else {
                            Toast.makeText(PracticalMarkInput.this, "Mark is not within 0 to 100!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PracticalMarkInput.this,
                                "Mark must be decimal values!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}