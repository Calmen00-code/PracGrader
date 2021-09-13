package com.calmen.pracgrader.ui.country_recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.shared.EditCountry;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.ui.user_settings.InstructorRegistration;
import com.calmen.pracgrader.ui.user_settings.StudentRegistration;
import com.calmen.pracgrader.ui.user_settings.EntityQuery;

public class CountryView extends Fragment {
    public static String operation;
    public static String userRoles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_view, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerFlag);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        CountryRecyclerAdapter countryRecyclerAdapter;
        if (operation.equals(EntityQuery.REGISTER_OPERATION)) {
            if (userRoles.equals(Login.INSTRUCTOR)) {
                // Admin register for Instructor
                countryRecyclerAdapter = new CountryRecyclerAdapter(
                        ((InstructorRegistration) getActivity()).getCountries(), this);
            } else {
                // Admin or Instructor register for Student
                countryRecyclerAdapter = new CountryRecyclerAdapter(
                        ((StudentRegistration) getActivity()).getCountries(), this);
            }
        } else {
            // Edit country operation
            countryRecyclerAdapter = new CountryRecyclerAdapter(
                    ((EditCountry) getActivity()).getCountries(), this);
        }
        rv.setAdapter(countryRecyclerAdapter);
        return view;
    }
}