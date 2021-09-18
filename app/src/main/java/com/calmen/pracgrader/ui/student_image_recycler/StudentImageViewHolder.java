package com.calmen.pracgrader.ui.student_image_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class StudentImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView studentImg;
    public Button selImageBtn;

    public StudentImageViewHolder(@NonNull View itemView) {
        super(itemView);
        studentImg = itemView.findViewById(R.id.studentImageIcon);
        selImageBtn = itemView.findViewById(R.id.selectImageBtn);
    }
}
