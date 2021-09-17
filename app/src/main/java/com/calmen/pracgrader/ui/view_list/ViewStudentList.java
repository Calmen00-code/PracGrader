package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.StudentPracticalList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.view_list.list_recycler.InsturctorListRecyclerAdapter;
import com.calmen.pracgrader.ui.view_list.list_recycler.StudentListRecyclerAdapter;

import java.util.ArrayList;

public class ViewStudentList extends AppCompatActivity {
    public ArrayList<User> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student_list);

        String grade = getIntent().getStringExtra("Grading");
        loadStudent();

        RecyclerView rv = findViewById(R.id.listStudentRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentListRecyclerAdapter listRecyclerAdapter;
        // check if viewing student list from grading option
        if (grade == null) {
            listRecyclerAdapter = new StudentListRecyclerAdapter(this, students, false);
        } else {
            listRecyclerAdapter = new StudentListRecyclerAdapter(this, students, true);
        }
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudent() {
        StudentList studentList = new StudentList();
        studentList.load(ViewStudentList.this);
        students = studentList.getStudents();

        for (User student: students) {
            StudentPracticalList studentPracList = ((Student) student).getStudentPracticalList();
            studentPracList.load(this);
            ArrayList<Practical> pracs = studentPracList.getStudentPracticals(
                    ((Student) student).getUniqueID());
            System.out.print("Username: " + student.getUsername() + "-> ");
            System.out.println("ID in loadStudent: " + ((Student) student).getUniqueID());
            for (Practical practical: pracs) {
                System.out.print(practical.getTitle() + ", ");
            }
            System.out.println();
        }
    }
}