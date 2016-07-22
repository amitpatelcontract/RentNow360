package com.firebase.samples.logindemo.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by arms on 2/14/16.
 */
public class UserManagement {
    public static String TAG = "UserManagement";
    private static DatabaseReference mDatabase;
//    public static void updateUserData(Context context, String userId, String username, String profileImageURL, String email, double[] location, String age, String gender, String aboutme) {

    // [START write_fan_out]// context, user.getUid(), user.getDisplayName(), profileImageURL, user.getEmail(), location);
    public static void writeUserProfile(Activity activity, String userId, String username, String profileImageURL, String email, double[] location, String age, String gender, String aboutme) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        String key = mDatabase.child("users").push().getKey();

        UserModel userModel = new UserModel(userId, username, profileImageURL, email, location[0], location[1], age, gender, aboutme);
        Map<String, Object> userValues = userModel.toMap();
        // check
        mDatabase.child("user-profiles").child(userId).setValue(userValues);
        mDatabase.child("geolocations").child(userId).child("location").child("location0").setValue(location[0]);
        mDatabase.child("geolocations").child(userId).child("location").child("location1").setValue(location[1]);

        HelpUtils.openCreateProfile(activity);
    }
    // update/ edit userdata
//    public static void updateUserData(Context context, String userId, String username, String profileImageURL, String email, double[] location, String age, String gender, String aboutme) {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
////        String key = mDatabase.child("users").push().getKey();
//        // Create new post at /user-posts/$userid/$postid and at
//        // /posts/$postid simultaneously
//
//        UserModel userModel = new UserModel(userId, username, profileImageURL, email, location[0], location[1] , age, gender, aboutme);
//        Map<String, Object> userValues = userModel.toEditUserMap();
//
//        mDatabase.child("user-profiles").child(userId).setValue(userValues);
//        mDatabase.child("geolocations").child(userId).child("location").child("location0").setValue(location[0]);
//        mDatabase.child("geolocations").child(userId).child("location").child("location1").setValue(location[1]);
//
//
//    }


    public static double[] updatedUserLocation(Context context) {
        double location[] = LocationUtils.getLocationManager(context).loc;
        return location;

        /*Using setValue() in this way overwrites data at the specified location, including any child nodes. However, you can still update a child without rewriting the entire object. If you want to allow users to update their profiles you could update the username as follows:*/
//        mDatabase.child("users").child(userId).child("username").setValue(userValues);


    }


    // geting userdata using user's UID/Key for userobject
    public static void getCurrentUserData(final Activity activity) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [START single_value_read]
        final String userId = HelpUtils.getCurrentUser().getUid();
        mDatabase.child("user-profiles").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        UserModel userModel = dataSnapshot.getValue(UserModel.class);

                        // [START_EXCLUDE]
                        if (userModel == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");

                        } else {
                            HelpUtils.openProfilePage(activity);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });

    }
    //    public static void updateUserData(Context context, String userId, String username, String profileImageURL, String email, double[] location, String age, String gender, String aboutme) {

    // setting up profile from Firebase object when u login
    public static void setUserprofile(FirebaseUser user, Activity activity) {

        // TODO: 5/28/16 I am not sure if this is the right way but I have to sign out and sign in userdata management
//         reAuthenticateUser();

        if (user != null) {


            double[] location = UserManagement.updatedUserLocation(activity);

            String profileImageURL;
            if (user.getPhotoUrl() != null)
                profileImageURL = (String) user.getPhotoUrl().toString();
            else
                profileImageURL = "";
            if (location != null)
                UserManagement.writeUserProfile(activity, user.getUid(), user.getDisplayName(), profileImageURL, user.getEmail(), location, "", "", "");
            else
                Toast.makeText(activity, "Location can not be found", Toast.LENGTH_LONG).show();

//            if (HelpUtils.getCurrentUser() != null)
//                HelpUtils.OpenListOfProducts(context);


        }
    }

    private static void reAuthenticateUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = FacebookAuthProvider.getCredential(user.getEmail());


// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                    }
                });
    }

    public static void signOut(FirebaseAuth mAuth) {
        mAuth.signOut();
    }


    // Users object basic
    public static void writeNewUser(Activity activity, String uid, FirebaseUser user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String photoUrl;
        if (user.getPhotoUrl()!=null)
          photoUrl = user.getPhotoUrl().toString();
        else
        photoUrl ="";

        if (photoUrl == null)
            photoUrl = "";
        Users users = new Users(user.getUid(), user.getDisplayName(), photoUrl, user.getEmail());

        mDatabase.child("users").child(uid).setValue(users);
        HelpUtils.openCreateProfile(activity);

    }
}
