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

        StudentPracticalList studentPracticalList = new StudentPracticalList(((Student) EditEntity.user).getUniqueID());
        studentPracticalList.load(MarkStudentPractical.this);

        RecyclerView rv = findViewById(R.id.editStudentPracticalRecyler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Practical> practicals = studentPracticalList.getStudentPracticals(((Student) EditEntity.user).getUniqueID());
        for (Practical practical: practicals) {
            System.out.println("Practical in Adapter: " + practical.getTitle());
        }

        EditStudentPracticalRecylerAdapter adapter = new EditStudentPracticalRecylerAdapter(
                studentPracticalList.getStudentPracticals(((Student) EditEntity.user).getUniqueID())
                , MARK_OPERATION);
        rv.setAdapter(adapter);
    }
}