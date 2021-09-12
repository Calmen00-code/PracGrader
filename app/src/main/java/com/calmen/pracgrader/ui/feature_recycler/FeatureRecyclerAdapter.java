package com.calmen.pracgrader.ui.feature_recycler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.ui.user_settings.InstructorSettings;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.ui.user_settings.StudentSettings;
import com.calmen.pracgrader.ui.view_list.ViewInstructorList;
import com.calmen.pracgrader.ui.view_list.ViewStudentList;

import java.util.ArrayList;

public class FeatureRecyclerAdapter extends RecyclerView.Adapter<FeatureViewHolder> {
    ArrayList<String> features;
    String userRole;

    public FeatureRecyclerAdapter(ArrayList<String> inFeatures, String inUserRole) {
        this.features = inFeatures;
        this.userRole = inUserRole;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.features_row, parent, false);
        FeatureViewHolder featureViewHolder = new FeatureViewHolder(view);
        return featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String singleFeature = features.get(position);
        holder.featureView.setText(singleFeature);
        holder.selectFeatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userRole.equals(Login.ADMIN)) {
                    if (position == Admin.INSTRUCTOR_SETTINGS) {
                        Intent intent = new Intent(view.getContext(), InstructorSettings.class);
                        view.getContext().startActivity(intent);
                    } else if (position == Admin.STUDENT_SETTINGS) {
                        Intent intent = new Intent(view.getContext(), StudentSettings.class);
                        view.getContext().startActivity(intent);
                    } else if (position == Admin.VIEW_INSTRUCTOR_LIST) {
                        Intent intent = new Intent(view.getContext(), ViewInstructorList.class);
                        view.getContext().startActivity(intent);
                    } else if (position == Admin.VIEW_STUDENT_LIST) {
                        Intent intent = new Intent(view.getContext(), ViewStudentList.class);
                        view.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
