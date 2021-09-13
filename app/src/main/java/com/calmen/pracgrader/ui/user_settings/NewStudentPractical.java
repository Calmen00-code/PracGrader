package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.shared.recyler_edit.RecyclerEditAdapter;
import com.calmen.pracgrader.ui.student_practical_recycler.NewStudentPracticalRecylerAdapter;

public class NewStudentPractical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_student_practical_page);

        PracticalList practicalList = new PracticalList();

        RecyclerView rv = findViewById(R.id.newStudentPracticalRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        NewStudentPracticalRecylerAdapter adapter = new NewStudentPracticalRecylerAdapter(
                practicalList.getPracticals());
        rv.setAdapter(adapter);
    }
}