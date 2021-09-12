package com.calmen.pracgrader.models;

import android.content.Context;

import com.calmen.pracgrader.database.DBModel;

import java.util.ArrayList;

public class StudentList extends UserList {
    DBModel dbModel;

    public StudentList() { super(); }

    public void load(Context context) {
        dbModel = new DBModel();
        dbModel.load(context);
        super.setUsers(dbModel.getAllStudents());
    }

    public void add(Student student) {
        super.getUsers().add(student);
        if (dbModel == null) {
            throw new NullPointerException("Database does not exist");
        } else {
            dbModel.addStudent(student);
        }
    }

    public void remove(Student student) {
        super.getUsers().remove(student);
        dbModel.removeStudent(student);
    }

    public void edit(Student oldStudent, Student newStudent) {
        System.out.println("oldStudent username: " + oldStudent.getUsername());
        if (getStudents().isEmpty()) {
            System.out.println("Student is empty");
        } else {
            System.out.println("Student is NOT EMPTY");
        }
        for (User user: getStudents()) {
            Student student = (Student) user;

            if (student.getUsername().equals(oldStudent.getUsername())) {
                student = newStudent;
                dbModel.updateStudent(student, oldStudent.getUsername());
            }
        }
    }

    public ArrayList<User> getStudents() {
        return super.getUsers();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean isExist(String username) {
        return super.isExist(username);
    }
}
