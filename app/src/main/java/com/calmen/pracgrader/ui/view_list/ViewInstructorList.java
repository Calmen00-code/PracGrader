package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.recyler_edit.RecyclerEditAdapter;
import com.calmen.pracgrader.ui.view_list.list_recycler.ListRecyclerAdapter;

import java.util.ArrayList;

public class ViewInstructorList extends AppCompatActivity {
    public ArrayList<User> instructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_list);

        loadInstructors();
        RecyclerView rv = findViewById(R.id.listViewRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ListRecyclerAdapter listRecyclerAdapter = new ListRecyclerAdapter(this, instructors);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadInstructors() {
        InstructorList instructorList = new InstructorList();
        instructorList.load(ViewInstructorList.this);
        instructors = instructorList.getUsers();
    }
}