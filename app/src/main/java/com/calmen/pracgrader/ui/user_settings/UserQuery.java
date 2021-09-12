package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.UserList;
import com.calmen.pracgrader.shared.ConfirmOperation;

/***
 * Query the user in the DB for operation edit and delete
 */
public class UserQuery extends AppCompatActivity {
    public static final String REGISTER_OPERATION = "REGISTER_OPERATION";
    public static final String EDIT_OPERATION = "EDIT_OPERATION";
    public static final String DELETE_OPERATION = "DELETE_OPERATION";
    public static final String USER_TYPE_INSTRUCTOR = "INSTRUCTOR";
    public static final String USER_TYPE_STUDENT = "STUDENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_query_page);

        EditText usernameTxt = findViewById(R.id.editTextPersonName);
        Button continueBtn = findViewById(R.id.continueDelBtn);

        String operation = getIntent().getStringExtra("Operation");
        String userType = getIntent().getStringExtra("UserType");

        UserList userList;

        if (userType.equals(USER_TYPE_INSTRUCTOR)) {
            userList = new InstructorList();
            ((InstructorList) userList).load(UserQuery.this);
        } else {
            userList = new StudentList();
            ((StudentList) userList).load(UserQuery.this);
            System.out.println("Student list loaded");
        }

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameTxt.getText().toString().equals("")) {
                    Toast.makeText(UserQuery.this, "Username is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (userList.isExist(usernameTxt.getText().toString())) {
                        Intent intent = new Intent(UserQuery.this, ConfirmOperation.class);
                        intent.putExtra("Username", usernameTxt.getText().toString());
                        if (userType.equals(USER_TYPE_INSTRUCTOR)) {
                            intent.putExtra("UserType", USER_TYPE_INSTRUCTOR);
                        } else {
                            intent.putExtra("UserType", USER_TYPE_STUDENT);
                        }

                        if (operation.equals(EDIT_OPERATION)) {
                            intent.putExtra("Operation", EDIT_OPERATION);
                        } else {
                            // Performing deletion
                            intent.putExtra("Operation", DELETE_OPERATION);
                        }
                        startActivity(intent);

                        // reload the DB again after delete or edit
                        if (userType.equals(USER_TYPE_INSTRUCTOR)) {
                            ((InstructorList) userList).load(UserQuery.this);
                        } else {
                            ((StudentList) userList).load(UserQuery.this);
                        }
                        finish();
                    } else {
                        Toast.makeText(UserQuery.this, "Username does not exist!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}