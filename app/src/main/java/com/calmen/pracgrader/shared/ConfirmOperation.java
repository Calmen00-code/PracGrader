package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.user_settings.UserQuery;

public class ConfirmOperation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_operation_page);

        Button yesDelBtn = findViewById(R.id.yesDelBtn);
        Button noDelBtn = findViewById(R.id.noDelBtn);
        TextView emailDelView = findViewById(R.id.emailDelView);
        TextView countryDelView = findViewById(R.id.countryDelView);
        TextView nameDelView = findViewById(R.id.nameView);
        TextView titleConfirmOperation = findViewById(R.id.titleConfirmOperation);

        String username = getIntent().getStringExtra("Username");
        String userType = getIntent().getStringExtra("UserType");
        String operation = getIntent().getStringExtra("Operation");

        User userOperation;
        InstructorList instructorList = new InstructorList();
        if (userType.equals(UserQuery.USER_TYPE_INSTRUCTOR)) {
            instructorList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            userOperation = instructorList.getUserByUsername(username);
        } else {
            // TODO: Do the same for Student
            userOperation = new User("temp", 0);
        }

        String titleTxt = "";
        if (operation.equals(UserQuery.EDIT_OPERATION)) {
            titleTxt += "Confirm Edit?";
            titleConfirmOperation.setText(titleTxt);
        } else {
            titleTxt += "Confirm Delete?";
            titleConfirmOperation.setText(titleTxt);
        }

        // parent instance (User) does not have name, email, country attributes
        if (userOperation instanceof Instructor) {
            Instructor delInstructor = (Instructor) userOperation;
            nameDelView.setText(delInstructor.getName());
            emailDelView.setText(delInstructor.getEmail());
            countryDelView.setText(delInstructor.getCountryName());
            System.out.println("Country: " + delInstructor.getCountryName());
        } // else if (userOperation instanceof Student) {
        //}

        yesDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userOperation instanceof Instructor) {
                    if (operation.equals(UserQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditUser.class);
                        intent.putExtra("User", userOperation);
                        startActivity(intent);
                    } else {
                        instructorList.remove((Instructor) userOperation);
                    }
                } else {
                    // TODO: Do the same for Student here
                }
                finish();
            }
        });

        noDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}