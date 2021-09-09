package com.calmen.pracgrader.ui.country_recycler;

import android.content.Context;
import android.content.Intent;
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
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.shared.ConfirmRegistration;
import com.calmen.pracgrader.shared.Validation;
import com.calmen.pracgrader.ui.InstructorRegistration;
import com.calmen.pracgrader.ui.UserRegistration;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    if (msg.equals("")) {
                        // proceed to ask confirmation to register instructor
                        Intent intent = new Intent(view.getContext(), ConfirmRegistration.class);
                        String name = getName();
                        String username = getUsername();
                        String email = getEmail();
                        String pin = getPin();
                        String pinTwo = getPinTwo();

                        intent.putExtra("Name", name);
                        intent.putExtra("Username", username);
                        intent.putExtra("Email", email);
                        intent.putExtra("Pin", pin);
                        intent.putExtra("PinTwo", pinTwo);

                        view.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
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
        EditText name = ((UserRegistration) (this.countryView.getActivity())).getUsername();
        EditText username = ((UserRegistration) (this.countryView.getActivity())).getUsername();
        EditText email = ((UserRegistration) (this.countryView.getActivity())).getEmail();
        EditText pin = ((UserRegistration) (this.countryView.getActivity())).getPin();
        EditText pinTwo = ((UserRegistration) (this.countryView.getActivity())).getPinTwo();

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

    /**
     * @return empty when all the attributes is valid
     */
    public String checkValidAttributes() {
        EditText username = ((UserRegistration) (this.countryView.getActivity())).getUsername();
        EditText email = ((UserRegistration) (this.countryView.getActivity())).getEmail();
        EditText pin = ((UserRegistration) (this.countryView.getActivity())).getPin();
        EditText pinTwo = ((UserRegistration) (this.countryView.getActivity())).getPinTwo();

        String msg = Validation.checkValidAttributes(username, pin, pinTwo);
        if (msg.equals("")) {
            Pattern pattern = Pattern.compile(Instructor.EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email.getText().toString());

            // check email if username and password is valid
            if (matcher.matches()) {
                return "";
            } else {
                return "Email is invalid!";
            }
        } else {
            return msg;
        }
    }

    public String getName() {
        return ((UserRegistration) (this.countryView.getActivity())).getUsername().toString();
    }

    public String getUsername() {
        return ((UserRegistration) (this.countryView.getActivity())).getUsername().toString();
    }

    public String getEmail() {
        return ((UserRegistration) (this.countryView.getActivity())).getEmail().toString();
    }

    public String getPin() {
        return ((UserRegistration) (this.countryView.getActivity())).getPin().toString();
    }

    public String getPinTwo() {
        return ((UserRegistration) (this.countryView.getActivity())).getPinTwo().toString();
    }
}
