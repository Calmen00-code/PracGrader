package com.calmen.pracgrader.ui.student_practical_recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.models.Practical;

import java.util.ArrayList;


public class NewStudentPracticalRecylerAdapter extends RecyclerView.Adapter<NewStudentPracticalViewHolder> {
    ArrayList<Practical> practicals;

    public NewStudentPracticalRecylerAdapter(ArrayList<Practical> inPracticals) {
        this.practicals = inPracticals;
    }

    @NonNull
    @Override
    public NewStudentPracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewStudentPracticalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
