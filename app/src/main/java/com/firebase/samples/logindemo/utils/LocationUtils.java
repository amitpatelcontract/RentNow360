package com.firebase.samples.logindemo.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import com.firebase.geofire.GeoLocation;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arms on 2/13/16.
 */
public class LocationUtils {//} implements LocationListener {
    private final String TAG ="LocationUtils";
        //The minimum distance to change updates in meters
        private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

        //The minimum time beetwen updates in milliseconds
        private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1; // 1 minute

        private final static boolean forceNetwork = false;

        private static LocationUtils instance = null;


    private LocationManager locationManager;
        public Location location;
        public double longitude;
        public double latitude;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean locationServiceAvailable;
public static  double[] loc = new double[2];

    /**
         * Singleton implementation
         * @return
         */
        public static LocationUtils getLocationManager(Context context)     {
            if (instance == null) {
                instance = new LocationUtils(context);
            }
            return instance;
        }

        /**
         * Local constructor
         */
        private LocationUtils( Context context )     {

            initLocationService(context);
            Log.i(TAG, "LocationService created");
        }



        /**
         * Sets up location service after permissions is granted
         */
        @TargetApi(23)
        private void initLocationService(Context context) {


            if ( Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return  ;
            }

            try   {
                this.longitude = 0.0;
                this.latitude = 0.0;
                this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                // Get GPS and network status
                this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (forceNetwork) isGPSEnabled = false;

                if (!isNetworkEnabled && !isGPSEnabled)    {
                    // cannot get location
                    this.locationServiceAvailable = false;
                }
                else
                {
                    this.locationServiceAvailable = true;

                    if (isNetworkEnabled) {
//                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                                MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, context);
                        if (locationManager != null)   {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                            updateCoordinates();
                        }
                    }//end if

                  else  if (isGPSEnabled)  {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                                MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, context);

                        if (locationManager != null)  {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            updateCoordinates();
                        }
                    }
                }
                loc[0] = location.getLatitude();
                loc[1] = location.getLongitude();
            } catch (Exception ex)  {
                Log.i(TAG, "Error creating location service: " + ex.getMessage() );

            }
        }


//        @Override
//        public void onLocationChanged(Location location)     {
//            // do stuff here with location object
//        }


    public static Address getCityAndState(Context context, double lat, double lng) {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0)
            return addresses.get(0);
        else
        return null;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



    }



//private static  double[] loc;
//
//
//    public static double[] getCurrentLocations(Context context)
//    {
//
//        // Get the location manager
//        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, false);
//
//        if ( ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions( context, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
//        }
//
//
//
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//        Double lat,lon;
//        try {
//            lat = location.getLatitude();
//            lon = location.getLongitude();
//            loc[0] = lat;
//            loc[1] = lon;
//            return loc;
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//            return null;
//        }
//    }
