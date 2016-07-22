package com.firebase.samples.logindemo.maplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.activities.ProfileActivity;
import com.firebase.samples.logindemo.activities.PropertyActivity;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.LocationUtils;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by arms on 7/5/16.
 */
public class SFVehiclesListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> myDataset;
    private static GeoLocation INITIAL_CENTER;// = new GeoLocation(37.7789, -122.4017);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String GEO_FIRE_REF = "https://publicdata-transit.firebaseio.com/_geofire";
    //    private GoogleMap map;
//    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;
    private Map<String, Marker> markers;
    private String TAG = "SFVehicles SFVehiclesListFragment";
    double location[];
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity =  activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sflist, container, false);
//          location = ((PropertyActivity)getActivity()).getLocation();//getArguments().getDoubleArray("location");
        location = getArguments().getDoubleArray("location");

        displayDataInRecycleView(location, activity);
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

    private void displayDataInRecycleView(double[] location, Activity activity) {
        INITIAL_CENTER = new GeoLocation(location[0], location[1]);
        ArmsLogs.i(TAG, "list onCreateView" + "location0: " + String.valueOf(location[0]));

        Firebase.setAndroidContext(activity);
        myDataset = new ArrayList<String>();
        // setup GeoFire
        this.geoFire = new GeoFire(new Firebase(GEO_FIRE_REF));
        // radius in km
        this.geoQuery = this.geoFire.queryAtLocation(INITIAL_CENTER, 1);

        // setup markers
//        this.markers = new HashMap<String, Marker>();
        this.geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if (myDataset == null)
                    myDataset = new ArrayList<String>();

                myDataset.add(key);
                ArmsLogs.i(TAG, "list onCreateView" + "location0: " + key);

                mAdapter = new MyAdapter(myDataset);
                if (mRecyclerView != null) {
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.invalidate();
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

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


    }

    @Override
    public void onStop() {
        super.onStop();
        ArmsLogs.i(TAG, "list onStop" + "location0: ");
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
        location = ((PropertyActivity)activity).getLocation();
        ArmsLogs.i(TAG, "list onStart" + "location0: ");


    }

    public void updateLocation(double[] location, Activity activity) {
//        updateDataInRecycleView(location, activity);
        displayDataInRecycleView(location, activity);
    }


}