package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.pracgrader.R;

import java.io.Serializable;

public class ConfirmRegistration extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_registration_page);

        Button yesRegBtn = findViewById(R.id.yesRegBtn);
        Button noRegBtn = findViewById(R.id.noRegBtn);
        String name = getIntent().getStringExtra("Name");
        String username = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        String pin = getIntent().getStringExtra("Pin");
        String pinTwo = getIntent().getStringExtra("PinTwo");

        yesRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        noRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}