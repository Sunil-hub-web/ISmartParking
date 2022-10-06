package com.example.ismartparking.user.model;

public class ShowBluetooh_ModelClass {

    String bluetoohName,bluetoohAddress,bluetoohpaired;

    public ShowBluetooh_ModelClass(String bluetoohName, String bluetoohAddress, String bluetoohpaired) {
        this.bluetoohName = bluetoohName;
        this.bluetoohAddress = bluetoohAddress;
        this.bluetoohpaired = bluetoohpaired;
    }

    public String getBluetoohName() {
        return bluetoohName;
    }

    public void setBluetoohName(String bluetoohName) {
        this.bluetoohName = bluetoohName;
    }

    public String getBluetoohAddress() {
        return bluetoohAddress;
    }

    public void setBluetoohAddress(String bluetoohAddress) {
        this.bluetoohAddress = bluetoohAddress;
    }

    public String getBluetoohpaired() {
        return bluetoohpaired;
    }

    public void setBluetoohpaired(String bluetoohpaired) {
        this.bluetoohpaired = bluetoohpaired;
    }
}
