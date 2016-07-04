package com.firebase.samples.logindemo.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.activities.CreateProfile;
import com.firebase.samples.logindemo.activities.EditProfileActivity;
import com.firebase.samples.logindemo.activities.ListofProducts;
import com.firebase.samples.logindemo.activities.ProfileActivity;
 import com.firebase.samples.logindemo.activities.UploadProfilePicturesActivity;
import com.firebase.samples.logindemo.models.User;
import com.firebase.samples.logindemo.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

//import com.firebase.samples.logindemo.activities.ListofProducts;

//import com.firebase.samples.logindemo.activities.ProfileActivity;

/**
 * Created by apatel on 2/11/16.
 */
public class HelpUtils {


    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public static Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }



    //  this will call firebase for single user detail
    public static void getCurrentUserData(Activity activity) {
        UserManagement.getCurrentUserData(activity);
    }





    public static FirebaseUser getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }

// start Activities
    public static void openProfilePage(Activity context) {
        context.startActivity(new Intent(context, ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));//.putExtra(IntentConstant.CURRENT_USER_DETA, userModel));

        context.finish();
    }
    public static void openEditProfilePage(Context context) {
        context.startActivity(new Intent(context, EditProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    public static void OpenListOfProducts(Context context) {
        context.startActivity(new Intent(context, ListofProducts.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    public static void openUploadItemActivity(Context context) {
//        context.startActivity(new Intent(context, UploadProfilePicturesActivity.class));

    }
    public static void openCreateProfile(Activity activity) {
        activity.startActivity(new Intent(activity, CreateProfile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        activity.finish();
    }

    public static void openUploadProfilePicturesActivity(Activity activity) {
        activity.startActivity(new Intent(activity, UploadProfilePicturesActivity.class));
    }

    public static int getGenderPosition(String gender, Context context) {
        if (gender.equalsIgnoreCase(context.getResources().getStringArray(R.array.gender_string_array)[0])){
            return 0;
        } else if (gender.equalsIgnoreCase(context.getResources().getStringArray(R.array.gender_string_array)[1])){
            return 1;
        }else if (gender.equalsIgnoreCase(context.getResources().getStringArray(R.array.gender_string_array)[2])){
            return 2;
        }
        return 0;
    }
//UniqueUserId
//    public static void setUniqueUserId(String uniqueUserId, Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        prefsEditor.putString(HelpUtils.uniqueUserId, uniqueUserId);
//        prefsEditor.commit();
//    }

    //    public static String getUniqueUserId(Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String uniqueUserIdFromSharedPreferance = mPrefs.getString(HelpUtils.uniqueUserId, "");
//        return uniqueUserIdFromSharedPreferance;
//    }
// uniqueProductId
//    public static void setUniqueProductId(String uniqueUserId, Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        prefsEditor.putString(HelpUtils.uniqueUserId, uniqueUserId);
//        prefsEditor.commit();
//    }
//
//    public static String getUniqueProductId(Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String uniqueUserIdFromSharedPreferance = mPrefs.getString(HelpUtils.uniqueUserId, "");
//        return uniqueUserIdFromSharedPreferance;
//    }

    // saveAllProductId
//    public static void saveAllProductIdInSharedPreferance(User user, Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        prefsEditor.putString(HelpUtils.user, json);
//        prefsEditor.commit();
//    }
//
//    public static User getAllProductIdFromSharedPreferance(Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = mPrefs.getString(HelpUtils.user, "");
//        User user = gson.fromJson(json, User.class);
//        return user;
//    }


//    public static void saveUserDataInSharedPreferance(UserModel user, Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        prefsEditor.putString(HelpUtils.user, json);
//        prefsEditor.commit();
//    }
//
//    public static UserModel getuserDataFromSharedPreferance(Context context) {
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = mPrefs.getString(HelpUtils.user, "");
//        UserModel usermodel = gson.fromJson(json, UserModel.class);
//        return usermodel;
//    }




}
