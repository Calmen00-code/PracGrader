package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.pracgrader.R;

public class UserSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        Button adminBtn = findViewById(R.id.adminSelBtn);
        Button instructorBtn = findViewById(R.id.instructorSelBtn);
        Button studentBtn = findViewById(R.id.studentSelBtn);

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSelection.this, Login.class);
                intent.putExtra("Roles", "ADMIN");
                startActivity(intent);
            }
        });

        instructorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSelection.this, Login.class);
                intent.putExtra("Roles", "INSTRUCTOR");
                startActivity(intent);
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSelection.this, Login.class);
                intent.putExtra("Roles", "STUDENT");
                startActivity(intent);
            }
        });
    }
}