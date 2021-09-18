package com.calmen.pracgrader.ui.view_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.StudentList;
import com.calmen.pracgrader.models.StudentPracticalList;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.ui.view_list.list_recycler.InsturctorListRecyclerAdapter;
import com.calmen.pracgrader.ui.view_list.list_recycler.StudentListRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ViewStudentList extends AppCompatActivity {
    public ArrayList<User> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student_list);

        String grade = getIntent().getStringExtra("Grading");
        loadStudent();

        RecyclerView rv = findViewById(R.id.listStudentRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentListRecyclerAdapter listRecyclerAdapter;
        // check if viewing student list from grading option
        if (grade == null) {
            listRecyclerAdapter = new StudentListRecyclerAdapter(this, students, false);
        } else {
            listRecyclerAdapter = new StudentListRecyclerAdapter(this, students, true);
        }
        rv.setAdapter(listRecyclerAdapter);
    }

    public void loadStudent() {
        StudentList studentList = new StudentList();
        studentList.load(ViewStudentList.this);
        students = studentList.getStudents();

        if (Login.getUser() instanceof Instructor) {
            ArrayList<User> instructorStudents = new ArrayList<>();
            // retrieve all students which is only register by the instructor
            for (User student: students) {
                System.out.println("Instructor state: " + ((Student) student).isRegByInstructor());
                if (((Student) student).isRegByInstructor() == Student.INSTRUCTOR_REG_TRUE) {
                    instructorStudents.add(student);
                    System.out.println("Added Instructor - Students");
                }
            }
            System.out.println("Change to instructor registered student");
            students = instructorStudents;
        }

        /***
         * @Sort
         * algorithm taken from StackOverflow
         * https://stackoverflow.com/questions/18895915/how-to-sort-an-array-of-objects-in-java
         */
        Collections.sort(students, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getUsername().compareTo(u2.getUsername());
            }
        });

        /**
         * @Test below code for testing purpose only, not involve in the App
         */
        for (User student: students) {
            StudentPracticalList studentPracList = ((Student) student).getStudentPracticalList();
            studentPracList.load(this);
            ArrayList<Practical> pracs = studentPracList.getStudentPracticals(
                    ((Student) student).getUniqueID());
            System.out.print("Username: " + student.getUsername() + "-> ");
            System.out.println("ID in loadStudent: " + ((Student) student).getUniqueID());
            for (Practical practical: pracs) {
                System.out.print(practical.getTitle() + ", ");
            }
            System.out.println();
        }
    }
}