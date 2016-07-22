package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.samples.logindemo.models.BasicInfo;
import com.firebase.samples.logindemo.models.ContactInfo;
import com.firebase.samples.logindemo.models.ExtraInfo;

/**
 * Created by apatel on 7/7/16.
 */
public class ProductParentActivity extends AppCompatActivity{
BasicInfo basicInfo;
    ExtraInfo extraInfo;
    ContactInfo contactInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        basicInfo = new BasicInfo();
        extraInfo = new ExtraInfo();
        contactInfo = new ContactInfo();
    }
}
