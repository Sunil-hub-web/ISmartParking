package com.example.ismartparking.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ismartparking.R;
import com.example.ismartparking.user.adapter.ShowBluetoohDeviceAdapter;
import com.example.ismartparking.user.bluetooth.BluetoothController;
import com.example.ismartparking.user.model.ShowBluetooh_ModelClass;
import com.example.ismartparking.user.view.DeviceRecyclerViewAdapter;
import com.example.ismartparking.user.view.ListInteractionListener;
import com.example.ismartparking.user.view.RecyclerViewProgressEmptySupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Set;

public class ShowBluetoohDevice extends AppCompatActivity implements ListInteractionListener<BluetoothDevice> {

    ShowBluetoohDeviceAdapter showBluetoohDeviceAdapter;
    BluetoothAdapter bluetoothAdapter;
    BluetoothManager bluetoothManager;
    Set<BluetoothDevice> pairedDevices;
    public static final int REQUEST_ENABLE_BT = 0;
    ArrayList<ShowBluetooh_ModelClass> showDevices = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerShowDevices;

    private static final String TAG = "MainActivity";
    private BluetoothController bluetooth;
    private ProgressDialog bondingProgressDialog;
    private DeviceRecyclerViewAdapter recyclerViewAdapter;
    Button btn_Scan;
    private View emptyView;
    private ProgressBar progressView;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bluetooh_device);

        recyclerShowDevices = findViewById(R.id.recyclerShowDevices);
        btn_Scan = findViewById(R.id.btn_Scan);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!bluetoothAdapter.isEnabled()) {

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();

        } else {

            getPairedDevices();

        }

        // Sets up the RecyclerView.
        this.recyclerViewAdapter = new DeviceRecyclerViewAdapter(this);

        // [#11] Ensures that the Bluetooth is available on this device before proceeding.
        boolean hasBluetooth = getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
        if (!hasBluetooth) {
            AlertDialog dialog = new AlertDialog.Builder(ShowBluetoohDevice.this).create();
            dialog.setTitle(getString(R.string.bluetooth_not_available_title));
            dialog.setMessage(getString(R.string.bluetooth_not_available_message));
            dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Closes the dialog and terminates the activity.
                            dialog.dismiss();
                            ShowBluetoohDevice.this.finish();
                        }
                    });
            dialog.setCancelable(false);
            dialog.show();
        }

        // Sets up the bluetooth controller.
        this.bluetooth = new BluetoothController(this, BluetoothAdapter.getDefaultAdapter(), recyclerViewAdapter);

        btn_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!bluetooth.isBluetoothEnabled()) {
                    Snackbar.make(view, R.string.enabling_bluetooth, Snackbar.LENGTH_SHORT).show();
                    bluetooth.turnOnBluetoothAndScheduleDiscovery();
                } else {
                    //Prevents the user from spamming the button and thus glitching the UI.
                    if (!bluetooth.isDiscovering()) {
                        // Starts the discovery.
                        Snackbar.make(view, R.string.device_discovery_started, Snackbar.LENGTH_SHORT).show();
                        bluetooth.startDiscovery();
                    } else {
                        Snackbar.make(view, R.string.device_discovery_stopped, Snackbar.LENGTH_SHORT).show();
                        bluetooth.cancelDiscovery();
                    }
                }

                recyclerShowDevices.setLayoutManager(new LinearLayoutManager(ShowBluetoohDevice.this));
                recyclerShowDevices.setAdapter(recyclerViewAdapter);
            }
        });

    }

    public void showTostMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

                linearLayoutManager = new LinearLayoutManager(ShowBluetoohDevice.this,LinearLayoutManager.VERTICAL,false);
                showBluetoohDeviceAdapter = new ShowBluetoohDeviceAdapter(showDevices,ShowBluetoohDevice.this);
                recyclerShowDevices.setLayoutManager(linearLayoutManager);
                recyclerShowDevices.setHasFixedSize(true);
                recyclerShowDevices.setAdapter(showBluetoohDeviceAdapter);
            }

        } else {
            showTostMessage("Trun On Bluetooh to get Paired Devices");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Creates the about popup.
     */
    private void showAbout() {
        // Inflate the about message contents
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(BluetoothDevice device) {
        Log.d(TAG, "Item clicked : " + BluetoothController.deviceToString(device));
        if (bluetooth.isAlreadyPaired(device)) {
            Log.d(TAG, "Device already paired!");
            Toast.makeText(this, R.string.device_already_paired, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Device not paired. Pairing.");
            boolean outcome = bluetooth.pair(device);

            // Prints a message to the user.
            String deviceName = BluetoothController.getDeviceName(device);
            if (outcome) {
                // The pairing has started, shows a progress dialog.
                Log.d(TAG, "Showing pairing dialog");
                bondingProgressDialog = ProgressDialog.show(this, "", "Pairing with device " + deviceName + "...", true, false);
            } else {
                Log.d(TAG, "Error while pairing with device " + deviceName + "!");
                Toast.makeText(this, "Error while pairing with device " + deviceName + "!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void endLoadingWithDialog(boolean error, BluetoothDevice device) {
        if (this.bondingProgressDialog != null) {
            //View view = findViewById(R.id.main_content);
            String message;
            String deviceName = BluetoothController.getDeviceName(device);

            // Gets the message to print.
            if (error) {
                message = "Failed pairing with device " + deviceName + "!";
            } else {
                message = "Succesfully paired with device " + deviceName + "!";
            }

            // Dismisses the progress dialog and prints a message to the user.
            this.bondingProgressDialog.dismiss();
           // Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

            // Cleans up state.
            this.bondingProgressDialog = null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        bluetooth.close();
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        // Stops the discovery.
        if (this.bluetooth != null) {
            this.bluetooth.cancelDiscovery();
        }
        // Cleans the view.
        if (this.recyclerViewAdapter != null) {
            this.recyclerViewAdapter.cleanView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();
        // Stoops the discovery.
        if (this.bluetooth != null) {
            this.bluetooth.cancelDiscovery();
        }
    }

    public void setProgressView(ProgressBar progressView) {
        this.progressView = progressView;
    }

    /**
     * Shows the progress view.
     */
    public void startLoading() {
        // Hides the empty view.
        if (this.emptyView != null) {
            this.emptyView.setVisibility(View.GONE);
        }
        // Shows the progress bar.
        if (this.progressView != null) {
            this.progressView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void endLoading(boolean partialResults) {

        // Hides the progress bar.
        if (this.progressView != null) {
            this.progressView.setVisibility(View.GONE);
        }

    }
}