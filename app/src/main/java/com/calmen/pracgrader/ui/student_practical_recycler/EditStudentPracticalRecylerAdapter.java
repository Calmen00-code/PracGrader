package com.calmen.pracgrader.ui.student_practical_recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.shared.EditEntity;
import com.calmen.pracgrader.shared.EditStudentPractical;
import com.calmen.pracgrader.shared.Validation;
import com.calmen.pracgrader.ui.entity_settings.NewStudentPractical;
import com.calmen.pracgrader.ui.entity_settings.PracticalMarkInput;

import java.util.ArrayList;


public class EditStudentPracticalRecylerAdapter extends RecyclerView.Adapter<EditStudentPracticalViewHolder> {
    ArrayList<Practical> practicals;
    String operation;

    public EditStudentPracticalRecylerAdapter(ArrayList<Practical> inPracticals, String inOperation) {
        this.practicals = inPracticals;
        this.operation = inOperation;
    }

    @NonNull
    @Override
    public EditStudentPracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_student_practical_row, parent, false);
        EditStudentPracticalViewHolder viewHolder = new EditStudentPracticalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditStudentPracticalViewHolder holder, int position) {
        Practical singlePractical = practicals.get(position);
        holder.practicalTitle.setText(singlePractical.getTitle());
        holder.selStudentPracticalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // adding new practical to the student
                if (operation.equals(NewStudentPractical.NEW_PRACTICAL)) {
                    if (EditEntity.user instanceof Student) {
                        Student student = (Student) EditEntity.user;
                        student.getStudentPracticalList().load(view.getContext());

                        ArrayList<Practical> practicals = student.getStudentPracticalList().
                                getStudentPracticals(student.getUniqueID());
                        // check for duplication practical register on student
                        if (Validation.checkDuplicateTitle(practicals, singlePractical.getTitle())) {
                            Toast.makeText(view.getContext(),
                                    "Current student has already register to the practical!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // adding new practical to student
                            student.getStudentPracticalList().add(new Practical(singlePractical.getTitle(),
                                    singlePractical.getDesc(), singlePractical.getMark(), 0.0,
                                    student.getUniqueID()));
                            Toast.makeText( view.getContext(), "Practical Added to "
                                    + student.getUsername() + "!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // input mark to current selected practical of a student
                    if (EditEntity.user instanceof Student) {
                        Student student = (Student) EditEntity.user;

                        ArrayList<Practical> practicals = student.getStudentPracticalList().
                                getStudentPracticals(student.getUniqueID());
                        Practical studentPractical = student.getStudentPracticalByTitle(practicals,
                                singlePractical.getTitle());

                        if (studentPractical != null) {
                            Intent intent = new Intent(view.getContext(), PracticalMarkInput.class);
                            intent.putExtra("studentPractical", studentPractical);
                            intent.putExtra("studentUniqueID", student.getUniqueID());
                            intent.putExtra("studentPracticalList", student.getStudentPracticalList());
                            view.getContext().startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return practicals.size();
    }
}
