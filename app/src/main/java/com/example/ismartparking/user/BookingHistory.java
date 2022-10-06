package com.example.ismartparking.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.adapter.ShowHistoryAdapter;
import com.example.ismartparking.user.fragment.EntryDataLayout;
import com.example.ismartparking.user.fragment.EntryLayout;
import com.example.ismartparking.user.fragment.ExitDataLayout;
import com.example.ismartparking.user.fragment.TotalCollectionLayout;
import com.example.ismartparking.user.model.ShowHistory_ModelClass;

import java.util.ArrayList;

public class BookingHistory extends AppCompatActivity {

    TextView text_Entry,text_Exit,text_Collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text_Collection = findViewById(R.id.text_Collection);
        text_Exit = findViewById(R.id.text_Exit);
        text_Entry = findViewById(R.id.text_Entry);

        EntryDataLayout entryDataLayout = new EntryDataLayout();
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram_data, entryDataLayout);
        transaction.addToBackStack(null);
        transaction.commit();

        text_Collection.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                text_Collection.setBackgroundResource(R.color.background1);
                text_Exit.setBackgroundResource(R.color.background2);
                text_Entry.setBackgroundResource(R.color.background2);

                TotalCollectionLayout totalCollectionLayout = new TotalCollectionLayout();
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fram_data, totalCollectionLayout);
                transaction.addToBackStack(null);
                transaction.commit();

                //textview.setBackground(ContextCompat.getColor(this,R.color.yourcolor));

                //textview.setBackground(ContextCompat.getColor(getActivity(),R.color.yourcolor));

            }
        });

        text_Entry.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                text_Collection.setBackgroundResource(R.color.background2);
                text_Exit.setBackgroundResource(R.color.background2);
                text_Entry.setBackgroundResource(R.color.background1);

                EntryDataLayout entryDataLayout = new EntryDataLayout();
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fram_data, entryDataLayout);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        text_Exit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                text_Collection.setBackgroundResource(R.color.background2);
                text_Exit.setBackgroundResource(R.color.background1);
                text_Entry.setBackgroundResource(R.color.background2);

                ExitDataLayout exitDataLayout = new ExitDataLayout();
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fram_data, exitDataLayout);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }
}