package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.firebase.samples.logindemo.R;

/**
 * Created by apatel on 7/7/16.
 */
public class UploadImagesandVideoActivity extends ProductParentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_images_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.upload_images_and_videos));
    }
}
