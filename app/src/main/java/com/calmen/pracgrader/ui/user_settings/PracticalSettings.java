package com.calmen.pracgrader.ui.user_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calmen.pracgrader.R;

public class PracticalSettings extends AppCompatActivity {
    private Button addBtn, delBtn, editBtn;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entity_settings);

        titleView = findViewById(R.id.entitySettingsView);
        addBtn = findViewById(R.id.addEntityBtn);
        delBtn = findViewById(R.id.deleteEntityBtn);
        editBtn = findViewById(R.id.editEntityBtn);

        titleView.setText("Practical's Settings");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticalSettings.this,
                        PracticalRegistration.class);
                startActivity(intent);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticalSettings.this,
                        EntityQuery.class);
                intent.putExtra("Operation", EntityQuery.EDIT_OPERATION);
                intent.putExtra("EntityType", EntityQuery.ENTITY_TYPE_PRACTICAL);
                startActivity(intent);
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticalSettings.this,
                        EntityQuery.class);
                intent.putExtra("Operation", EntityQuery.DELETE_OPERATION);
                intent.putExtra("EntityType", EntityQuery.ENTITY_TYPE_PRACTICAL);
                startActivity(intent);
                finish();
            }
        });
    }
}