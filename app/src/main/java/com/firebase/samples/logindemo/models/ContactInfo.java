package com.firebase.samples.logindemo.models;

import java.io.Serializable;

/**
 * Created by arms on 7/11/16.
 */
public class ContactInfo implements Serializable {

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

    public boolean isRoommateContact() {
        return roommateContact;
    }

    public void setRoommateContact(boolean roommateContact) {
        this.roommateContact = roommateContact;
    }

    public boolean isBrokerContact() {
        return brokerContact;
    }

    public void setBrokerContact(boolean brokerContact) {
        this.brokerContact = brokerContact;
    }

    public boolean isOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(boolean ownerContact) {
        this.ownerContact = ownerContact;
    }


    public boolean isPreferedPhoneCall() {
        return preferedPhoneCall;
    }

    public void setPreferedPhoneCall(boolean preferedPhoneCall) {
        this.preferedPhoneCall = preferedPhoneCall;
    }

    public boolean isPreferedPhoneText() {
        return preferedPhoneText;
    }

    public void setPreferedPhoneText(boolean preferedPhoneText) {
        this.preferedPhoneText = preferedPhoneText;
    }

    public boolean isPreferedEmail() {
        return preferedEmail;
    }

    public void setPreferedEmail(boolean preferedEmail) {
        this.preferedEmail = preferedEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
