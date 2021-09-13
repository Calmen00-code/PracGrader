package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class PracticalListViewHolder extends RecyclerView.ViewHolder {
    TextView practicalTitleView;
    Button viewPracticalBtn;

    public PracticalListViewHolder(@NonNull View itemView) {
        super(itemView);
        practicalTitleView = itemView.findViewById(R.id.practicalTitleView);
        viewPracticalBtn = itemView.findViewById(R.id.viewPracticalBtn);
    }
}
