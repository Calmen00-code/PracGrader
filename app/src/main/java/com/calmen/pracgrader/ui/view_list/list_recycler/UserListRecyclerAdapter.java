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

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    ArrayList<User> users;
    Activity activity;
    boolean isGrade;

    public UserListRecyclerAdapter(Activity inActivity, ArrayList<User> inUsers, boolean inGrade) {
        this.activity = inActivity;
        this.users = inUsers;
        this.isGrade = inGrade;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_user_list_row, parent, false);
        UserListViewHolder listViewHolder = new UserListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        User user = users.get(position);

        holder.usernameView.setText(user.getUsername());
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user instanceof Instructor) {

                } else if (user instanceof Student) {
                    Intent intent;
                    if (isGrade) {
                        intent = new Intent(view.getContext(), EditStudentPractical.class);
                        EditEntity.user = user; // setting user directly
                    } else {
                        // display the detail of the editing for student
                        intent = new Intent(view.getContext(), EditEntity.class);
                        intent.putExtra("User", user);
                    }
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
