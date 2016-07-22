package com.firebase.samples.logindemo.listener;

import android.app.Activity;

import com.firebase.samples.logindemo.activities.PropertyActivity;
import com.firebase.samples.logindemo.models.Filters;

/**
 * Created by arms on 7/16/16.
 */
public interface UpdateFragmentData {
    public void updateLocation(double[] location, Activity activity);
//    public void updateFilter(Filters filters);
}
