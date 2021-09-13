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
        TextView emailOpView = findViewById(R.id.emailOpView);
        TextView countryOpView = findViewById(R.id.countryOpView);
        TextView nameOpView = findViewById(R.id.nameOpView);
        TextView titleConfirmOperation = findViewById(R.id.titleConfirmOperation);

        String entityVal = getIntent().getStringExtra("Entity");
        String userType = getIntent().getStringExtra("EntityType");
        String operation = getIntent().getStringExtra("Operation");

        User userOperation;
        InstructorList instructorList = null;
        StudentList studentList = null;
        if (userType.equals(EntityQuery.USER_TYPE_INSTRUCTOR)) {
            instructorList = new InstructorList();
            instructorList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            userOperation = instructorList.getUserByUsername(entityVal);
        } else {
            studentList = new StudentList();
            studentList.load(ConfirmOperation.this);
            // username is check to be existed from previous activity ConfirmDeletion
            userOperation = studentList.getUserByUsername(entityVal);
        }

        String titleTxt = "";
        if (operation.equals(EntityQuery.EDIT_OPERATION)) {
            titleTxt += "Confirm Edit?";
        } else {
            titleTxt += "Confirm Delete?";
        }
        titleConfirmOperation.setText(titleTxt);

        // parent instance (User) does not have name, email, country attributes
        if (userOperation instanceof Instructor) {
            Instructor instructor = (Instructor) userOperation;
            nameOpView.setText(instructor.getName());
            emailOpView.setText(instructor.getEmail());
            countryOpView.setText(instructor.getCountryName());
        } else if (userOperation instanceof Student) {
            Student student = (Student) userOperation;
            nameOpView.setText(student.getName());
            emailOpView.setText(student.getEmail());
            countryOpView.setText(student.getCountryName());
        }

        InstructorList finalInstructorList = instructorList;
        StudentList finalStudentList = studentList;
        yesOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userOperation instanceof Instructor) {
                    if (operation.equals(EntityQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditUser.class);
                        intent.putExtra("User", userOperation);
                        startActivity(intent);
                    } else {
                        finalInstructorList.remove((Instructor) userOperation);
                        Toast.makeText(ConfirmOperation.this,
                                "Instructor has been removed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (operation.equals(EntityQuery.EDIT_OPERATION)) {
                        Intent intent = new Intent(ConfirmOperation.this,
                                EditUser.class);
                        intent.putExtra("User", userOperation);
                        startActivity(intent);
                    } else {
                        assert finalStudentList != null;
                        finalStudentList.remove((Student) userOperation);
                        Toast.makeText(ConfirmOperation.this,
                                "Student has been removed!", Toast.LENGTH_SHORT).show();
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