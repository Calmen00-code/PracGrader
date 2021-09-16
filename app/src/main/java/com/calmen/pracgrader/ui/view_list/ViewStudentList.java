package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.view_list.list_recycler.UserListRecyclerAdapter;

import java.util.ArrayList;

public class ViewStudentList extends AppCompatActivity {
    public ArrayList<User> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_list);

        loadStudent();
        String grade = getIntent().getStringExtra("Grading");

        RecyclerView rv = findViewById(R.id.listViewRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        UserListRecyclerAdapter listRecyclerAdapter;
        // check if viewing student list from grading option
        if (grade == null) {
            listRecyclerAdapter = new UserListRecyclerAdapter(this, students, false);
        } else {
            listRecyclerAdapter = new UserListRecyclerAdapter(this, students, true);
        }
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudent() {
        StudentList studentList = new StudentList();
        studentList.load(ViewStudentList.this);
        students = studentList.getStudents();
    }
}