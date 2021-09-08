package com.calmen.pracgrader.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.calmen.pracgrader.R;

import java.io.Serializable;

public class Login extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }
}