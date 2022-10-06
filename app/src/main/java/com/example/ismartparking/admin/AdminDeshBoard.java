package com.example.ismartparking.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ismartparking.R;

public class AdminDeshBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_desh_board);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}