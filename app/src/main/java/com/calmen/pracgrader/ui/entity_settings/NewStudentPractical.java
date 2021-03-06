package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.ui.student_practical_recycler.EditStudentPracticalRecylerAdapter;

public class NewStudentPractical extends AppCompatActivity {
    public static final String NEW_PRACTICAL = "NEW_PRACTICAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student_practical_page);

        PracticalList practicalList = new PracticalList();
        practicalList.load(NewStudentPractical.this);

        RecyclerView rv = findViewById(R.id.editStudentPracticalRecyler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        EditStudentPracticalRecylerAdapter adapter = new EditStudentPracticalRecylerAdapter(
                practicalList.getPracticals(), NEW_PRACTICAL);
        rv.setAdapter(adapter);
    }
}