package com.example.ismartparking.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.fragment.EntryLayout;
import com.example.ismartparking.user.fragment.ExitLayout;

public class DeshBoard extends AppCompatActivity {

    TextView text_Entry,text_Exit;
    ImageView imagePrint,imageHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desh_board);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text_Exit = findViewById(R.id.text_Exit);
        text_Entry = findViewById(R.id.text_Entry);
        imagePrint = findViewById(R.id.imagePrint);
        imageHistory = findViewById(R.id.imageHistory);

        EntryLayout entryLayout = new EntryLayout();
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framLayourt, entryLayout);
        transaction.addToBackStack(null);
        transaction.commit();

        text_Entry.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                text_Entry.setBackgroundResource(R.color.background1);
                text_Exit.setBackgroundResource(R.color.background2);

                EntryLayout entryLayout = new EntryLayout();
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framLayourt, entryLayout);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text_Exit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                text_Entry.setBackgroundResource(R.color.background2);
                text_Exit.setBackgroundResource(R.color.background1);

                //textview.setBackground(ContextCompat.getColor(this,R.color.yourcolor));

                //textview.setBackground(ContextCompat.getColor(getActivity(),R.color.yourcolor));

                ExitLayout exitLayout = new ExitLayout();
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framLayourt, exitLayout);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        imagePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DeshBoard.this,ShowBluetoohDevice.class);
                startActivity(intent);
            }
        });

        imageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DeshBoard.this,BookingHistory.class);
                startActivity(intent);

            }
        });

    }
}