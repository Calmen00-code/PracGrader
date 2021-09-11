package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.calmen.pracgrader.R;

public class EditAttribute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_attribute_page);

        TextView oldAttributeView = findViewById(R.id.oldAttributeTitle);
        EditText newAttributeTxt = findViewById(R.id.newAttributeInput);
        Button confirmEditBtn = findViewById(R.id.confirmEditBtn);
        
    }
}