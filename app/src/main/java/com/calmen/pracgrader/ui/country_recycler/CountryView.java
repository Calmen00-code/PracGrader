package com.calmen.pracgrader.ui.country_recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.ui.user_settings.InstructorRegistration;

public class CountryView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_view, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerFlag);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CountryRecyclerAdapter countryRecyclerAdapter =
                new CountryRecyclerAdapter(((InstructorRegistration) getActivity()).getCountries(),
                        this);
        rv.setAdapter(countryRecyclerAdapter);
        return view;
    }
}