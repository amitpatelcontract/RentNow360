package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.utils.ConvertUtils;

/**
 * Created by apatel on 7/7/16.
 */
public class ExtraInfoActivity extends ProductParentActivity {
    private EditText description;
    private CheckBox no_pets_checkBox, dogs_checkBox, cats_checkBox, furnished, wheelchair_access;
    private Spinner laundry_spinner, parking_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.extra));


        description = (EditText) findViewById(R.id.description);

        no_pets_checkBox = (CheckBox) findViewById(R.id.no_pets_checkBox);
        dogs_checkBox = (CheckBox) findViewById(R.id.no_pets_checkBox);
        cats_checkBox = (CheckBox) findViewById(R.id.no_pets_checkBox);
        furnished = (CheckBox) findViewById(R.id.no_pets_checkBox);
        wheelchair_access = (CheckBox) findViewById(R.id.no_pets_checkBox);

        laundrySpinner();
        parkingSpinner();


    }

    private void laundrySpinner() {
        // Spinner element
        laundry_spinner = (Spinner) findViewById(R.id.laundry_spinner);
        // Spinner click listener
        laundry_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String laundryType = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "laundryType: " + laundryType, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> laundryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.laundry_options));
        // Drop down layout style - list view with radio button
        laundryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        laundry_spinner.setAdapter(laundryAdapter);
    }

    private void parkingSpinner() {
        // Spinner element
        parking_spinner = (Spinner) findViewById(R.id.parking_spinner);
        // Spinner click listener
        parking_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String parkingType = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "parkingType: " + parkingType, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> parkingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.parking_options));
        // Drop down layout style - list view with radio button
        parkingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        parking_spinner.setAdapter(parkingAdapter);
    }


}
