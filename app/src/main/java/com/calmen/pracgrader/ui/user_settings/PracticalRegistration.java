package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.Validation;

import java.util.ArrayList;

public class PracticalRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practical_registration_page);

        EditText pracTitle = findViewById(R.id.practicalTitileTxt);
        EditText pracDesc = findViewById(R.id.pracDescTxt);
        EditText markTxt = findViewById(R.id.pracMarkTxt);
        Button confirmBtn = findViewById(R.id.confirmRegisterPracBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pracTitle.getText().toString().equals("")) {
                    Toast.makeText(PracticalRegistration.this, "Practical Title is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (pracDesc.getText().toString().equals("")) {
                    Toast.makeText(PracticalRegistration.this, "Practical Description is empty!",
                            Toast.LENGTH_SHORT).show();
                } else if (markTxt.getText().toString().equals("")) {
                    Toast.makeText(PracticalRegistration.this, "Practical Mark is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    PracticalList practicalList = new PracticalList();
                    practicalList.load(view.getContext());

                    if (checkDuplicatePractical(practicalList.getPracticals(),
                            pracTitle.getText().toString())) {
                        Toast.makeText(PracticalRegistration.this, "Practical Title already exist!",
                                Toast.LENGTH_SHORT).show();
                    } else if (Validation.isDouble(markTxt.getText().toString())) {
                        Toast.makeText(PracticalRegistration.this, "Practical mark must be decimals value!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Practical practical = new Practical(pracTitle.getText().toString(),
                                pracDesc.getText().toString(), Double.parseDouble(markTxt.getText().toString()),
                                0.0, practicalList.getUniqueRefID());
                        practicalList.add(practical);
                        Toast.makeText(PracticalRegistration.this, "Practical created!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }

    public static boolean checkDuplicatePractical(ArrayList<Practical> practicals, String title) {
        for (Practical practical: practicals) {
            if (practical.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}