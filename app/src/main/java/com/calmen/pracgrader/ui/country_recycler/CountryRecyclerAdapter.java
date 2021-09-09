package com.calmen.pracgrader.ui.country_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;

import java.util.ArrayList;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    ArrayList<Country> countries;
    CountryView countryView;

    public CountryRecyclerAdapter(ArrayList<Country> inCountries, CountryView inCountryView) {
        this.countries = inCountries;
        this.countryView = inCountryView;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_row, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country singleCountry = countries.get(position);

        holder.countryTxt.setText(singleCountry.getName());

        /*
        Context context = holder.countryImg.getContext();
        int drawableID = context.getResources().getIdentifier(singleCountry.getFlag(),
                "drawable", context.getPackageCodePath());
        System.out.println(drawableID);
         */
        holder.countryImg.setImageResource(singleCountry.getFlag());
        holder.selectCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: selected image to the user
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
