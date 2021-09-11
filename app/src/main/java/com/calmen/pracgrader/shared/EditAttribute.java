package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;

public class EditAttribute extends AppCompatActivity {

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
                    }
                }
            }
        });
    }
}