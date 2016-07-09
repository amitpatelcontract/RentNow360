package com.firebase.samples.logindemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.adapters.HintAdapter;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by apatel on 7/7/16.
 */
public class UpLoadProperty extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String TAG = "UpLoadProperty";
    Spinner propertTypeSpinner;


    Place placeProperty;


    String address;
    String properyType;
    private Button logButton;
    private RelativeLayout uploadImagesandVideoLayout, basicInfoLayout, extraInfoLayout,contactInfoLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_property);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.publish_propety));

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {


            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                placeProperty = place;
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        // Spinner element
        propertTypeSpinner = (Spinner) findViewById(R.id.spinner_property_type);

        // Spinner click listener
        propertTypeSpinner.setOnItemSelectedListener(this);
        // Creating adapter for spin
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.property_types_array));


        HintAdapter dataAdapter = new HintAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.property_types_array));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        propertTypeSpinner.setAdapter(dataAdapter);
        propertTypeSpinner.setSelection(dataAdapter.getCount());




        logButton = (Button) findViewById(R.id.log_button);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllDataForProperty();
            }
        });

        uploadImagesandVideoLayout = (RelativeLayout) findViewById(R.id.upload_images_and_videos_layout);
        uploadImagesandVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(UpLoadProperty.this, UploadImagesandVideoActivity.class));
            }
        });

        basicInfoLayout = (RelativeLayout) findViewById(R.id.basic_info_layout);
        basicInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpLoadProperty.this, BasicInfoActivity.class));
            }
        });


        extraInfoLayout = (RelativeLayout) findViewById(R.id.extra_info_layout);
        extraInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpLoadProperty.this, ExtraInfoActivity.class));
            }
        });

        contactInfoLayout = (RelativeLayout) findViewById(R.id.contact_info_layout);
        contactInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpLoadProperty.this, ContactInfoActivity.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getAllDataForProperty() {
        address = (String) placeProperty.getAddress(); // can get lot of things
        properyType = propertTypeSpinner.getSelectedItem().toString();

        ArmsLogs.i(TAG, "address:  " + address + "properyType:  " + properyType);
    }
}
