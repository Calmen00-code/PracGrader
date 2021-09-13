package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.InstructorList;
import com.calmen.pracgrader.models.PracticalList;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.UserList;
import com.calmen.pracgrader.shared.ConfirmOperation;

/***
 * Query the user in the DB for operation edit and delete
 */
public class EntityQuery extends AppCompatActivity {
    public static final String REGISTER_OPERATION = "REGISTER_OPERATION";
    public static final String EDIT_OPERATION = "EDIT_OPERATION";
    public static final String DELETE_OPERATION = "DELETE_OPERATION";
    public static final String USER_TYPE_INSTRUCTOR = "INSTRUCTOR";
    public static final String USER_TYPE_STUDENT = "STUDENT";
    public static final String ENTITY_TYPE_PRACTICAL = "PRACTICAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entity_query_page);

        EditText entityTxt = findViewById(R.id.editEntityTxt);
        TextView entityTitleView = findViewById(R.id.entityTitleView);
        Button continueBtn = findViewById(R.id.continueDelBtn);

        String operation = getIntent().getStringExtra("Operation");
        String entityType = getIntent().getStringExtra("EntityType");

        UserList userList = null;
        PracticalList practicalList = null;

        String entityTitleTxt = "Enter Username";
        if (entityType.equals(USER_TYPE_INSTRUCTOR)) {
            entityTitleView.setText(entityTitleTxt);
            userList = new InstructorList();
            ((InstructorList) userList).load(EntityQuery.this);
        } else if (entityType.equals(USER_TYPE_STUDENT)) {
            entityTitleView.setText(entityTitleTxt);
            userList = new StudentList();
            ((StudentList) userList).load(EntityQuery.this);
            System.out.println("Student list loaded");
        } else {
            entityTitleTxt = "Enter Practical";
            entityTitleView.setText(entityTitleTxt);
            practicalList = new PracticalList();
            practicalList.load(EntityQuery.this);
        }

        UserList finalUserList = userList;
        PracticalList finalPracticalList = practicalList;
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entityTxt.getText().toString().equals("")) {
                    Toast.makeText(EntityQuery.this, "Username is empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (entityType.equals(USER_TYPE_INSTRUCTOR) || entityType.equals(USER_TYPE_STUDENT)) {
                        if (finalUserList.isExist(entityTxt.getText().toString())) {
                            Intent intent = new Intent(EntityQuery.this, ConfirmOperation.class);
                            intent.putExtra("Entity", entityTxt.getText().toString());
                            if (entityType.equals(USER_TYPE_INSTRUCTOR)) {
                                intent.putExtra("EntityType", USER_TYPE_INSTRUCTOR);
                            } else {
                                intent.putExtra("EntityType", USER_TYPE_STUDENT);
                            }

                            if (operation.equals(EDIT_OPERATION)) {
                                intent.putExtra("Operation", EDIT_OPERATION);
                            } else {
                                // Performing deletion
                                intent.putExtra("Operation", DELETE_OPERATION);
                            }
                            startActivity(intent);

                            // reload the DB again after delete or edit
                            if (entityType.equals(USER_TYPE_INSTRUCTOR)) {
                                ((InstructorList) finalUserList).load(EntityQuery.this);
                            } else {
                                ((StudentList) finalUserList).load(EntityQuery.this);
                            }
                            finish();
                        } else {
                            Toast.makeText(EntityQuery.this, "Username does not exist!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Edit or Delete for practical
                        if (finalPracticalList.isExist(entityTxt.getText().toString())) {
                            Intent intent = new Intent(EntityQuery.this, ConfirmOperation.class);
                            intent.putExtra("Entity", entityTxt.getText().toString());

                            intent.putExtra("EntityType", ENTITY_TYPE_PRACTICAL);

                            if (operation.equals(EDIT_OPERATION)) {
                                intent.putExtra("Operation", EDIT_OPERATION);
                            } else {
                                // Performing deletion
                                intent.putExtra("Operation", DELETE_OPERATION);
                            }
                            startActivity(intent);

                            // reload the DB again after delete or edit
                            finalPracticalList.load(EntityQuery.this);
                            finish();
                        } else {
                            Toast.makeText(EntityQuery.this, "Practical does not exist!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}