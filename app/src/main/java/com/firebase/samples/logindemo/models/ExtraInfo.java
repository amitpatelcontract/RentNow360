package com.firebase.samples.logindemo.models;

import java.io.Serializable;

/**
 * Created by arms on 7/11/16.
 */
public class ExtraInfo implements Serializable {

    // extra Info
    private String description;
    private boolean noPetAllowed;
    private boolean dogsAllowed;
    private boolean catsAllowed;
    private boolean furnished;
    private boolean elevator;
    private boolean doorman;
    private boolean wheelchairAccess;
    private boolean fitnessGymcenter;
    private boolean swimmingpool;
    private String laundryType;

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNoPetAllowed() {
        return noPetAllowed;
    }

    public void setNoPetAllowed(boolean noPetAllowed) {
        this.noPetAllowed = noPetAllowed;
    }

    public boolean isDogsAllowed() {
        return dogsAllowed;
    }

    public void setDogsAllowed(boolean dogsAllowed) {
        this.dogsAllowed = dogsAllowed;
    }

    public boolean isCatsAllowed() {
        return catsAllowed;
    }

    public void setCatsAllowed(boolean catsAllowed) {
        this.catsAllowed = catsAllowed;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public boolean isDoorman() {
        return doorman;
    }

    public void setDoorman(boolean doorman) {
        this.doorman = doorman;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    public boolean isFitnessGymcenter() {
        return fitnessGymcenter;
    }

    public void setFitnessGymcenter(boolean fitnessGymcenter) {
        this.fitnessGymcenter = fitnessGymcenter;
    }

    public boolean isSwimmingpool() {
        return swimmingpool;
    }

    public void setSwimmingpool(boolean swimmingpool) {
        this.swimmingpool = swimmingpool;
    }

    public String getLaundryType() {
        return laundryType;
    }

    public void setLaundryType(String laundryType) {
        this.laundryType = laundryType;
    }

    private String parkingType;
}
