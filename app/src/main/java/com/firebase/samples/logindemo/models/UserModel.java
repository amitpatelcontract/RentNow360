package com.firebase.samples.logindemo.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arms on 5/23/16.
 */
@IgnoreExtraProperties
public class UserModel implements Serializable {
    private String uid;
    private String displayName;
    private String profileImageURL;
    private String email;
//    private double[] location;
    private double location0;
    private double location1;
    private String age;
    private String gender;
    private String aboutme;


    public UserModel(String uid, String displayName, String profileImageURL, String email, double location0, double location1, String age, String gender, String aboutme) {
        this.uid = uid;
        this.displayName = displayName;
        this.profileImageURL = profileImageURL;
        this.email = email;

//        this.location = location;
        this.location0 = location0;
        this.location1 = location1;
        this.age = age;
        this.gender = gender;
        this.aboutme = aboutme;
    }

    //    public UserModel(String uid, String displayName, String profileImageURL, String email) {
//        this.uid = uid;
//        this.displayName = displayName;
//        this.profileImageURL = profileImageURL;
//        this.email = email;
//        this.location = location;
//
//    }
    public UserModel() {
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("displayName", displayName);
        result.put("profileImageURL", profileImageURL);
        result.put("email", email);
        result.put("location0", location0);
        result.put("location1", location1);
        // new
        result.put("age", age);
        result.put("gender", gender);
        result.put("aboutme", aboutme);
        return result;
    }

    @Exclude
    public Map<String, Object> toEditUserMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("displayName", displayName);
        result.put("profileImageURL", profileImageURL);
        result.put("email", email);
        result.put("location0", location0);
        result.put("location1", location1);
        // new
        result.put("age", age);
        result.put("gender", gender);
        result.put("aboutme", aboutme);



        return result;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public double getLocation0() {
        return location0;
    }

    public void setLocation0(double location0) {
        this.location0 = location0;
    }

    public double getLocation1() {
        return location1;
    }

    public void setLocation1(double location1) {
        this.location1 = location1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public double[] getLocation() {
//        return location;
//    }
//
//    public void setLocation(double[] location) {
//        this.location = location;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
