package com.calmen.pracgrader.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;

/***
 * Edit all the attributes of the user except for Country
 */
public class EditAttribute extends AppCompatActivity {
    // number of params for Instructor is 6
    public static final int INSTRUCTOR_PARAM = 6;
    // number of params for Student is 6 (include practicalList)
    public static final int STUDENT_PARAM = 6;
    // number of params for Practical is 5
    public static final int PRACTICAL_PARAM = 5;

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
                if (newAttributeTxt.getText().toString().equals("")) {
                    Toast.makeText(EditAttribute.this, "New Value is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String newVal = newAttributeTxt.getText().toString();
                    if (editTitle.equals(EditEntity.EDIT_PIN) &&
                            !Validation.checkValidPIN(newVal).equals(Validation.VALID)) {
                        Toast.makeText(EditAttribute.this, Validation.checkValidPIN(newVal),
                                Toast.LENGTH_SHORT).show();
                    } else if (editTitle.equals(EditEntity.EDIT_EMAIL) &&
                            !Validation.checkValidEmail(newVal).equals(Validation.VALID)) {
                        Toast.makeText(EditAttribute.this, Validation.checkValidEmail(newVal),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        updateUser(newVal, editTitle);
                    }
                }
            }
        });
    }

    public void updateUser(Object newVal, String editTitle) {
        Object[] param;

        if (EditEntity.user == null && EditEntity.practical != null) {
            // edit is on practical
            param = new Object[PRACTICAL_PARAM];
            Practical practical = (Practical) EditEntity.practical;
            param[0] = practical.getTitle();
            param[1] = practical.getDesc();
            param[2] = String.valueOf(practical.getMark());
            param[3] = String.valueOf(practical.getStudentMark());
            param[4] = String.valueOf(practical.getUniqueRefID());

            // choose which attributes to be updated
            // student mark can be edited from Edit Student
            // uniqueRefID can never be edited
            switch (editTitle) {
                case EditEntity.EDIT_TITLE:
                    param[0] = newVal;
                    break;

                case EditEntity.EDIT_DESCRIPTION:
                    param[1] = newVal;
                    break;

                case EditEntity.EDIT_MARK:
                    param[2] = newVal;
                    break;
            }

            // Edit for instructor
            Practical updatePractical = new Practical((String) param[0], (String) param[1],
                    Double.parseDouble((String) param[2]), Double.parseDouble((String) param[3]),
                    Integer.parseInt((String) param[4]));

            PracticalList practicalList = new PracticalList();
            practicalList.load(EditAttribute.this);

            if (editTitle.equals(EditEntity.EDIT_TITLE) &&
                    Validation.checkDuplicateTitle(practicalList.getPracticals(), (String) param[0])) {
                Toast.makeText(EditAttribute.this, "Title has already been taken!",
                        Toast.LENGTH_SHORT).show();
            } else if (editTitle.equals(EditEntity.EDIT_TITLE) &&
                    !Validation.checkDuplicateTitle(practicalList.getPracticals(), (String) param[1])) {
                // Edit for practical title ONLY
                practicalList.edit(EditEntity.practical, updatePractical);
                Toast.makeText(EditAttribute.this, "Title updated!",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // Edit for the rest of instructor attributes
                practicalList.edit(EditEntity.practical, updatePractical);
                Toast.makeText(EditAttribute.this, "Attribute updated!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            // edit is either on Instructor or Student
            // assign all the old data, then update later
            if (EditEntity.user instanceof Instructor) {
                param = new String[INSTRUCTOR_PARAM];
                Instructor user = (Instructor) EditEntity.user;
                param[0] = user.getName();
                param[1] = user.getUsername();
                param[2] = String.valueOf(user.getPin());
                param[3] = user.getEmail();
                param[4] = user.getCountryName();
                param[5] = String.valueOf(user.getCountryFlag());

                // choose which attributes to be updated
            } else {
                param = new Object[STUDENT_PARAM];
                Student user = (Student) EditEntity.user;
                param[0] = user.getName();
                param[1] = user.getUsername();
                param[2] = String.valueOf(user.getPin());
                param[3] = user.getEmail();
                param[4] = user.getCountryName();
                param[5] = String.valueOf(user.getCountryFlag());

                // choose which attributes to be updated
            }

            switch (editTitle) {
                case EditEntity.EDIT_NAME:
                    param[0] = newVal;
                    break;
                case EditEntity.EDIT_USERNAME:
                    param[1] = newVal;
                    break;
                case EditEntity.EDIT_PIN:
                    param[2] = newVal;
                    break;
                case EditEntity.EDIT_EMAIL:
                    param[3] = newVal;
                    break;
            }

            if (EditEntity.user instanceof Instructor) {
                // Edit for instructor
                assert param[4] != null;
                Instructor updateInstructor = new Instructor((String) param[0], (String) param[1],
                        Integer.parseInt((String) param[2]), (String) param[3], (String) param[4],
                        Integer.parseInt((String) param[5]));
                InstructorList instructorList = new InstructorList();
                instructorList.load(EditAttribute.this);

                if (editTitle.equals(EditEntity.EDIT_USERNAME) &&
                        Validation.checkDuplicateName(instructorList.getInstructors(), (String) param[1])) {
                    Toast.makeText(EditAttribute.this, "Username has already been taken!",
                            Toast.LENGTH_SHORT).show();
                } else if (editTitle.equals(EditEntity.EDIT_USERNAME) &&
                        !Validation.checkDuplicateName(instructorList.getInstructors(), (String) param[1])) {
                    // Edit for instructor username ONLY
                    instructorList.edit((Instructor) EditEntity.user, updateInstructor);
                    Toast.makeText(EditAttribute.this, "Username updated!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Edit for the rest of instructor attributes
                    instructorList.edit((Instructor) EditEntity.user, updateInstructor);
                    Toast.makeText(EditAttribute.this, "Entity updated!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                // Edit for student
                assert false;
                Student updateStudent = new Student((String) param[0], (String) param[1],
                        Integer.parseInt((String) param[2]), (String) param[3], (String) param[4],
                        Integer.parseInt((String) param[5]));
                StudentList studentList = new StudentList();
                studentList.load(EditAttribute.this);

                if (editTitle.equals(EditEntity.EDIT_USERNAME) &&
                        Validation.checkDuplicateName(studentList.getStudents(), (String) param[1])) {
                    Toast.makeText(EditAttribute.this, "Username has already been taken!",
                            Toast.LENGTH_SHORT).show();
                } else if (editTitle.equals(EditEntity.EDIT_USERNAME) &&
                        !Validation.checkDuplicateName(studentList.getStudents(), (String) param[1])) {
                    // Edit for student username ONLY
                    studentList.edit((Student) EditEntity.user, updateStudent);
                    Toast.makeText(EditAttribute.this, "Username updated!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Edit for the rest of student attributes
                    studentList.edit((Student) EditEntity.user, updateStudent);
                    Toast.makeText(EditAttribute.this, "Entity updated!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}