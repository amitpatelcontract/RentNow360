package com.firebase.samples.logindemo.extra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by arms on 5/28/16.
 */
public class ArmsSpinner extends Spinner implements AdapterView.OnItemSelectedListener {
    public ArmsSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
