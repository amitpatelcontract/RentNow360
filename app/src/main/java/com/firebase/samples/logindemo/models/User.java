package com.firebase.samples.logindemo.models;


import java.util.LinkedHashMap;

/**
 * Created by apatel on 2/11/16.
 */
public class User {


    //    data.put("date", ServerValue.TIMESTAMP); add time as a parameters as well
    private String id;
    private String accessToken;
    private String displayName;

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    private String profileImageURL;
    private String email;
    private double location[];
    // cachedUserProfile
//    private LinkedHashMap cachedUserProfile;
    private String TAG = "User";

    public User(String id, String accessToken, String displayName, String profileImageURL, String email, double[] location ) {
        this.accessToken = accessToken;
        this.displayName = displayName;
        this.profileImageURL = profileImageURL;
        this.email = email;
        this.location = location;
//        this.cachedUserProfile = cachedUserProfile;
        this.id = id;
    }

    public User() {
    }

//    public LinkedHashMap getCachedUserProfile() {
////        getCachedUserProfileFromHashMap(cachedUserProfile);
//
//        return cachedUserProfile;
//    }


//    public void setCachedUserProfile(LinkedHashMap cachedUserProfile) {
//        this.cachedUserProfile = cachedUserProfile;
//    }


    public static CashedUserHashMap getCachedUserProfileFromHashMap(LinkedHashMap cachedUserProfile) {
        CashedUserHashMap cashedUserHashMap = new CashedUserHashMap();

        try {
            cashedUserHashMap.setGender((String) cachedUserProfile.get("gender"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            cashedUserHashMap.setLast_name((String) cachedUserProfile.get("last_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            cashedUserHashMap.setFirst_name((String) cachedUserProfile.get("first_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        LinkedTreeMap age_rangeMap = (LinkedTreeMap) cachedUserProfile.get("age_range");
        try {
            if (cachedUserProfile.get("age_range") != null) {
                cashedUserHashMap.setAge(String.valueOf(cachedUserProfile.get("age_range")));
            } else {
                cashedUserHashMap.setAge("21+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            cashedUserHashMap.setLocale((String) cachedUserProfile.get("locale"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cashedUserHashMap.setTimezone(String.valueOf(cachedUserProfile.get("timezone")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashedUserHashMap;
    }

    public static String getTimezone() {
        return timezone;
    }

    public static void setTimezone(String timezone) {
        User.timezone = timezone;
    }


    private static String timezone;


    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }


//    public String getProfileImageURL() {
//        return profileImageURL;
//    }
//
//    public void setProfileImageURL(String profileImageURL) {
//        this.profileImageURL = profileImageURL;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        NLogs.i(TAG, id);
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
