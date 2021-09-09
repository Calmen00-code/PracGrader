package com.calmen.pracgrader.ui.feature_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class FeatureViewHolder extends RecyclerView.ViewHolder {
    public TextView featureView;
    public Button selectFeatureBtn;

    public FeatureViewHolder(@NonNull View itemView) {
        super(itemView);
        featureView = itemView.findViewById(R.id.featuresView);
        selectFeatureBtn = itemView.findViewById(R.id.selectFeatureBtn);
    }
}
