package com.example.ismartparking.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.adapter.ShowHistoryAdapter;
import com.example.ismartparking.user.model.ShowHistory_ModelClass;

import java.util.ArrayList;

public class ExitDataLayout extends Fragment {

    ShowHistoryAdapter showHistoryAdapter;
    ArrayList<ShowHistory_ModelClass> showHistory = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerEntryDataCollection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.exitdatalayout,container,false);


        return view;
    }
}
