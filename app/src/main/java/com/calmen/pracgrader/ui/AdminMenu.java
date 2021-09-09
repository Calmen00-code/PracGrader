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
public class AdminMenu extends Fragment {
    public ArrayList<String> adminMenus;

    public AdminMenu() {
        adminMenus = new ArrayList<String>();
        adminMenus.add("Instructor");
        adminMenus.add("Student");
        adminMenus.add("Practical");
        adminMenus.add("Marking/Grading");
        adminMenus.add("View Instructor List");
        adminMenus.add("View Practical List");
        adminMenus.add("View Student List");
        adminMenus.add("Search Student List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_menu, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerFeature);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        FeatureRecyclerAdapter featureRecyclerAdapter = new FeatureRecyclerAdapter(this.adminMenus, Login.ADMIN);
        rv.setAdapter(featureRecyclerAdapter);
        return view;
    }
}