package com.calmen.pracgrader.shared;

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
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.MenuPage;

/***
 * Edit all the attributes of the user except for Country
 */
public class EditAttribute extends AppCompatActivity {
    // number of params for Instructor is six
    public static final int INSTRUCTOR_PARAM = 6;
    // number of params for Student is 8 (include labUnit and mark)
    public static final int STUDENT_PARAM = 8;

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
                        updateUser(newVal, editTitle);
                        Toast.makeText(EditAttribute.this, "User has been updated!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    public void updateUser(String newVal, String editTitle) {
        String[] param;

        // assign all the old data, then update later
        if (EditUser.user instanceof Instructor) {
            param = new String[INSTRUCTOR_PARAM];
            Instructor user = (Instructor) EditUser.user;
            param[0] = user.getName();
            param[1] = user.getUsername();
            param[2] = String.valueOf(user.getPin());
            param[3] = user.getEmail();
            param[4] = user.getCountryName();
            param[5] = String.valueOf(user.getCountryFlag());

            // choose which attributes to be updated
            switch (editTitle) {
                case EditUser.EDIT_NAME:
                    param[0] = newVal;
                    break;
                case EditUser.EDIT_USERNAME:
                    param[1] = newVal;
                    break;
                case EditUser.EDIT_PIN:
                    param[2] = newVal;
                    break;
                case EditUser.EDIT_EMAIL:
                    param[3] = newVal;
                    break;
                case EditUser.EDIT_COUNTRY:
                    param[4] = newVal;
                    break;
            }
        } else {
            param = new String[STUDENT_PARAM];
            Student user = (Student) EditUser.user;
            param[0] = user.getName();
            param[1] = user.getUsername();
            param[2] = String.valueOf(user.getPin());
            param[3] = user.getEmail();
            param[4] = user.getLabUnit();
            param[5] = String.valueOf(user.getMark());
            param[6] = user.getCountryName();
            param[7] = String.valueOf(user.getCountryFlag());

            // choose which attributes to be updated
            switch (editTitle) {
                case EditUser.EDIT_NAME:
                    param[0] = newVal;
                    break;
                case EditUser.EDIT_USERNAME:
                    param[1] = newVal;
                    break;
                case EditUser.EDIT_PIN:
                    param[2] = newVal;
                    break;
                case EditUser.EDIT_EMAIL:
                    param[3] = newVal;
                    break;

                case EditUser.EDIT_LAB_UNIT:
                    param[4] = newVal;
                    break;

                case EditUser.EDIT_MARK:
                    param[5] = newVal;
                    break;

                case EditUser.EDIT_COUNTRY:
                    param[6] = newVal;
                    break;
            }
        }

        if (EditUser.user instanceof Instructor) {
            Instructor updateInstructor = new Instructor(param[0], param[1], Integer.parseInt(param[2]),
                    param[3], param[4], Integer.parseInt(param[5]));
            InstructorList instructorList = new InstructorList();
            instructorList.load(EditAttribute.this);
            instructorList.edit((Instructor) EditUser.user, updateInstructor);
        } else {
            Student updateStudent = new Student(param[0], param[1], Integer.parseInt(param[2]),
                    param[3], param[4], Double.parseDouble(param[5]), param[6], Integer.parseInt(param[7]));
            StudentList studentList = new StudentList();
            studentList.load(EditAttribute.this);
            studentList.edit((Student) EditUser.user, updateStudent);
        }
    }
}