package com.firebase.samples.logindemo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.adapters.HintAdapter;
import com.firebase.samples.logindemo.models.BasicInfo;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.SaveObjects;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

/**
 * Created by apatel on 7/7/16.
 */
public class UpLoadProperty extends ProductParentActivity implements AdapterView.OnItemSelectedListener {

    private String TAG = "UpLoadProperty";
    Spinner propertTypeSpinner;


    Place placeProperty;


    String address;
    String properyType;
    private Button logButton;
    private RelativeLayout uploadImagesandVideoLayout, basicInfoLayout, extraInfoLayout,contactInfoLayout;
    private double[] location;
    private LatLng latLng;
    private TextView basic_textview, extra_textview, contact_textview;

    @Override
    protected void onStart() {
        super.onStart();
        ArmsLogs.i(TAG, "onStart");

    }

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

        logButton = (Button) findViewById(R.id.save);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllDataForTheProperty();
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


        basic_textview = (TextView)findViewById(R.id.basic_textview);
        extra_textview = (TextView)findViewById(R.id.extra_textview);
        contact_textview = (TextView)findViewById(R.id.contact_textview);
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

    public void saveAllDataForTheProperty() {
//        address = (String) placeProperty.getAddress(); // can get lot of things
        if (placeProperty==null) {
            ArmsLogs.i(TAG, "data is Not valid ");
        }
        // you need to check all filed are there using boolean for every row - u can do it after uploading Images
//else if (basicInfo.getRent()!=null ){
//
//        }
        else   {
            location[0] = placeProperty.getLatLng().longitude;
            location[1] = placeProperty.getLatLng().longitude;
            properyType = propertTypeSpinner.getSelectedItem().toString();




        }


//        ArmsLogs.i(TAG, "address:  " + address + "properyType:  " + properyType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArmsLogs.i(TAG, "onResume");
        extraInfo = SaveObjects.getExtraInfoDataFromSharedPreferance(UpLoadProperty.this,  SaveObjects.extraInfo_sharedpref_key);
        contactInfo = SaveObjects.getContactInfoDataFromSharedPreferance(UpLoadProperty.this,  SaveObjects.contactInfo_sharedpref_key);
        displayBasicInfo();
        displayExtraInfo();
        displayContactInfo();
    }

    private void displayContactInfo() {
        if (contactInfo!=null) {
            contact_textview.setTextColor(getResources().getColor(R.color.blue_text_property_color));
            contact_textview.setText("FirstName:");
            contact_textview.append(String.valueOf(contactInfo.getFirstName()));
            contact_textview.append("  LastName:");
            contact_textview.append(String.valueOf(contactInfo.getLastName()));
            contact_textview.append("     Email:");
            contact_textview.append(String.valueOf(contactInfo.getEmail()));
            contact_textview.append("     Phone Number:");
            contact_textview.append(String.valueOf(contactInfo.getPhoneNumber()));
            contact_textview.append("     Owner:");
            contact_textview.append(String.valueOf(contactInfo.isOwnerContact()));
            contact_textview.append("     Roommate:");
            contact_textview.append(String.valueOf(contactInfo.isRoommateContact()));
            contact_textview.append("     Broker:");
            contact_textview.append(String.valueOf(contactInfo.isBrokerContact()));
            contact_textview.append("     PreferedEmail:");
            contact_textview.append(String.valueOf(contactInfo.isPreferedEmail()));
            contact_textview.append("     PreferedPhoneText:");
            contact_textview.append(String.valueOf(contactInfo.isPreferedPhoneText()));
            contact_textview.append("     PreferedPhoneCall:");
            contact_textview.append(String.valueOf(contactInfo.isPreferedPhoneCall()));

        }
    }

    private void displayExtraInfo() {
        if (extraInfo!=null) {
            extra_textview.setTextColor(getResources().getColor(R.color.blue_text_property_color));
            extra_textview.setText("Description:");
            extra_textview.append(String.valueOf(extraInfo.getDescription()));
            extra_textview.append("     NoPetAllowed:");
            extra_textview.append(String.valueOf(extraInfo.isNoPetAllowed()));
            extra_textview.append("     DogsAllowed:");
            extra_textview.append(String.valueOf(extraInfo.isDogsAllowed()));
            extra_textview.append("     CatsAllowed:");
            extra_textview.append(String.valueOf(extraInfo.isCatsAllowed()));
            extra_textview.append("     Furnished:");
            extra_textview.append(String.valueOf(extraInfo.isFurnished()));
            extra_textview.append("     Elevator:");
            extra_textview.append(String.valueOf(extraInfo.isElevator()));
            extra_textview.append("     Doorman:");
            extra_textview.append(String.valueOf(extraInfo.isDoorman()));
            extra_textview.append("     WheelchairAccess:");
            extra_textview.append(String.valueOf(extraInfo.isWheelchairAccess()));
            extra_textview.append("     FitnessGymcenter:");
            extra_textview.append(String.valueOf(extraInfo.isFitnessGymcenter()));
            extra_textview.append("     Swimmingpool:");
            extra_textview.append(String.valueOf(extraInfo.isSwimmingpool()));
            extra_textview.append("     LaundryType:");
            extra_textview.append(String.valueOf(extraInfo.getLaundryType()));
            extra_textview.append("     ParkingType:");
            extra_textview.append(String.valueOf(extraInfo.getParkingType()));
  }
    }

    private void displayBasicInfo() {
        basicInfo = SaveObjects.getBasicInfoDataFromSharedPreferance(UpLoadProperty.this,  SaveObjects.basicInfo_sharedpref_key);

        if (basicInfo!=null) {
            extra_textview.setTextColor(getResources().getColor(R.color.blue_text_property_color));
            extra_textview.setText("Beds:");
            extra_textview.append(String.valueOf(basicInfo.getBeds()));
            basic_textview.append("     Baths:");
            basic_textview.append(String.valueOf(basicInfo.getBath()));
            basic_textview.append("     SquareFeet:");
            basic_textview.append(String.valueOf(basicInfo.getSquareFeet()));
            basic_textview.append("     Rent:");
            basic_textview.append(String.valueOf(basicInfo.getRent()));
            basic_textview.append("     Deposit:");
            basic_textview.append(String.valueOf(basicInfo.getDeposit()));
            basic_textview.append("     LeaseTime:");
            basic_textview.append(String.valueOf(basicInfo.getLeaseTime()));
            basic_textview.append("     Fees:");
            basic_textview.append(String.valueOf(basicInfo.isFee()));
            basic_textview.append("     No Fees:");
            basic_textview.append(String.valueOf(basicInfo.isNoFee()));
            basic_textview.append("     Owner:");
            basic_textview.append(String.valueOf(basicInfo.isOwner()));
            basic_textview.append("     Roommate:");
            basic_textview.append(String.valueOf(basicInfo.isRoommate()));
            basic_textview.append("     Broker:");
            basic_textview.append(String.valueOf(basicInfo.isBroker()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
