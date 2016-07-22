package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.utils.ArmsDialog;
import com.firebase.samples.logindemo.utils.HelpUtils;
import com.firebase.samples.logindemo.utils.LocationUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by apatel on 2/11/16.
 */
public class ProfileActivity extends AppCompatActivity {


    private static String TAG = "ProfileActivity";


    ImageView profile_face;
    TextView user_name_me, email_edit_me, gender_edit_me, age_edit_me, location_edit_me, about_me_edit_me, edit_profile;
    double[] location = new double[2];
    private DatabaseReference mDatabase;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        setProfileIds();
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpUtils.openEditProfilePage(ProfileActivity.this);
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [START single_value_read]
        if (HelpUtils.getCurrentUser()!=null){
        if (HelpUtils.getCurrentUser().getUid()!=null) {
            final String userId = HelpUtils.getCurrentUser().getUid();
            mDatabase.child("user-profiles").child(userId).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            UserModel uModel = dataSnapshot.getValue(UserModel.class);
                            userModel = uModel;
                            // [START_EXCLUDE]
                            if (uModel == null) {
                                // User is null, error out
                                Log.e(TAG, "User " + userId + " is unexpectedly null");

                            } else {

                                user_name_me.setText(userModel.getDisplayName());
                                email_edit_me.setText(userModel.getEmail());
                                gender_edit_me.setText(userModel.getGender());
                                age_edit_me.setText(userModel.getAge());
                                about_me_edit_me.setText(userModel.getAboutme());
                                location[0] = userModel.getLocation0();
                                location[1] = userModel.getLocation1();
                                    location_edit_me.setText(LocationUtils.getCityAndState(ProfileActivity.this, location[0], location[1]).getAddressLine(1));    //  http://developer.android.com/reference/android/location/Address.html
//                                new DownloadImageTask(profile_face).execute(userModel.getProfileImageURL());
                                Glide.with(ProfileActivity.this)
                                        .load(userModel.getProfileImageURL())
                                        .into(profile_face);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        }
                    });
        }}
        else
        {
              ArmsDialog.myCustomDialog(ProfileActivity.this,  getResources().getString(R.string.you_have_been_loggedout), getResources().getString(R.string.log_in_please));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        HelpUtils.OpenListOfProducts(ProfileActivity.this);
        finish();

    }

    private void setProfileIds() {
        profile_face = (ImageView) findViewById(R.id.profile_face);
        user_name_me = (TextView) findViewById(R.id.user_name_me);
        email_edit_me = (TextView) findViewById(R.id.email_edit_me);
        gender_edit_me = (TextView) findViewById(R.id.gender_edit_me);
        age_edit_me = (TextView) findViewById(R.id.age_edit_me);
        location_edit_me = (TextView) findViewById(R.id.location_edit_me);
        about_me_edit_me = (TextView) findViewById(R.id.about_me_edit_me);
        edit_profile = (TextView) findViewById(R.id.edit_profile);
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
}


/**
 * Adding custom view to tab
 * <p/>
 * Adding fragments to ViewPager
 *
 * @param viewPager
 */
//    private void setupTabIcons() {
//
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("ONE");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabTwo.setText("TWO");
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0);
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabThree.setText("THREE");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
//
////        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
////        tabFour.setText("Four");
////        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0);
////        tabLayout.getTabAt(3).setCustomView(tabFour);
//
//    }

/**
 * Adding fragments to ViewPager
 * @param viewPager
 */
//    private void setupViewPager(ViewPager viewPager) {
//        ProductPagerAdapter adapter = new ProductPagerAdapter(getSupportFragmentManager());
//        adapter.addFrag(new MyProductTabs(), "ONE");
//        adapter.addFrag(new MyProductTabs(), "TWO");
//        adapter.addFrag(new MyProductTabs(), "THREE");
////        adapter.addFrag(new MyProductTabs(), "FOUR");
//        viewPager.setAdapter(adapter);
//    }


//NLogs.i(TAG, user.getDisplayName());
//        NLogs.i(TAG, String.valueOf(user.getLocation()[0]));
//        NLogs.i(TAG, String.valueOf(user.getLocation()[1]));
//        NLogs.i(TAG, user.getCachedUserProfileFromHashMap(user.getCachedUserProfile()).getLast_name());
//        NLogs.i(TAG, user.getCachedUserProfileFromHashMap(user.getCachedUserProfile()).getFirst_name());
//        NLogs.i(TAG, user.getCachedUserProfileFromHashMap(user.getCachedUserProfile()).getGender());
//        NLogs.i(TAG, user.getCachedUserProfileFromHashMap(user.getCachedUserProfile()).getAge());