package com.firebase.samples.logindemo.maplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.activities.PropertyActivity;
import com.firebase.samples.logindemo.listener.UpdateFragmentData;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class SFVehiclesFragment extends Fragment implements GeoQueryEventListener, GoogleMap.OnCameraChangeListener {

    private static GeoLocation INITIAL_CENTER;// = new GeoLocation(37.7789, -122.4017);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String GEO_FIRE_REF = "https://publicdata-transit.firebaseio.com/_geofire";

    private GoogleMap map;
    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;

    private Map<String, Marker> markers;
    private int REQUEST_LOCATION = 1001;
    double location[];
    private Activity activity;
    private String TAG = "SFVehiclesSFF";
    private UpdateFragmentData updateFragmentDataListner;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sfvehicles, container, false);
          location = ((PropertyActivity) activity).getLocation();//getArguments().getDoubleArray("location");
        ArmsLogs.i(TAG, "map: onCreateView " + "location0: " + String.valueOf(location[0]));
        INITIAL_CENTER = new GeoLocation(location[0], location[1]);
        ArmsLogs.i("Locationn", "map: " + "location0: " + String.valueOf(location[0]));



        this.map = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map)).getMap();


        LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
        this.searchCircle = this.map.addCircle(new CircleOptions().center(latLngCenter).radius(1000));
        this.searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
        this.searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
        this.map.setOnCameraChangeListener(this);

        Firebase.setAndroidContext(activity);

        // setup GeoFire
        this.geoFire = new GeoFire(new Firebase(GEO_FIRE_REF));
        // radius in km
        this.geoQuery = this.geoFire.queryAtLocation(INITIAL_CENTER, 1);

        // setup markers
        this.markers = new HashMap<String, Marker>();
        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        ArmsLogs.i(TAG, "map: onStop " + "location0: " + String.valueOf(location[0]));
        // remove all event listeners to stop updating in the background
        this.geoQuery.removeAllListeners();
        for (Marker marker : this.markers.values()) {
            marker.remove();
        }
        this.markers.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        location = ((PropertyActivity)activity).getLocation();
        ArmsLogs.i(TAG, "map: onStart " + "location0: " + String.valueOf(location[0]));
        // add an event listener to start updating locations again
        this.geoQuery.addGeoQueryEventListener(this);
    }

    @Override
    public void onKeyEntered(final String key, GeoLocation location) {
        // Add a new marker to the map
        Marker marker = this.map.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)));
        this.markers.put(key, marker);
        ArmsLogs.i("onKeyEntered", key);
        ArmsLogs.i(TAG, "map: onKeyEntered " + "location0: " + String.valueOf(this.location[0]));
        this.map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.setTitle(key);
                return false;
            }
        });
    }

    @Override
    public void onKeyExited(String key) {
        ArmsLogs.i("onKeyExited", key);
        ArmsLogs.i(TAG, "map: onKeyExited " + "location0: " + String.valueOf(this.location[0]));
        // Remove any old marker
        Marker marker = this.markers.get(key);
        if (marker != null) {
            marker.remove();
            ArmsLogs.i("onKeyExitedremoved", key);
            this.markers.remove(key);
        }
    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {
        ArmsLogs.i(TAG, "map: onKeyMoved " + "location0: " + String.valueOf(this.location[0]));
        // Move the marker
        ArmsLogs.i("onKeyMoved", key);
        Marker marker = this.markers.get(key);
        if (marker != null) {
            this.animateMarkerTo(marker, location.latitude, location.longitude);
        }
    }

    @Override
    public void onGeoQueryReady() {
        ArmsLogs.i(TAG, "map: onGeoQueryReady " + "location0: " + String.valueOf(this.location[0]));
    }

    @Override
    public void onGeoQueryError(FirebaseError error) {
        ArmsLogs.i(TAG, "map: onGeoQueryError " + "location0: " + String.valueOf(this.location[0]));

        new AlertDialog.Builder(activity)
                .setTitle("Error")
                .setMessage("There was an unexpected error querying GeoFire: " + error.getMessage())
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // Animation handler for old APIs without animation support
    private void animateMarkerTo(final Marker marker, final double lat, final double lng) {
        ArmsLogs.i(TAG, "map: animateMarkerTo " + "location0: " + String.valueOf(this.location[0]));

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long DURATION_MS = 3000;
        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final LatLng startPosition = marker.getPosition();
        handler.post(new Runnable() {
            @Override
            public void run() {
                float elapsed = SystemClock.uptimeMillis() - start;
                float t = elapsed / DURATION_MS;
                float v = interpolator.getInterpolation(t);

                double currentLat = (lat - startPosition.latitude) * v + startPosition.latitude;
                double currentLng = (lng - startPosition.longitude) * v + startPosition.longitude;
                marker.setPosition(new LatLng(currentLat, currentLng));

                // if animation is not finished yet, repeat
                if (t < 1) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    private double zoomLevelToRadius(double zoomLevel) {
        ArmsLogs.i(TAG, "map: zoomLevelToRadius " + "location0: " + String.valueOf(this.location[0]));
        // Approximation to fit circle into view
        return 16384000 / Math.pow(2, zoomLevel);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        ArmsLogs.i(TAG, "map: onCameraChange " + "location0: " + String.valueOf(this.location[0]));

        // Update the search criteria for this geoQuery and the circle on the map
        LatLng center = cameraPosition.target;
        double radius = zoomLevelToRadius(cameraPosition.zoom);
        this.searchCircle.setCenter(center);
        this.searchCircle.setRadius(radius);
        this.geoQuery.setCenter(new GeoLocation(center.latitude, center.longitude));
        location[0] = center.latitude;
        location[1] = center.longitude;
        updateFragmentDataListner.updateLocation(location, activity);


        // radius in km
        this.geoQuery.setRadius(radius / 1000);
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            ArmsLogs.i(TAG, "map: onRequestPermissionsResult " + "location0: " + String.valueOf(this.location[0]));
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to

            } else {
                // Permission was denied or request was cancelled
            }
        }
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ArmsLogs.i(TAG, "map: onAttach " + "location0: "  );

        updateFragmentDataListner = (UpdateFragmentData) context;
    }
//    @Override
//    public void updateLocation(double[] location) {
//        INITIAL_CENTER = new GeoLocation(location[0], location[1]);
//        ArmsLogs.i("Locationn", "map: "+ "location0: "+String.valueOf(location[0]));
//    }
//
//    @Override
//    public void updateFilter(Filters filters) {
//
//    }
}
