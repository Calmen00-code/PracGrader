package com.calmen.pracgrader.ui.view_list.list_recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Instructor;
import com.calmen.pracgrader.models.Student;
import com.calmen.pracgrader.models.User;

import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListViewHolder> {
    ArrayList<User> users;
    Activity activity;

    public ListRecyclerAdapter(Activity inActivity, ArrayList<User> inUsers) {
        this.activity = inActivity;
        this.users = inUsers;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_user_list_row, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        User user = users.get(position);

        holder.usernameView.setText(user.getUsername());
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user instanceof Instructor) {

                } else if (user instanceof Student) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
