package com.calmen.pracgrader.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.ui.feature_recycler.FeatureRecyclerAdapter;

import java.util.ArrayList;

/**
 * Display all functionalities of Admin
 */
public class InstructorMenu extends Fragment {
    public ArrayList<String> instructorMenus;

    public InstructorMenu() {
        instructorMenus = new ArrayList<String>();
        instructorMenus.add("Student");
        instructorMenus.add("Marking/Grading");
        instructorMenus.add("View Student List");
        instructorMenus.add("Search Student List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_menu, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerFeature);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        FeatureRecyclerAdapter featureRecyclerAdapter = new FeatureRecyclerAdapter(this.instructorMenus, Login.INSTRUCTOR);
        rv.setAdapter(featureRecyclerAdapter);
        return view;
    }
}