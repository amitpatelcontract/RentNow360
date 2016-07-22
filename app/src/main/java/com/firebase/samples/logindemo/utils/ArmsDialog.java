package com.firebase.samples.logindemo.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by arms on 5/28/16.
 */
public class ArmsDialog   {
    public static void myCustomDialog(final Activity activity, String title, String message)
    {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);
        alertbox.setTitle("Warning");
        alertbox.setMessage("Exit Application?");
        alertbox.setPositiveButton("ok", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        activity.finish();
                    }
                });
        alertbox.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        alertbox.show();
    }

}