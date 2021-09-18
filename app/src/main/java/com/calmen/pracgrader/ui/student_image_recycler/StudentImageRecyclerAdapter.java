package com.calmen.pracgrader.ui.student_image_recycler;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.shared.ConfirmOperation;
import com.calmen.pracgrader.shared.ConfirmRegistration;

public class StudentImageRecyclerAdapter extends RecyclerView.Adapter<StudentImageViewHolder> {
    Activity activity;
    int[] studentImages;

    public StudentImageRecyclerAdapter(Activity inActivity, int[] inStudentImage) {
        this.activity = inActivity;
        this.studentImages = inStudentImage;
    }

    @NonNull
    @Override
    public StudentImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_image_row, parent, false);
        StudentImageViewHolder studentImageViewHolder = new StudentImageViewHolder(view);
        return studentImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentImageViewHolder holder, int position) {
        int image = studentImages[position];

        holder.studentImg.setImageResource(image);
        holder.selImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Add image to student here
                // TODO: need to implement a new image field in student model
                // TODO: reflect the changes in DB too
                Intent intent = new Intent(view.getContext(), ConfirmRegistration.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentImages.length;
    }
}
