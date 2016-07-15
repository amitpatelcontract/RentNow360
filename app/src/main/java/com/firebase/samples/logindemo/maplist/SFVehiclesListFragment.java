package com.firebase.samples.logindemo.maplist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by arms on 7/5/16.
 */
public class SFVehiclesListFragment extends Fragment {//} implements GeoQueryEventListener  {//}, GoogleMap.OnCameraChangeListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> myDataset;
    private static final GeoLocation INITIAL_CENTER = new GeoLocation(37.7789, -122.4017);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String GEO_FIRE_REF = "https://publicdata-transit.firebaseio.com/_geofire";
//    private GoogleMap map;
//    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;
    private Map<String, Marker> markers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sflist, container, false);

        // setup map and camera position
//        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//        this.map = mapFragment.getMap();
        LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
//        this.searchCircle = this.map.addCircle(new CircleOptions().center(latLngCenter).radius(1000));
//        this.searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
//        this.searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
//        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
//        this.map.setOnCameraChangeListener(this);

        Firebase.setAndroidContext(getActivity());

        // setup GeoFire
        this.geoFire = new GeoFire(new Firebase(GEO_FIRE_REF));
        // radius in km
        this.geoQuery = this.geoFire.queryAtLocation(INITIAL_CENTER, 1);

        // setup markers
//        this.markers = new HashMap<String, Marker>();
        this.geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                myDataset.add(key);

                mAdapter = new MyAdapter(myDataset);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onKeyExited(String key) {
                // Remove any old marker
//                Marker marker = this.markers.get(key);
//                if (marker != null) {
//                    marker.remove();
//                    this.markers.remove(key);
//                }
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
//                // Move the marker
//                Marker marker = this.markers.get(key);
//
//                if (marker != null) {
//                    this.animateMarkerTo(marker, location.latitude, location.longitude);
//                }
            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(FirebaseError error) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage("There was an unexpected error querying GeoFire: " + error.getMessage())
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        myDataset = new ArrayList<String>();
//        myDataset.add("Amit Patel");
//        myDataset.add("Amit Patel1");
//        myDataset.add("Amit Patel2");
//        myDataset.add("Amit Patel3");
//        myDataset.add("Amit Patel4");
//        myDataset.add("Amit Patel5");


        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        return v;

    }


    @Override
    public void onStop() {
        super.onStop();
        // remove all event listeners to stop updating in the background
        this.geoQuery.removeAllListeners();
//        for (Marker marker: this.markers.values()) {
//            marker.remove();
//        }
//        this.markers.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        // add an event listener to start updating locations again
//        this.geoQuery.addGeoQueryEventListener(this);
    }

//    @Override
//    public void onKeyEntered(String key, GeoLocation location) {
//        // Add a new marker to the map
////        Marker marker = this.map.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)));
////        this.markers.put(key, marker);
//
//        myDataset.add(key);
//
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
//
//    }

//    @Override
//    public void onKeyExited(String key) {
//        // Remove any old marker
//        Marker marker = this.markers.get(key);
//        if (marker != null) {
//            marker.remove();
//            this.markers.remove(key);
//        }
//    }

//    @Override
//    public void onKeyMoved(String key, GeoLocation location) {
//    // Move the marker
//    Marker marker = this.markers.get(key);
//
//    if (marker != null) {
//        this.animateMarkerTo(marker, location.latitude, location.longitude);
//    }
//}

//    @Override
//    public void onGeoQueryReady() {
//    }

//    @Override
//    public void onGeoQueryError(FirebaseError error) {
//        new AlertDialog.Builder(this)
//                .setTitle("Error")
//                .setMessage("There was an unexpected error querying GeoFire: " + error.getMessage())
//                .setPositiveButton(android.R.string.ok, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//    }

//    // Animation handler for old APIs without animation support
    private void animateMarkerTo(final Marker marker, final double lat, final double lng) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long DURATION_MS = 3000;
        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final LatLng startPosition = marker.getPosition();
        handler.post(new Runnable() {
            @Override
            public void run() {
                float elapsed = SystemClock.uptimeMillis() - start;
                float t = elapsed/DURATION_MS;
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

//    private double zoomLevelToRadius(double zoomLevel) {
//        // Approximation to fit circle into view
//        return 16384000/Math.pow(2, zoomLevel);
//    }

//    @Override
//    public void onCameraChange(CameraPosition cameraPosition) {
//        // Update the search criteria for this geoQuery and the circle on the map
//        LatLng center = cameraPosition.target;
//        double radius = zoomLevelToRadius(cameraPosition.zoom);
//        this.searchCircle.setCenter(center);
//        this.searchCircle.setRadius(radius);
//        this.geoQuery.setCenter(new GeoLocation(center.latitude, center.longitude));
//        // radius in km
//        this.geoQuery.setRadius(radius/1000);
//    }
}