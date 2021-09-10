package com.calmen.pracgrader.ui.user_settings;

import androidx.annotation.Nullable;
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
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.ConfirmDeletion;

import java.util.ArrayList;

public class InstructorDelete extends AppCompatActivity {
    public static final int REQUEST_PLAY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_delete_page);

        EditText usernameDelTxt = findViewById(R.id.editTextPersonName);
        Button continueBtn = findViewById(R.id.continueDelBtn);

        InstructorList instructorList = new InstructorList();
        instructorList.load(InstructorDelete.this);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameDelTxt.getText().toString().equals("")) {
                    Toast.makeText(InstructorDelete.this, "Username is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (instructorList.isExist(usernameDelTxt.getText().toString())) {
                        Intent intent = new Intent(InstructorDelete.this, ConfirmDeletion.class);
                        intent.putExtra("Username", usernameDelTxt.getText().toString());
                        startActivity(intent);

                        // reload the DB again after deletion
                        instructorList.load(InstructorDelete.this);
                    } else {
                        Toast.makeText(InstructorDelete.this, "Username does not exist!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}