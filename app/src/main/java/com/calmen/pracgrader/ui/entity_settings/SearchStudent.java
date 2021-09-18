package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.view_list.ViewStudentList;

import java.util.ArrayList;

public class SearchStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student_page);

        TextView searchName = findViewById(R.id.searchNameTxt);
        Button confirmSearchBtn = findViewById(R.id.confirmSearchNameBtn);

        StudentList studentList = new StudentList();
        studentList.load(this);
        ArrayList<User> students = studentList.getStudents();

        confirmSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchName.getText().toString().equals("")) {
                    Toast.makeText(SearchStudent.this, "Search Name is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<User> searchStudents = new ArrayList<>();

                    /***
                     * @Search for all relevant username and add to searchStudents
                     */
                    for (User student: students) {
                        if (student.getUsername().contains(searchName.getText().toString())) {
                            searchStudents.add(student);
                        }
                    }

                    /**
                     * @searchStudents is empty if the search name does not exist
                     */
                    if (searchStudents.isEmpty()) {
                        Toast.makeText(SearchStudent.this, "Student does not exist!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(SearchStudent.this, ViewStudentList.class);
                        intent.putExtra("SearchStudentList", searchStudents);
                        view.getContext().startActivity(intent);
                    }
                }
            }
        });
    }
}