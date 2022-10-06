package com.example.ismartparking.user.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ismartparking.R;
import com.lucem.anb.characterscanner.ScannerListener;
import com.lucem.anb.characterscanner.ScannerView;

public class EntryLayout extends Fragment {

    EditText vechicleNumber;
    ScannerView scanner;
    Dialog dialog;
    LinearLayout lin_Scanner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entrylayout,container,false);

        vechicleNumber = view.findViewById(R.id.vechicleNumber);
        lin_Scanner = view.findViewById(R.id.lin_Scanner);

        lin_Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.scanneractivity);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(lp);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                Button btncapture = dialog.findViewById(R.id.btncapture);
                scanner = dialog.findViewById(R.id.scanner);

                scanner.setOnDetectedListener(getActivity(), new ScannerListener() {
                    @Override
                    public void onDetected(String s) {

                        vechicleNumber.setText(s);

                    }

                    @Override
                    public void onStateChanged(String s, int i) {

                        vechicleNumber.setText(s);

                    }
                });

                btncapture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        scanner.isScanning();
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });

        return view;
    }
}
