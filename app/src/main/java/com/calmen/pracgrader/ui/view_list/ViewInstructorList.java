package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.view_list.list_recycler.InsturctorListRecyclerAdapter;

import java.util.ArrayList;

public class ViewInstructorList extends AppCompatActivity {
    public ArrayList<User> instructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_instructor_list);

        loadInstructors();
        RecyclerView rv = findViewById(R.id.listViewRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        InsturctorListRecyclerAdapter listRecyclerAdapter = new InsturctorListRecyclerAdapter(this, instructors);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadInstructors() {
        InstructorList instructorList = new InstructorList();
        instructorList.load(ViewInstructorList.this);
        instructors = instructorList.getInstructors();
    }
}