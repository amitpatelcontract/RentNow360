package com.firebase.samples.logindemo.utils;

import android.util.Log;

/**
 * Created by arms on 5/29/16.
 */
public class ArmsLogs {

    private static boolean DEBUG = true;

    public static  void i(String Tag, String message){
        if (DEBUG)
            Log.i(Tag, message);
    }

    public static  void e(String Tag, String message){
        if (DEBUG)
            Log.e(Tag, message);
    }

    public static  void d(String Tag, String message){
        if (DEBUG)
            Log.d(Tag, message);
    }
}
