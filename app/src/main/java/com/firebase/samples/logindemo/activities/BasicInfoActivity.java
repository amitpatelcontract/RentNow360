package com.firebase.samples.logindemo.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.auth.BaseActivity;
import com.firebase.samples.logindemo.extra.MultiSelectionSpinner;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.ConvertUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

/**
 * Created by apatel on 7/7/16.
 */
public class BasicInfoActivity extends ProductParentActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener,
        AdapterView.OnItemSelectedListener,    DatePickerDialog.OnDateSetListener {
    private String TAG = "BasicInfoActivity";
    private EditText square_feet_text, rent_per_month, deposite;
    Spinner bedroomSpinner, bathSpinner;
    private Button buttonDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.basic));


        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.lease_multipal_selector);
        multiSelectionSpinner.setItems(getResources().getStringArray(R.array.lease_time));
        multiSelectionSpinner.setListener(this);
        multiSelectionSpinner.setPrompt("dfkadnbfkabd");
        square_feet_text = (EditText) findViewById(R.id.square_feet_text);
        rent_per_month = (EditText) findViewById(R.id.rent_per_month);
        deposite = (EditText) findViewById(R.id.deposite);


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

        ArrayAdapter<String> bathAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.bathrooms));
        // Drop down layout style - list view with radio button
        bathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        bathSpinner.setAdapter(bathAdapter);

        buttonDatePicker = (Button)findViewById(R.id.selectAvailableDateButton);
        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker( );

            }
        });
    }

    private void showDatePicker(   ) {

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
            for(int i = -6; i <= 6; i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.WEEK_OF_YEAR, i);
                dates[i+6] = date;
            }
            dpd.setHighlightedDays(dates);
        }
        dpd.show(getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void selectedIndices(List<Integer> indices) {
    }

    @Override
    public void selectedStrings(List<String> strings) {
        ArmsLogs.i(TAG, strings.toString());
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
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        buttonDatePicker.setText(date);
    }
}
