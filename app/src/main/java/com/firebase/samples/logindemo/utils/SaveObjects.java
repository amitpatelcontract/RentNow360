package com.firebase.samples.logindemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.samples.logindemo.models.BasicInfo;
import com.firebase.samples.logindemo.models.ContactInfo;
import com.firebase.samples.logindemo.models.ExtraInfo;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by arms on 7/10/16.
 */
public class SaveObjects {

    public static String basicInfo_sharedpref_key = "basicInfo";
    public static String extraInfo_sharedpref_key = "extraInfo";
    public static String contactInfo_sharedpref_key = "contactInfo";

    public static void saveObjectInSharedPreferance(Serializable objects, Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(objects);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    public static void clearObjectInSharedPreferance(Serializable objects, Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(null);
        prefsEditor.putString(key, json);

        prefsEditor.commit();
    }

// basic Info
    public static BasicInfo getBasicInfoDataFromSharedPreferance(Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = mPrefs.getString(key, "");
        BasicInfo basicInfo = gson.fromJson(json, BasicInfo.class);
        return basicInfo;
    }
    //extraInfo
    public static ExtraInfo getExtraInfoDataFromSharedPreferance(Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = mPrefs.getString(key, "");
        ExtraInfo extraInfo = gson.fromJson(json, ExtraInfo.class);
        return extraInfo;
    }

    //extraInfo
    public static ContactInfo getContactInfoDataFromSharedPreferance(Context context, String key) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = mPrefs.getString(key, "");
        ContactInfo contactInfo = gson.fromJson(json, ContactInfo.class);
        return contactInfo;
    }




    public static void clearAllPreviousPropertyData(Context context) {
        SaveObjects.clearObjectInSharedPreferance(BasicInfo.class, context, SaveObjects.basicInfo_sharedpref_key);
        SaveObjects.clearObjectInSharedPreferance(BasicInfo.class, context, SaveObjects.extraInfo_sharedpref_key);
        SaveObjects.clearObjectInSharedPreferance(BasicInfo.class, context, SaveObjects.contactInfo_sharedpref_key);
    }
}
