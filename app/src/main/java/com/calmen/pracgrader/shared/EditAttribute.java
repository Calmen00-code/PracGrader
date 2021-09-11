package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.MenuPage;

public class EditAttribute extends AppCompatActivity {
    // number of params for Instructor and Student are six
    public static final int USER_PARAM = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_attribute_page);

        TextView oldAttributeTitle = findViewById(R.id.oldAttributeTitle);
        TextView oldAttributeVal = findViewById(R.id.oldAttributeVal);
        EditText newAttributeTxt = findViewById(R.id.newAttributeInput);
        Button confirmEditBtn = findViewById(R.id.confirmEditBtn);

        String editTitle = getIntent().getStringExtra("EditTitle");
        String oldVal = getIntent().getStringExtra("OldValue");

        oldAttributeTitle.setText(editTitle);
        oldAttributeVal.setText(oldVal);
        confirmEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newAttributeTxt.equals("")) {
                    Toast.makeText(EditAttribute.this, "New Value is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String newVal = newAttributeTxt.getText().toString();
                    if (editTitle.equals(EditUser.EDIT_PIN) &&
                            !Validation.checkValidPIN(newVal).equals(Validation.VALID)) {
                        Toast.makeText(EditAttribute.this, Validation.checkValidPIN(newVal),
                                Toast.LENGTH_SHORT).show();
                    } else if (editTitle.equals(EditUser.EDIT_EMAIL) &&
                            !Validation.checkValidEmail(newVal).equals(Validation.VALID)) {
                        Toast.makeText(EditAttribute.this, Validation.checkValidEmail(newVal),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // TODO: implement edit in DB here
                        updateUser(newVal, editTitle);
                        Toast.makeText(EditAttribute.this, "User has been updated!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    public void updateUser(String newVal, String editTitle) {
        String[] param = new String[USER_PARAM];

        if (EditUser.user instanceof Instructor) {
            Instructor user = (Instructor) EditUser.user;
            param[0] = user.getName();
            param[1] = user.getUsername();
            param[2] = String.valueOf(user.getPin());
            param[3] = user.getEmail();
            param[4] = user.getCountryName();
            param[5] = String.valueOf(user.getCountryFlag());
        } else {
            Student user = (Student) EditUser.user;
            param[0] = user.getName();
            param[1] = user.getUsername();
            param[2] = String.valueOf(user.getPin());
            param[3] = user.getEmail();
            param[4] = user.getCountryName();
            param[5] = String.valueOf(user.getCountryFlag());
        }

        switch (editTitle) {
            case EditUser.EDIT_NAME:
                param[0] = newVal;
                break;
            case EditUser.EDIT_USERNAME:
                param[1] = newVal;
                break;
            case EditUser.EDIT_PIN:
                param[2] = newVal;
                break;
            case EditUser.EDIT_EMAIL:
                param[3] = newVal;
                break;
            case EditUser.EDIT_COUNTRY:
                param[4] = newVal;
                break;
        }

        if (EditUser.user instanceof Instructor) {
            Instructor updateInstructor = new Instructor(param[0], param[1], Integer.parseInt(param[2]),
                    param[3], param[4], Integer.parseInt(param[5]));
            InstructorList instructorList = new InstructorList();
            instructorList.edit((Instructor) EditUser.user, updateInstructor);
            instructorList.load(EditAttribute.this);
        } else {
            // TODO: update student list here
        }
    }
}