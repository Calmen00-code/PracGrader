package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.ui.student_practical_result.StudentPracticalRecyclerAdapter;
import com.calmen.pracgrader.ui.view_list.list_recycler.StudentListRecyclerAdapter;

import java.util.ArrayList;

public class StudentMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_menu_page);

        ArrayList<Practical> practicals = (ArrayList<Practical>) getIntent()
                .getSerializableExtra("StudentPractical");

        RecyclerView rv = findViewById(R.id.listStudentPracticalRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentPracticalRecyclerAdapter listRecyclerAdapter = new StudentPracticalRecyclerAdapter(
                this, practicals);
        rv.setAdapter(listRecyclerAdapter);
    }
}