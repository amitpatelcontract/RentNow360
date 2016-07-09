package com.firebase.samples.logindemo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.extra.ArmsSpinner;
import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.utils.HelpUtils;
import com.firebase.samples.logindemo.utils.LocationUtils;
import com.firebase.samples.logindemo.utils.UserManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;

/**
 * Created by arms on 2/15/16.
 */
public class EditProfileActivity extends AppCompatActivity {
    public String TAG = "CreateProfile";
    ImageView profile_face;
    TextView user_name_me, age_edit_me, about_me_row_me, email_create_edit_me;
    EditText location_create_edit_me, about_me_edit_me, gender_create_edit_me;
    Button saveButton;
    double[] location = new double[2];
    private DatabaseReference mDatabase;
    UserModel userModel;
   private ArmsSpinner armsSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);
        setProfileIds();

        profile_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               HelpUtils.openUploadProfilePicturesActivity(EditProfileActivity.this);
            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_string_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        armsSpinner.setAdapter(genderAdapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//            updateUserData(Context context, String userId, String username, String profileImageURL, String email, double[] location, String age, String gender, String aboutme)
                UserManagement.writeUserProfile(EditProfileActivity.this, userModel.getUid(), user_name_me.getText().toString(),
                        userModel.getProfileImageURL(), email_create_edit_me.getText().toString(), UserManagement.updatedUserLocation(EditProfileActivity.this), age_edit_me.getText().toString(),
                        armsSpinner.getSelectedItem().toString()
                        , about_me_edit_me.getText().toString());

                HelpUtils.openProfilePage(EditProfileActivity.this);

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [START single_value_read]
        final String userId = HelpUtils.getCurrentUser().getUid();
        mDatabase.child("user-profiles").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        UserModel uModel = dataSnapshot.getValue(UserModel.class);
                        userModel = uModel;
                        if (uModel == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");

                        } else {

                            user_name_me.setText(uModel.getDisplayName());
                            email_create_edit_me.setText(uModel.getEmail());
                            age_edit_me.setText(uModel.getAge());

                            armsSpinner.setSelection(HelpUtils.getGenderPosition(uModel.getGender(), EditProfileActivity.this));
                            about_me_edit_me.setText(uModel.getAboutme());
                            location[0] = uModel.getLocation0();
                            location[1] = uModel.getLocation1();
                            location_create_edit_me.setText(LocationUtils.getCityAndState(EditProfileActivity.this, uModel.getLocation0(), uModel.getLocation1()).getAddressLine(1));    //  http://developer.android.com/reference/android/location/Address.html
//                            new DownloadImageTask(profile_face).execute(uModel.getProfileImageURL());
                            Glide.with(EditProfileActivity.this)
                                    .load(uModel.getProfileImageURL())
                                    .into(profile_face);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });


    }

    private void setProfileIds() {
        armsSpinner = (ArmsSpinner) findViewById(R.id.gender_edit_me_spinner);
        profile_face = (ImageView) findViewById(R.id.profile_face);
        user_name_me = (TextView) findViewById(R.id.user_name_me);
        email_create_edit_me = (TextView) findViewById(R.id.email_create_edit_me);

        age_edit_me = (TextView) findViewById(R.id.age_edit_me);
        location_create_edit_me = (EditText) findViewById(R.id.location_create_edit_me);
        about_me_row_me = (TextView) findViewById(R.id.location_me);
        about_me_edit_me = (EditText) findViewById(R.id.about_me_edit_me);
        saveButton = (Button) findViewById(R.id.save_profile);
    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
