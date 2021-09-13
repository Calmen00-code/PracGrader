package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.user_settings.EntityQuery;

public class ConfirmOperation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_operation_page);

        Button yesOpBtn = findViewById(R.id.yesOpBtn);
        Button noOpBtn = findViewById(R.id.noOpBtn);
        TextView firstView = findViewById(R.id.firstView);
        TextView secondView = findViewById(R.id.secondView);
        TextView thirdView = findViewById(R.id.thirdView);
        TextView titleConfirmOperation = findViewById(R.id.titleConfirmOperation);

        String entityVal = getIntent().getStringExtra("Entity");
        String entityType = getIntent().getStringExtra("EntityType");
        String operation = getIntent().getStringExtra("Operation");

        User userOperation = null;
        InstructorList instructorList = null;
        StudentList studentList = null;
        PracticalList practicalList = null;
        Practical practical = null;
        if (entityType.equals(EntityQuery.USER_TYPE_INSTRUCTOR)) {
            instructorList = new InstructorList();
            instructorList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            userOperation = instructorList.getUserByUsername(entityVal);
        } else if (entityType.equals(EntityQuery.USER_TYPE_STUDENT)) {
            studentList = new StudentList();
            studentList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            userOperation = studentList.getUserByUsername(entityVal);
        } else {
            practicalList = new PracticalList();
            practicalList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            practical = practicalList.getPracByTitle(entityVal);
        }

        String titleTxt = "";
        if (operation.equals(EntityQuery.EDIT_OPERATION)) {
            titleTxt += "Confirm Edit?";
        } else {
            titleTxt += "Confirm Delete?";
        }
        titleConfirmOperation.setText(titleTxt);

        if (userOperation instanceof Instructor) {
            // parent instance (User) does not have name, email, country attributes
            Instructor instructor = (Instructor) userOperation;
            firstView.setText(instructor.getName());
            secondView.setText(instructor.getEmail());
            thirdView.setText(instructor.getCountryName());
        } else if (userOperation instanceof Student) {
            // parent instance (User) does not have name, email, country attributes
            Student student = (Student) userOperation;
            firstView.setText(student.getName());
            secondView.setText(student.getEmail());
            thirdView.setText(student.getCountryName());
        } else {
            firstView.setText(practical.getTitle());
            secondView.setText(practical.getDesc());
            thirdView.setText(String.valueOf(practical.getMark()));
        }

        InstructorList finalInstructorList = instructorList;
        StudentList finalStudentList = studentList;
        User finalUserOperation = userOperation;
        PracticalList finalPracticalList = practicalList;
        Practical finalPractical = practical;
        yesOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalUserOperation instanceof Instructor) {
                    if (operation.equals(EntityQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditEntity.class);
                        intent.putExtra("User", finalUserOperation);
                        startActivity(intent);
                    } else {
                        assert finalInstructorList != null;
                        finalInstructorList.remove((Instructor) finalUserOperation);
                        Toast.makeText(ConfirmOperation.this,
                                "Instructor has been removed!", Toast.LENGTH_SHORT).show();
                    }
                } else if (finalUserOperation instanceof Student) {
                    if (operation.equals(EntityQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditEntity.class);
                        intent.putExtra("User", finalUserOperation);
                        startActivity(intent);
                    } else {
                        assert finalStudentList != null;
                        finalStudentList.remove((Student) finalUserOperation);
                        Toast.makeText(ConfirmOperation.this,
                                "Student has been removed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (operation.equals(EntityQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditEntity.class);
                        intent.putExtra("Practical", finalPractical);
                        startActivity(intent);
                    } else {
                        assert finalPracticalList != null;
                        finalPracticalList.remove(finalPractical);
                        Toast.makeText(ConfirmOperation.this,
                                "Practical has been removed!", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });

        noOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}