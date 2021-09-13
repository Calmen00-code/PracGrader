package com.calmen.pracgrader.ui.student_practical_recycler;

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
import com.calmen.pracgrader.shared.recyler_edit.EditDataViewHolder;

import java.util.ArrayList;


public class NewStudentPracticalRecylerAdapter extends RecyclerView.Adapter<NewStudentPracticalViewHolder> {
    ArrayList<Practical> practicals;

    public NewStudentPracticalRecylerAdapter(ArrayList<Practical> inPracticals) {
        this.practicals = inPracticals;
    }

    @NonNull
    @Override
    public NewStudentPracticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_student_practical_row, parent, false);
        NewStudentPracticalViewHolder viewHolder = new NewStudentPracticalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewStudentPracticalViewHolder holder, int position) {
        Practical singlePractical = practicals.get(position);
        holder.practicalTitle.setText(singlePractical.getTitle());
        holder.selStudentPracticalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: adding practical to the student here
                if (EditEntity.user instanceof Student) {
                    Student student = (Student) EditEntity.user;
                    student.getStudentPracticalList().load(view.getContext());
                    student.getStudentPracticalList().add(new Practical(singlePractical.getTitle(),
                            singlePractical.getDesc(), singlePractical.getMark(), 0.0,
                            student.getUniqueID()));
                    ArrayList<Practical> practicals = student.getStudentPracticalList().
                            getStudentPracticals(student.getUniqueID());
                    for (Practical practical: practicals) {
                        System.out.println(practical.getTitle());
                    }
                    Toast.makeText( view.getContext(), "Practical Added to "
                                    + student.getUsername() + "!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return practicals.size();
    }
}
