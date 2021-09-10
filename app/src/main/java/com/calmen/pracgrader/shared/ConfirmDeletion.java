package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.user_settings.InstructorDelete;

public class ConfirmDeletion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_deletion_page);

        Button yesDelBtn = findViewById(R.id.yesDelBtn);
        Button noDelBtn = findViewById(R.id.noDelBtn);
        TextView emailDelView = findViewById(R.id.emailDelView);
        TextView countryDelView = findViewById(R.id.countryDelView);
        TextView nameDelView = findViewById(R.id.nameView);
        String username = getIntent().getStringExtra("Username");

        InstructorList instructorList = new InstructorList();
        instructorList.load(ConfirmDeletion.this);

        // username is check to be existed from previous activity ConfirmDeletion
        User delUser = instructorList.getUserByUsername(username);

        // parent instance (User) does not have name, email, country attributes
        if (delUser instanceof Instructor) {
            Instructor delInstructor = (Instructor) delUser;
            nameDelView.setText(delInstructor.getName());
            emailDelView.setText(delInstructor.getEmail());
            countryDelView.setText(delInstructor.getCountryName());
            System.out.println("Country: " + delInstructor.getCountryName());
        } // else if (delUser instanceof Student) {
        //}

        yesDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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