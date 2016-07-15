package com.firebase.samples.logindemo.models;

import java.io.Serializable;

/**
 * Created by arms on 7/10/16.
 */
public class Property implements Serializable {

    private double[] location;
    private String propertyType;
    private PropertyImages propertyImages;
    private PropertyVideos propertyVideos;
    // basic Info
    private int beds;
    private float bath;
    private int squareFeet;
    private int rent;
    private int deposit;
    private boolean fee;
    private boolean noFee;
    private boolean owner;
    private boolean roommate;
    private boolean broker;
    private String availableDate;
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
    private String parkingType;

    // Contact info
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean ownerContact;
    private boolean brokerContact;
    private boolean roommateContact;

    private boolean preferedEmail;
    private boolean preferedPhoneText;
    private boolean preferedPhoneCall;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(boolean ownerContact) {
        this.ownerContact = ownerContact;
    }

    public boolean isBrokerContact() {
        return brokerContact;
    }

    public void setBrokerContact(boolean brokerContact) {
        this.brokerContact = brokerContact;
    }

    public boolean isRoommateContact() {
        return roommateContact;
    }

    public void setRoommateContact(boolean roommateContact) {
        this.roommateContact = roommateContact;
    }

    public boolean isPreferedEmail() {
        return preferedEmail;
    }

    public void setPreferedEmail(boolean preferedEmail) {
        this.preferedEmail = preferedEmail;
    }

    public boolean isPreferedPhoneText() {
        return preferedPhoneText;
    }

    public void setPreferedPhoneText(boolean preferedPhoneText) {
        this.preferedPhoneText = preferedPhoneText;
    }

    public boolean isPreferedPhoneCall() {
        return preferedPhoneCall;
    }

    public void setPreferedPhoneCall(boolean preferedPhoneCall) {
        this.preferedPhoneCall = preferedPhoneCall;
    }


    public Property() {
    }

    public PropertyVideos getPropertyVideos() {
        return propertyVideos;
    }

    public double[] getLocation() {
        return location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public PropertyImages getPropertyImages() {
        return propertyImages;
    }

    public int getBeds() {
        return beds;
    }

    public float getBath() {
        return bath;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getRent() {
        return rent;
    }

    public int getDeposit() {
        return deposit;
    }

    public boolean isFee() {
        return fee;
    }

    public boolean isNoFee() {
        return noFee;
    }

    public boolean isOwner() {
        return owner;
    }

    public boolean isRoommate() {
        return roommate;
    }

    public boolean isBroker() {
        return broker;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNoPetAllowed() {
        return noPetAllowed;
    }

    public boolean isDogsAllowed() {
        return dogsAllowed;
    }

    public boolean isCatsAllowed() {
        return catsAllowed;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public boolean isElevator() {
        return elevator;
    }

    public boolean isDoorman() {
        return doorman;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public boolean isFitnessGymcenter() {
        return fitnessGymcenter;
    }

    public boolean isSwimmingpool() {
        return swimmingpool;
    }

    public String getLaundryType() {
        return laundryType;
    }

    public String getParkingType() {
        return parkingType;
    }


}
