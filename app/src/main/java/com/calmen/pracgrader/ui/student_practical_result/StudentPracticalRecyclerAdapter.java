package com.calmen.pracgrader.ui.student_practical_result;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.ui.view_list.list_recycler.StudentListViewHolder;

import java.util.ArrayList;

public class StudentPracticalRecyclerAdapter extends RecyclerView.Adapter<StudentPracticalViewHolder> {
    ArrayList<Practical> practicals;
    Activity activity;

    public StudentPracticalRecyclerAdapter(Activity inActivity, ArrayList<Practical> inPracticals) {
        this.activity = inActivity;
        this.practicals = inPracticals;
    }

    @NonNull
    @Override
    public StudentPracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_student_menu_row, parent, false);
        return new StudentPracticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentPracticalViewHolder holder, int position) {
        Practical practical = practicals.get(position);
        holder.practicalName.setText(practical.getTitle());
        holder.practicalMark.setText(String.valueOf(practical.getMark()));
    }

    @Override
    public int getItemCount() {
        return practicals.size();
    }
}
