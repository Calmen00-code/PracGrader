package com.calmen.pracgrader.ui.country_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.shared.Validation;
import com.calmen.pracgrader.ui.InstructorRegistration;

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
        holder.countryImg.setImageResource(singleCountry.getFlag());
        holder.selectCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = checkEmptyAttributes();
                if (msg.equals("")) {
                    msg = checkValidAttributes();
                    // TODO: proceed to ask confirmation to register instructor
                } else {
                    Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    /**
     * @return empty string if all attributes is not empty
     */
    public String checkEmptyAttributes() {
        EditText name = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorName();
        EditText username = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorUsername();
        EditText email = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorEmail();
        EditText pin = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorPin();
        EditText pinTwo = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorPinTwo();

        if (name.getText().toString().equals("")) {
            return "Name is empty!";
        } else if (username.getText().toString().equals("")) {
            return "Username is empty!";
        } else if (email.getText().toString().equals("")) {
            return "Email is empty!";
        } else if (pin.getText().toString().equals("")) {
            return "PIN is empty!";
        } else if (pinTwo.getText().toString().equals("")) {
            return "Re-enter PIN is empty!";
        } else {
            return "";
        }
    }

    public String checkValidAttributes() {
        EditText username = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorUsername();
        EditText email = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorEmail();
        EditText pin = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorPin();
        EditText pinTwo = ((InstructorRegistration) (this.countryView.getActivity())).getInstructorPinTwo();

        String msg = Validation.checkValidAttributes(username, pin, pinTwo);
        if (msg.equals("")) {
            // check email if username and password is valid
            if (email.getText().toString().endsWith(""))
        }

    }
}
