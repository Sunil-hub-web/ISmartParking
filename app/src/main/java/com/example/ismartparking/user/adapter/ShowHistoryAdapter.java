package com.example.ismartparking.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismartparking.R;
import com.example.ismartparking.user.model.ShowHistory_ModelClass;

import java.util.ArrayList;

public class ShowHistoryAdapter extends RecyclerView.Adapter<ShowHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<ShowHistory_ModelClass> showHistory;

    public ShowHistoryAdapter(ArrayList<ShowHistory_ModelClass> showHistory, FragmentActivity activity) {

        this.context = activity;
        this.showHistory = showHistory;
    }

    @NonNull
    @Override
    public ShowHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datacollection,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHistoryAdapter.ViewHolder holder, int position) {

        ShowHistory_ModelClass show_history = showHistory.get(position);

        holder.vid.setText(show_history.getvId());
        holder.vnumber.setText(show_history.getvNumber());
        holder.vdateTime.setText(show_history.getvDateTime());
        holder.vAdvancep.setText(show_history.getvAdvanceAmount());

        holder.image_v.setImageResource(show_history.getvImage());
    }

    @Override
    public int getItemCount() {
        return showHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vid,vnumber,vdateTime,vAdvancep;
        ImageView image_v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_v = itemView.findViewById(R.id.image_v);
            vid = itemView.findViewById(R.id.vid);
            vnumber = itemView.findViewById(R.id.vnumber);
            vdateTime = itemView.findViewById(R.id.vdateTime);
            vAdvancep = itemView.findViewById(R.id.vAdvancep);
        }
    }
}
