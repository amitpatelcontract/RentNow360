package com.firebase.samples.logindemo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arms on 7/10/16.
 */
public class BasicInfo implements Serializable{

    private int beds;
    private String bath;
    private int squareFeet;
    private int rent;
    private int deposit;
    private boolean fee;
    private boolean noFee;
    private boolean owner;
    private boolean roommate;
    private boolean broker;
    private String availableDate;
    private ArrayList<String> leaseTime;


    public ArrayList<String> getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(ArrayList<String> leaseTime) {
        this.leaseTime = leaseTime;
    }

    public boolean isRoommate() {
        return roommate;
    }

    public void setRoommate(boolean roommate) {
        this.roommate = roommate;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean isFee() {
        return fee;
    }

    public void setFee(boolean fee) {
        this.fee = fee;
    }

    public boolean isNoFee() {
        return noFee;
    }

    public void setNoFee(boolean noFee) {
        this.noFee = noFee;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isBroker() {
        return broker;
    }

    public void setBroker(boolean broker) {
        this.broker = broker;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }



    public BasicInfo() {
    }
}
