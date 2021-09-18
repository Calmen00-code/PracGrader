package com.calmen.pracgrader.ui.entity_settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.models.Country;
import com.calmen.pracgrader.ui.student_image_recycler.StudentImageRecyclerAdapter;

public class SelectStudentImage extends AppCompatActivity {
    public static final int[] STUDENT_IMAGES = {
            R.drawable.avatar_boy_one, R.drawable.avatar_boy_two,
            R.drawable.avatar_girl_one, R.drawable.avatar_girl_two
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_student_image);

        String name = getIntent().getStringExtra("Name");
        String username = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        String pin = getIntent().getStringExtra("Pin");
        Country country = (Country) getIntent().getSerializableExtra("Country");

        RecyclerView rv = findViewById(R.id.studentImageRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        StudentImageRecyclerAdapter theAdapter = new StudentImageRecyclerAdapter(this,
                STUDENT_IMAGES, name, username, email, pin, country);
        rv.setAdapter(theAdapter);
    }
}