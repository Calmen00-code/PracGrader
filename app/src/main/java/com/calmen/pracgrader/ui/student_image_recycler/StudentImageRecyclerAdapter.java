package com.calmen.pracgrader.ui.student_image_recycler;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.shared.ConfirmOperation;
import com.calmen.pracgrader.shared.ConfirmRegistration;

public class StudentImageRecyclerAdapter extends RecyclerView.Adapter<StudentImageViewHolder> {
    Activity activity;
    int[] studentImages;
    String name;
    String username;
    String email;
    String pin;
    Country country;

    public StudentImageRecyclerAdapter(Activity inActivity, int[] inStudentImage, String inName,
                                       String inUsername, String inEmail, String inPin, Country inCountry) {
        this.activity = inActivity;
        this.studentImages = inStudentImage;
        this.name = inName;
        this.username = inUsername;
        this.email = inEmail;
        this.pin = inPin;
        this.country = inCountry;
    }

    @NonNull
    @Override
    public StudentImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_image_row, parent, false);
        StudentImageViewHolder studentImageViewHolder = new StudentImageViewHolder(view);
        return studentImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentImageViewHolder holder, int position) {
        int image = studentImages[position];

        holder.studentImg.setImageResource(image);
        holder.selImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConfirmRegistration.class);
                intent.putExtra("Name", name);
                intent.putExtra("Username", username);
                intent.putExtra("Email", email);
                intent.putExtra("Pin", pin);
                intent.putExtra("Country", country);
                intent.putExtra("StudentImage", image);
                System.out.println("PIN in StudentImageAdapter: " + pin);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentImages.length;
    }
}
