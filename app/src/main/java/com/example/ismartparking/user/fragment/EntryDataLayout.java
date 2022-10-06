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

public class EntryDataLayout extends Fragment {

    ShowHistoryAdapter showHistoryAdapter;
    ArrayList<ShowHistory_ModelClass> showHistory = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerEntryDataCollection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entrydatalayout,container,false);

        recyclerEntryDataCollection = view.findViewById(R.id.recyclerEntryDataCollection);

        showHistory.add(new ShowHistory_ModelClass("123456789","abcd123u45","28.07.2022 4:30 Pm","Advance : 20",R.drawable.sports_car));
        showHistory.add(new ShowHistory_ModelClass("123456789","abcd123u45","29.07.2022 3:30 Pm","Advance : 30",R.drawable.bus));
        showHistory.add(new ShowHistory_ModelClass("123456789","abcd123u45","21.07.2022 2:30 Pm","Advance : 10",R.drawable.minibus));
        showHistory.add(new ShowHistory_ModelClass("123456789","abcd123u45","22.07.2022 5:30 Pm","Advance : 40",R.drawable.cargo_truck));
        showHistory.add(new ShowHistory_ModelClass("123456789","abcd123u45","23.07.2022 5:30 Pm","Advance : 50",R.drawable.motorcycle));

        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        showHistoryAdapter = new ShowHistoryAdapter(showHistory,getActivity());
        recyclerEntryDataCollection.setLayoutManager(linearLayoutManager);
        recyclerEntryDataCollection.setHasFixedSize(true);
        recyclerEntryDataCollection.setAdapter(showHistoryAdapter);

        return view;
    }
}
