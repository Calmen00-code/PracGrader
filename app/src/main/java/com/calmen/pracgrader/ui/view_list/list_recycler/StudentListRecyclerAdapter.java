package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.EditEntity;
import com.calmen.pracgrader.shared.EditStudentPractical;

import java.util.ArrayList;

public class StudentListRecyclerAdapter extends RecyclerView.Adapter<StudentListViewHolder> {
    ArrayList<User> students;
    Activity activity;
    boolean isGrade;


    public StudentListRecyclerAdapter(Activity inActivity, ArrayList<User> inStudents, boolean inGrade) {
        this.activity = inActivity;
        this.students = inStudents;
        this.isGrade = inGrade;
    }

    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_student_row, parent, false);
        StudentListViewHolder listViewHolder = new StudentListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int position) {
        User user = students.get(position);

        Student student = ((Student) user);
        View view = holder.getView();
        if (student.getTotalMark(activity) >= 0 && student.getTotalMark(activity) <= 50) {
            view.setBackgroundColor(0xFFFF1100);
        } else if (student.getTotalMark(activity) >= 51 && student.getTotalMark(activity) <= 80) {
            view.setBackgroundColor(0xFFFFE500);
        } else if (student.getTotalMark(activity) >= 81 && student.getTotalMark(activity) <= 100) {
            view.setBackgroundColor(0xFF06730A);
        }
        holder.studentNameView.setText(user.getUsername());
        holder.studentMarkView.setText(String.valueOf(((Student) user).getTotalMark(activity)));
        holder.viewStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (isGrade) {
                    intent = new Intent(view.getContext(), EditStudentPractical.class);
                    EditEntity.user = user; // setting user directly
                } else {
                    // display the detail of the editing for student
                    intent = new Intent(view.getContext(), EditEntity.class);
                    intent.putExtra("User", user);
                }
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
