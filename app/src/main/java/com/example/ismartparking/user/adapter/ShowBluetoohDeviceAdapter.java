package com.example.ismartparking.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.ShowBluetoohDevice;
import com.example.ismartparking.user.model.ShowBluetooh_ModelClass;

import java.util.ArrayList;

public class ShowBluetoohDeviceAdapter extends RecyclerView.Adapter<ShowBluetoohDeviceAdapter.ViewHolder> {

    Context context;
    ArrayList<ShowBluetooh_ModelClass> showDevices;


    public ShowBluetoohDeviceAdapter(ArrayList<ShowBluetooh_ModelClass> showDevices, Context context) {

        this.context = context;
        this.showDevices = showDevices;
    }

    @NonNull
    @Override
    public ShowBluetoohDeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showbluetooth,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBluetoohDeviceAdapter.ViewHolder holder, int position) {

        ShowBluetooh_ModelClass show_devices = showDevices.get(position);

        holder.bluetoohName.setText(show_devices.getBluetoohName());
        holder.bluetoohAddress.setText(show_devices.getBluetoohAddress());

    }

    @Override
    public int getItemCount() {
        return showDevices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView bluetoohName,bluetoohAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bluetoohAddress = itemView.findViewById(R.id.bluetoohAddress);
            bluetoohName = itemView.findViewById(R.id.bluetoohName);

        }
    }
}
