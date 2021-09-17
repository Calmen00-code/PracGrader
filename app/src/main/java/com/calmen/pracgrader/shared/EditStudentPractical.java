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
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.entity_settings.MarkStudentPractical;
import com.calmen.pracgrader.ui.entity_settings.NewStudentPractical;

import java.util.ArrayList;

public class EditStudentPractical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_practical_page);

        User student = (User) getIntent().getSerializableExtra("Student");
        Button addNewBtn = findViewById(R.id.addNewPracBtn);
        Button markPracticalBtn = findViewById(R.id.markingPracBtn);

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStudentPractical.this, NewStudentPractical.class);
                startActivity(intent);
            }
        });

        markPracticalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStudentPractical.this, MarkStudentPractical.class);
                intent.putExtra("Student", student);
                startActivity(intent);

                // Marking is done from Edit by Query student username if User student is not NULL
                if (student == null) {
                    ((Student) EditEntity.user).getStudentPracticalList().load(view.getContext());
                    ArrayList<Practical> practicals = ((Student) EditEntity.user)
                            .getStudentPracticalList()
                            .getStudentPracticals(((Student) EditEntity.user).getUniqueID());
                    double mark = 0.0;
                    for (Practical practical: practicals) {
                        System.out.println("Practical Title: " + practical.getTitle());
                        mark += practical.getStudentMark();
                    }
                    System.out.println("Student name: " + ((Student) EditEntity.user).getName());
                    System.out.println("Student mark: " + mark);
                }

            }
        });
    }
}