package com.firebase.samples.logindemo.models;

/**
 * Created by arms on 6/14/16.
 */
public class Users {
    private String uid;
    private String displayName;
    private String profileImageURL;
    private String email;

    public Users(String uid, String displayName, String profileImageURL, String email) {
        this.uid = uid;
        this.displayName = displayName;
        this.profileImageURL = profileImageURL;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Users() {
    }
//    //    private double[] location;
//    private double location0;
//    private double location1;
//    private String age;
//    private String gender;
//    private String aboutme;

}
