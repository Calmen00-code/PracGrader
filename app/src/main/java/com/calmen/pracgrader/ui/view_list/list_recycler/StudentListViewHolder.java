package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class StudentListViewHolder extends RecyclerView.ViewHolder {
    TextView studentNameView, studentMarkView;
    Button viewStudentBtn;

    public StudentListViewHolder(@NonNull View itemView) {
        super(itemView);
        studentNameView = itemView.findViewById(R.id.nameStudentView);
        studentMarkView = itemView.findViewById(R.id.totalMarkStudentView);
        viewStudentBtn = itemView.findViewById(R.id.viewStudentBtn);
    }
}
