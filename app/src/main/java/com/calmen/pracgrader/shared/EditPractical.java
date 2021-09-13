/***
 * @Purpose Assign a new practical to a Student Object
 * @Trigger when the select button was clicked on EditStudent features
 */

package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.ui.entity_settings.NewStudentPractical;

public class EditPractical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_practical_page);

        Button addNewBtn = findViewById(R.id.addNewPracBtn);
        Button markPracBtn = findViewById(R.id.markingPracBtn);

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPractical.this, NewStudentPractical.class);
                startActivity(intent);
            }
        });
    }
}