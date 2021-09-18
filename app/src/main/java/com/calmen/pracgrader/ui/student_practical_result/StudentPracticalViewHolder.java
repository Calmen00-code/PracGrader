package com.calmen.pracgrader.ui.student_practical_result;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class StudentPracticalViewHolder extends RecyclerView.ViewHolder {
    TextView practicalName, practicalMark;

    public StudentPracticalViewHolder(@NonNull View itemView) {
        super(itemView);
        practicalName = itemView.findViewById(R.id.studentPracticalName);
        practicalMark = itemView.findViewById(R.id.thePracticalMark);
    }
}
