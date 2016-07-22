package com.firebase.samples.logindemo;

import android.app.Application;


/**
 * Initialize Firebase with the application context. This must happen before the client is used.
 *
 * @author mimming
 * @since 12/17/14
 */
public class RentNowApplication extends Application {

    public static AppData appData = new AppData();

    public static class AppData {
        public double[] location;
//        public Filters filters;

//        public void getLastAppData() {

//              location = appData.location;
//             filters = appData.filters;
//        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        appData = SaveObjects.getAppDataDataFromSharedPreferance(this, SaveObjects.appData_sharedpref_key);


    }
}
