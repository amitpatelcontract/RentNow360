package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.SaveObjects;

/**
 * Created by apatel on 7/7/16.
 */
public class ExtraInfoActivity extends ProductParentActivity {
    private EditText description;
    private CheckBox no_pets_checkBox, dogs_checkBox, cats_checkBox, furnished, elevator, doorman, wheelchairAccess, fitnessGymCenter, swimmingPool;

    private Spinner laundry_spinner, parking_spinner;
    private Button logButton;
    private String TAG = "ExtraInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.extra));

        logButton = (Button) findViewById(R.id.save);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllExtraInfoLocally();
                Toast.makeText(ExtraInfoActivity.this, "clicked", Toast.LENGTH_LONG).show();
            }
        });
        description = (EditText) findViewById(R.id.description);

        no_pets_checkBox = (CheckBox) findViewById(R.id.no_pets_checkBox);
        dogs_checkBox = (CheckBox) findViewById(R.id.dogs_checkBox);
        cats_checkBox = (CheckBox) findViewById(R.id.cats_checkBox);
        furnished = (CheckBox) findViewById(R.id.furnished_checkBox);
        elevator = (CheckBox) findViewById(R.id.elevator_checkBox);
        doorman = (CheckBox) findViewById(R.id.doorman_checkBox);
        wheelchairAccess = (CheckBox) findViewById(R.id.wheelchair_access_checkBox);
        fitnessGymCenter = (CheckBox) findViewById(R.id.fitness_center_gym_checkBox);
        swimmingPool = (CheckBox) findViewById(R.id.swimming_pool_checkBox);

        laundrySpinner();
        parkingSpinner();


    }



    private void saveAllExtraInfoLocally() {
        String descriptionProperty = description.getText().toString();
        if (descriptionProperty!=null)
            descriptionProperty="";
        boolean petsNotAllowed = no_pets_checkBox.isChecked(),
                dogsAllowed = dogs_checkBox.isChecked(),
                catsAllowed = cats_checkBox.isChecked(),
                isFurnished = furnished.isChecked(),
                isElevator = elevator.isChecked(),
                isDoorman = doorman.isChecked(),
                isWheelchairAccess = wheelchairAccess.isChecked(),
                isFitnessGymCenter = fitnessGymCenter.isChecked(),
                isSwimmingPool = swimmingPool.isChecked();

        String laundryType = laundry_spinner.getSelectedItem().toString(),
                parkingType = parking_spinner.getSelectedItem().toString();

        ArmsLogs.d(TAG, "descriptionProperty:-" + descriptionProperty + " petsNotAllowed:" + petsNotAllowed
                + " dogsAllowed:" + dogsAllowed + " catsAllowed:" + catsAllowed + " isFurnished:" + isFurnished
                + " isElevator: " + isElevator + " isDoorman: " + isDoorman + " isWheelchairAccess: " + isWheelchairAccess
                + " isFitnessGymCenter: " + isFitnessGymCenter+ " isSwimmingPool: " + isSwimmingPool+ " laundryType: " + laundryType
                + " parkingType: " + parkingType);


        extraInfo.setDescription(descriptionProperty);
        extraInfo.setNoPetAllowed(petsNotAllowed);
        extraInfo.setDogsAllowed(dogsAllowed);
                extraInfo.setCatsAllowed(catsAllowed);
                extraInfo.setFurnished(isFurnished);
                extraInfo.setElevator(true);
                extraInfo.setDoorman(true);
                extraInfo.setWheelchairAccess(isWheelchairAccess);
                extraInfo.setFitnessGymcenter(isFitnessGymCenter);
                extraInfo.setSwimmingpool(isSwimmingPool);
                extraInfo.setLaundryType(laundryType);
                extraInfo.setParkingType(parkingType);

        SaveObjects.saveObjectInSharedPreferance(basicInfo, ExtraInfoActivity.this, SaveObjects.extraInfo_sharedpref_key);
        finish();

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
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
