package com.example.ismartparking.user.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.ShowBluetoohDevice;
import com.example.ismartparking.user.adapter.ShowBluetoohDeviceAdapter;
import com.example.ismartparking.user.bluetooth.BluetoothController;
import com.example.ismartparking.user.model.ShowBluetooh_ModelClass;
import com.example.ismartparking.user.view.DeviceRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Set;

public class ShowPairedDevices extends Fragment {


    ShowBluetoohDeviceAdapter showBluetoohDeviceAdapter;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    public static final int REQUEST_ENABLE_BT = 0;
    ArrayList<ShowBluetooh_ModelClass> showDevices = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerShowDevices;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.showpairedevices,container,false);

        recyclerShowDevices = view.findViewById(R.id.recyclerShowDevices);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!bluetoothAdapter.isEnabled()) {

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);

        }else{

            getPairedDevices();

        }

        return view;
    }

    @SuppressLint("MissingPermission")
    public void getPairedDevices() {


        if (bluetoothAdapter.isEnabled()) {

            bluetoothAdapter.enable();
            pairedDevices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice bd : pairedDevices) {

                ShowBluetooh_ModelClass showBluetooh_modelClass = new ShowBluetooh_ModelClass(
                        bd.getName(),bd.getAddress(),"Paired"
                );
                showDevices.add(showBluetooh_modelClass);

                linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                showBluetoohDeviceAdapter = new ShowBluetoohDeviceAdapter(showDevices, getActivity());
                recyclerShowDevices.setLayoutManager(linearLayoutManager);
                recyclerShowDevices.setHasFixedSize(true);
                recyclerShowDevices.setAdapter(showBluetoohDeviceAdapter);
            }

        } else {
            showTostMessage("Trun On Bluetooh to get Paired Devices");
        }
    }

    public void showTostMessage(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_ENABLE_BT:
                if (requestCode == REQUEST_ENABLE_BT) {
                    showTostMessage("Bluetooh is Already On");

                } else {

                    showTostMessage("Turning On Bluetooh");
                }
                break;
        }
    }
}
