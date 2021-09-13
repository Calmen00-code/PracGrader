package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.ui.view_list.list_recycler.PracticalListRecyclerAdapter;

import java.util.ArrayList;

public class ViewPracticalList extends AppCompatActivity {
    public ArrayList<Practical> practicals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_practical_list);

        loadPractical();
        RecyclerView rv = findViewById(R.id.recyclerPracticalList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        PracticalListRecyclerAdapter listRecyclerAdapter = new PracticalListRecyclerAdapter(this, practicals);
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadPractical() {
        PracticalList practicalList = new PracticalList();
        practicalList.load(ViewPracticalList.this);
        practicals = practicalList.getPracticals();
    }
}