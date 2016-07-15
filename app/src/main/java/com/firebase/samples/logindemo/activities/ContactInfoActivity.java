package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.models.ContactInfo;
import com.firebase.samples.logindemo.models.ExtraInfo;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.EmailPhoneUtils;
import com.firebase.samples.logindemo.utils.SaveObjects;

/**
 * Created by apatel on 7/7/16.
 */
public class ContactInfoActivity extends ProductParentActivity {
    private Button logButton;
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText phoneNumberEdit;
    private RadioButton radioOwner, radioBroker, radioRoommate;
    private CheckBox checkBoxEmail, checkBoxPhoneText, checkBoxPhoneCall;
    public String TAG = "ContactInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.contact_info));

        firstNameEdit = (EditText) findViewById(R.id.first_name_card);
        lastNameEdit = (EditText) findViewById(R.id.last_name_card);
        emailEdit = (EditText) findViewById(R.id.email);
        phoneNumberEdit = (EditText) findViewById(R.id.phonenumber);
        radioOwner = (RadioButton) findViewById(R.id.radioOwner);
        radioBroker = (RadioButton) findViewById(R.id.radioBroker);
        radioRoommate = (RadioButton) findViewById(R.id.radioRoommate);
        checkBoxEmail = (CheckBox) findViewById(R.id.prefered_way_email);
        checkBoxPhoneText = (CheckBox) findViewById(R.id.prefered_way_phone_text);
        checkBoxPhoneCall = (CheckBox) findViewById(R.id.prefered_way_phone_call);

        logButton = (Button) findViewById(R.id.save);
        logButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String firstName = firstNameEdit.getText().toString(),
                        lastName = lastNameEdit.getText().toString(),
                        email = emailEdit.getText().toString(),
                        phoneNumber = phoneNumberEdit.getText().toString();
                boolean owner = radioOwner.isChecked(), broker = radioBroker.isChecked(), roommate = radioRoommate.isChecked();
                boolean cBoxEmail = checkBoxEmail.isChecked(),
                        cBoxPhoneText = checkBoxPhoneText.isChecked(),
                        cBoxPhoneCall = checkBoxPhoneCall.isChecked();

                if (!EmailPhoneUtils.isEmailValid(email) && !email.equalsIgnoreCase("")) {
                    ArmsLogs.i(TAG, "not a valid Email ");

                } else if (!EmailPhoneUtils.isValidMobile(phoneNumber) && !phoneNumber.equalsIgnoreCase("")) {
                    ArmsLogs.i(TAG, "not a valid Phone Number");


                } else if (EmailPhoneUtils.isEmailValid(email) || EmailPhoneUtils.isValidMobile(phoneNumber)) {
                    ArmsLogs.i(TAG, "firstname: " + firstName
                            + " lastname:" + lastName + " email:" + email + " phoneNumber:" + phoneNumber

                            + " owner: " + owner
                            + " broker: " + broker
                            + " roommate: " + roommate
                            + " preMethEmail: " + cBoxEmail
                            + " preMethPhoneText: " + cBoxPhoneText
                            + " preMethPhoneCall: " + cBoxPhoneCall);
                    contactInfo.setFirstName(firstName);
                    contactInfo.setLastName(lastName);
                    contactInfo.setEmail(email);
                    contactInfo.setPhoneNumber(phoneNumber);
                    contactInfo.setOwnerContact(owner);
                    contactInfo.setBrokerContact(broker);
                    contactInfo.setRoommateContact(roommate);
                    contactInfo.setPreferedEmail(cBoxEmail);
                    contactInfo.setPreferedPhoneCall(cBoxPhoneCall);
                    contactInfo.setPreferedPhoneText(cBoxPhoneText);
                    SaveObjects.saveObjectInSharedPreferance(contactInfo, ContactInfoActivity.this, SaveObjects.contactInfo_sharedpref_key);
                    finish();
                } else {
                    Toast.makeText(ContactInfoActivity.this, "Provide Email id or Contact Number", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
