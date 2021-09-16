package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class InstructorListViewHolder extends RecyclerView.ViewHolder {
    TextView usernameView;
    Button viewBtn;

    public InstructorListViewHolder(@NonNull View itemView) {
        super(itemView);
        usernameView = itemView.findViewById(R.id.usernameListView);
        viewBtn = itemView.findViewById(R.id.viewUserBtn);
    }
}
