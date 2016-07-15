package com.firebase.samples.logindemo.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.auth.BaseActivity;
import com.firebase.samples.logindemo.extra.MultiSelectionSpinner;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.ConvertUtils;
import com.firebase.samples.logindemo.utils.SaveObjects;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by apatel on 7/7/16.
 */
public class BasicInfoActivity extends ProductParentActivity implements
        AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {
    private String TAG = "BasicInfoActivity";
    private EditText square_feet_edit, rent_per_month, deposite_editText;
    Spinner bedroomSpinner, bathSpinner;

    private ImageView imageDatePicker;
    private Button logButton;

    int bedroom, squareFeet, rentPM, deposit;
    String bathroom;
    String availableDate;
    List<String> leaseTime;
    private TextView availableDateTextView;
    private RadioButton radioFee, radioNoFee, radioOwner, radioBroker, radioRoommate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.basic));
        logButton = (Button) findViewById(R.id.save);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllBasicInfoLocally();
                Toast.makeText(BasicInfoActivity.this, "clicked", Toast.LENGTH_LONG).show();
            }
        });

        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.lease_multipal_selector);
        multiSelectionSpinner.setItems(getResources().getStringArray(R.array.lease_time));
        multiSelectionSpinner.setListener(new MultiSelectionSpinner.OnMultipleItemsSelectedListener() {
            @Override
            public void selectedIndices(List<Integer> indices) {

            }

            @Override
            public void selectedStrings(List<String> strings) {

                ArmsLogs.i(TAG, "selectedStrings:" + strings.toString());
                leaseTime = strings;
            }
        });

        square_feet_edit = (EditText) findViewById(R.id.square_feet_edit);
        rent_per_month = (EditText) findViewById(R.id.rent_per_month);
        deposite_editText = (EditText) findViewById(R.id.deposite);


        // Spinner element
        bedroomSpinner = (Spinner) findViewById(R.id.bed_room_spinner);
        // Spinner click listener
        bedroomSpinner.setOnItemSelectedListener(this);
        Integer[] items = ConvertUtils.toObject(getResources().getIntArray(R.array.bed_rooms));
        ArrayAdapter<Integer> bedAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ConvertUtils.toObject(getResources().getIntArray(R.array.bed_rooms)));
        // Drop down layout style - list view with radio button
        bedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        bedroomSpinner.setAdapter(bedAdapter);


        // Spinner element
        bathSpinner = (Spinner) findViewById(R.id.bath_spinner);

        // Spinner click listener
        bathSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> bathAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bathrooms));
        // Drop down layout style - list view with radio button
        bathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        bathSpinner.setAdapter(bathAdapter);

        availableDateTextView = (TextView) findViewById(R.id.selectAvailableDate);
        availableDateTextView.setOnClickListener(this);
        imageDatePicker = (ImageView) findViewById(R.id.selectAvailableDateImageView);
        imageDatePicker.setOnClickListener(this);

        radioFee = (RadioButton) findViewById(R.id.radioFee);
        radioNoFee = (RadioButton) findViewById(R.id.radioNoFee);

        radioOwner = (RadioButton) findViewById(R.id.radioOwner);
        radioBroker = (RadioButton) findViewById(R.id.radioBroker);
        radioRoommate = (RadioButton) findViewById(R.id.radioRoommate);

    }

    private void saveAllBasicInfoLocally() {
        boolean fee = radioFee.isChecked(), nofee = radioNoFee.isChecked(), owner = radioOwner.isChecked(), broker = radioBroker.isChecked(), roommate = radioRoommate.isChecked();
        bedroom = (int) bedroomSpinner.getSelectedItem();
        bathroom =  bathSpinner.getSelectedItem().toString();
        if (square_feet_edit.getText().toString().equalsIgnoreCase("")) {
            squareFeet = Integer.parseInt(square_feet_edit.getText().toString());
        }
        if (!rent_per_month.getText().toString().equalsIgnoreCase(""))
        rentPM = Integer.parseInt(rent_per_month.getText().toString());
        deposit = Integer.parseInt(deposite_editText.getText().toString());
        availableDate = availableDateTextView.getText().toString();
        List<String> leaseTimelist = leaseTime;
// if user havn't set spinner, it will consider any
        if (leaseTimelist == null) {
            leaseTimelist = new ArrayList<String>();
            leaseTimelist.add(getResources().getStringArray(R.array.lease_time)[0]);
        }

        if (!square_feet_edit.getText().toString().equalsIgnoreCase("") && !rent_per_month.getText().toString().equalsIgnoreCase("") && !deposite_editText.getText().toString().equalsIgnoreCase("") && !availableDate.equalsIgnoreCase(getResources().getString(R.string.available_date))) {
            ArmsLogs.d(TAG, "bedroom:-" + bedroom + " bathroom:" + bathroom + " squareFeet:" + squareFeet + " rentPM:" + rentPM + " deposit:" + deposit
                    + " availableDate: " + availableDate + " leaseTimelist: " + leaseTimelist.toString()
                    + " fee: " + fee
                    + " nofee: " + nofee
                    + " owner: " + owner
                    + " broker: " + broker
                    + " roommate: " + roommate);
            basicInfo.setBath(bathroom);
            basicInfo.setSquareFeet(squareFeet);
            basicInfo.setRent(rentPM);
            basicInfo.setDeposit(deposit);
            basicInfo.setFee(fee);
            basicInfo.setNoFee(nofee);
            basicInfo.setOwner(owner);
            basicInfo.setRoommate(roommate);
            basicInfo.setBroker(broker);
            basicInfo.setAvailableDate(availableDate);

            SaveObjects.saveObjectInSharedPreferance(basicInfo, BasicInfoActivity.this, SaveObjects.basicInfo_sharedpref_key);
            finish();

        } else {
            ArmsLogs.d(TAG, "fill all the fields");

        }


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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        availableDateTextView.setText(date);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.selectAvailableDate:
                showDatePicker();
                break;

            case R.id.selectAvailableDateImageView:
                showDatePicker();
                break;


            default:
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void showDatePicker() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
//        dpd.setThemeDark(modeDarkDate.isChecked());
//        dpd.vibrate(vibrateDate.isChecked());
//        dpd.dismissOnPause(dismissDate.isChecked());
//        dpd.showYearPickerFirst(showYearFirst.isChecked());
//        if (modeCustomAccentDate.isChecked()) {
        dpd.setAccentColor(getResources().getColor(R.color.light_blue));
//        }
//        if (titleDate.isChecked()) {
//            dpd.setTitle("DatePicker Title");
//        }
//        if (limitDates.isChecked()) {
//            Calendar[] dates = new Calendar[13];
//            for(int i = -6; i <= 6; i++) {
//                Calendar date = Calendar.getInstance();
//                date.add(Calendar.MONTH, i);
//                dates[i+6] = date;
//            }
//            dpd.setSelectableDays(dates);
//        }
//        if (highlightDates.isChecked()) {
        if (true) {
            Calendar[] dates = new Calendar[13];
            for (int i = -6; i <= 6; i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.WEEK_OF_YEAR, i);
                dates[i + 6] = date;
            }
            dpd.setHighlightedDays(dates);
        }
        dpd.show(getFragmentManager(), "Datepickerdialog");

    }
}
