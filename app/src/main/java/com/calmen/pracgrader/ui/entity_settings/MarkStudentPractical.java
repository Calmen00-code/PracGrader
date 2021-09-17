package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentPracticalList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.EditEntity;
import com.calmen.pracgrader.ui.student_practical_recycler.EditStudentPracticalRecylerAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class MarkStudentPractical extends AppCompatActivity implements Serializable {
    public static final String MARK_OPERATION = "MARK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student_practical_page);

        User student = (User) getIntent().getSerializableExtra("Student");
        StudentPracticalList studentPracticalList = new StudentPracticalList(
                ((Student) student).getUniqueID());
        studentPracticalList.load(MarkStudentPractical.this);

        RecyclerView rv = findViewById(R.id.editStudentPracticalRecyler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        EditStudentPracticalRecylerAdapter adapter;

        if (student == null) {
            // Marking student from EditQuery
            adapter = new EditStudentPracticalRecylerAdapter(studentPracticalList
                    .getStudentPracticals(((Student) EditEntity.user).getUniqueID()), MARK_OPERATION);
        } else {
            // Edit student from Marking/Grading
            adapter = new EditStudentPracticalRecylerAdapter(studentPracticalList
                    .getStudentPracticals(((Student) student).getUniqueID()), MARK_OPERATION);

        }
        rv.setAdapter(adapter);
    }
}