package com.calmen.pracgrader.ui.feature_recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

import java.util.ArrayList;

public class FeatureRecyclerAdapter extends RecyclerView.Adapter<FeatureViewHolder> {
    ArrayList<String> features;

    public FeatureRecyclerAdapter(ArrayList<String> inFeatures) { this.features = inFeatures; }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.features_row, parent, false);
        FeatureViewHolder featureViewHolder = new FeatureViewHolder(view);
        return featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        String singleFeature = features.get(position);
        holder.featureView.setText(singleFeature);
        holder.selectFeatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display feature of user here
            }
        });

    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
