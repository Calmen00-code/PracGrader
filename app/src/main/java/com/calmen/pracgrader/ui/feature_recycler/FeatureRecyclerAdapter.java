package com.calmen.pracgrader.ui.feature_recycler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.ui.entity_settings.GradingSettings;
import com.calmen.pracgrader.ui.entity_settings.InstructorSettings;
import com.calmen.pracgrader.ui.Login;
import com.calmen.pracgrader.models.Admin;
import com.calmen.pracgrader.ui.entity_settings.PracticalSettings;
import com.calmen.pracgrader.ui.entity_settings.SearchStudent;
import com.calmen.pracgrader.ui.entity_settings.StudentSettings;
import com.calmen.pracgrader.ui.view_list.ViewInstructorList;
import com.calmen.pracgrader.ui.view_list.ViewPracticalList;
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
                    Intent intent;
                    if (position == Admin.INSTRUCTOR_SETTINGS) {
                        intent = new Intent(view.getContext(), InstructorSettings.class);
                    } else if (position == Admin.STUDENT_SETTINGS) {
                        intent = new Intent(view.getContext(), StudentSettings.class);
                    } else if (position == Admin.PRACTICAL_SETTINGS) {
                        intent = new Intent(view.getContext(), PracticalSettings.class);
                    } else if (position == Admin.MARKING_SETTINGS) {
                        intent = new Intent(view.getContext(), GradingSettings.class);
                    } else if (position == Admin.VIEW_INSTRUCTOR_LIST) {
                        intent = new Intent(view.getContext(), ViewInstructorList.class);
                    } else if (position == Admin.VIEW_PRACTICAL_LIST) {
                        intent = new Intent(view.getContext(), ViewPracticalList.class);
                    } else if (position == Admin.VIEW_STUDENT_LIST) {
                        intent = new Intent(view.getContext(), ViewStudentList.class);
                    } else {
                        intent = new Intent(view.getContext(), SearchStudent.class);
                    }
                    view.getContext().startActivity(intent);
                } else if (userRole.equals(Login.INSTRUCTOR)) {
                    Intent intent;
                    if (position == Instructor.STUDENT_SETTINGS) {
                        intent = new Intent(view.getContext(), StudentSettings.class);
                    } else if (position == Instructor.MARKING_SETTINGS) {
                        intent = new Intent(view.getContext(), GradingSettings.class);
                    } else if (position == Instructor.VIEW_STUDENT_LIST) {
                        intent = new Intent(view.getContext(), ViewStudentList.class);
                    } else {
                        intent = new Intent(view.getContext(), SearchStudent.class);
                    }
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
