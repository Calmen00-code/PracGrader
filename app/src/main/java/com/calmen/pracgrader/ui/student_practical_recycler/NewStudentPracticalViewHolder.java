package com.calmen.pracgrader.ui.student_practical_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class NewStudentPracticalViewHolder extends RecyclerView.ViewHolder {
    public TextView practicalTitle;
    public Button selStudentPracticalBtn;

    public NewStudentPracticalViewHolder(@NonNull View itemView) {
        super(itemView);
        practicalTitle = itemView.findViewById(R.id.practicalTitle);
        selStudentPracticalBtn = itemView.findViewById(R.id.studentPracticalBtn);
    }
}
