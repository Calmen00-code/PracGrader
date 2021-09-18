package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Practical;
import com.calmen.pracgrader.shared.EditEntity;

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
        PracticalListViewHolder practicalListViewHolder = new PracticalListViewHolder(view);
        return practicalListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticalListViewHolder holder, int position) {
        Practical practical = practicals.get(position);

        holder.practicalTitleView.setText(practical.getTitle());
        holder.viewPracticalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display the detail of the editing for student
                Intent intent = new Intent(view.getContext(), EditEntity.class);
                intent.putExtra("Practical", practical);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return practicals.size();
    }
}
