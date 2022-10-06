package com.example.ismartparking.user.model;

public class ShowHistory_ModelClass {

    String vId,vNumber,vDateTime,vAdvanceAmount;
    int vImage;

    public ShowHistory_ModelClass(String vId, String vNumber, String vDateTime, String vAdvanceAmount, int vImage) {
        this.vId = vId;
        this.vNumber = vNumber;
        this.vDateTime = vDateTime;
        this.vAdvanceAmount = vAdvanceAmount;
        this.vImage = vImage;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public String getvNumber() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber = vNumber;
    }

    public String getvDateTime() {
        return vDateTime;
    }

    public void setvDateTime(String vDateTime) {
        this.vDateTime = vDateTime;
    }

    public String getvAdvanceAmount() {
        return vAdvanceAmount;
    }

    public void setvAdvanceAmount(String vAdvanceAmount) {
        this.vAdvanceAmount = vAdvanceAmount;
    }

    public int getvImage() {
        return vImage;
    }

    public void setvImage(int vImage) {
        this.vImage = vImage;
    }
}
