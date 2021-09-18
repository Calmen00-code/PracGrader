package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;
import com.calmen.pracgrader.shared.EditEntity;
import com.calmen.pracgrader.shared.EditStudentPractical;

import java.util.ArrayList;

public class InsturctorListRecyclerAdapter extends RecyclerView.Adapter<InstructorListViewHolder> {
    ArrayList<User> users;
    Activity activity;

    public InsturctorListRecyclerAdapter(Activity inActivity, ArrayList<User> inUsers) {
        this.activity = inActivity;
        this.users = inUsers;
    }

    @NonNull
    @Override
    public InstructorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_instructor_list_row, parent, false);
        InstructorListViewHolder listViewHolder = new InstructorListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorListViewHolder holder, int position) {
        User user = users.get(position);

        holder.usernameView.setText(user.getUsername());
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user instanceof Instructor) {
                    // display the detail of the editing for student
                    Intent intent = new Intent(view.getContext(), EditEntity.class);
                    intent.putExtra("User", user);
                    view.getContext().startActivity(intent);
                    ((Activity) view.getContext()).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
