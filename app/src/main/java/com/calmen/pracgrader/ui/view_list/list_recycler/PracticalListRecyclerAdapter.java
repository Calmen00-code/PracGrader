package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;

import java.util.ArrayList;

public class PracticalListRecyclerAdapter extends RecyclerView.Adapter<PracticalListViewHolder> {
    ArrayList<Practical> practicals;
    Activity activity;

    public PracticalListRecyclerAdapter(Activity inActivity, ArrayList<Practical> inPracticals) {
        this.activity = inActivity;
        this.practicals = inPracticals;
    }

    @NonNull
    @Override
    public PracticalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_practical_row, parent, false);
        PracticalListViewHolder listViewHolder = new PracticalListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticalListViewHolder holder, int position) {
        Practical singlePractical = practicals.get(position);

        holder.practicalTitleView.setText(singlePractical.getTitle());
        holder.viewPracticalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: implement viewing of practical
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
