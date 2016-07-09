package com.firebase.samples.logindemo.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.auth.BaseActivity;
import com.firebase.samples.logindemo.extra.MyDownloadService;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by arms on 5/28/16.
 */
public class UploadProfilePicturesActivity extends BaseActivity implements
        View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private static final String TAG = "UploadProfilePicturesActivity";

    private static final int RC_TAKE_PICTURE = 101;
    private static final int RC_STORAGE_PERMS = 102;

    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";

    private BroadcastReceiver mDownloadReceiver;
    private ProgressDialog mProgressDialog;
//    private FirebaseAuth mAuth;

    private Uri mDownloadUrl = null;
    private Uri mFileUri = null;

    // [START declare_ref]
    private StorageReference mStorageRef;
    private ImageView imageview1, imageview2,imageview3,imageview4,imageview5,imageview6;
    // [END declare_ref]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_profile_pic);
setViewIds();
        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();

        // Initialize Firebase Storage Ref
        // [START get_storage_ref]
        mStorageRef = FirebaseStorage.getInstance().getReference();
        // [END get_storage_ref]

        // Click listeners
//        findViewById(R.id.button_camera).setOnClickListener(this);
//        findViewById(R.id.button_sign_in).setOnClickListener(this);
//        findViewById(R.id.button_download).setOnClickListener(this);

        // Restore instance state
        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(KEY_FILE_URI);
            mDownloadUrl = savedInstanceState.getParcelable(KEY_DOWNLOAD_URL);
        }

        // Download receiver
        mDownloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArmsLogs.i(TAG, "downloadReceiver:onReceive:" + intent);
                hideProgressDialog();

                if (MyDownloadService.ACTION_COMPLETED.equals(intent.getAction())) {
                    String path = intent.getStringExtra(MyDownloadService.EXTRA_DOWNLOAD_PATH);
                    long numBytes = intent.getLongExtra(MyDownloadService.EXTRA_BYTES_DOWNLOADED, 0);

                    // Alert success
                    showMessageDialog("Success", String.format(Locale.getDefault(),
                            "%d bytes downloaded from %s", numBytes, path));
                }

                if (MyDownloadService.ACTION_ERROR.equals(intent.getAction())) {
                    String path = intent.getStringExtra(MyDownloadService.EXTRA_DOWNLOAD_PATH);

                    // Alert failure
                    showMessageDialog("Error", String.format(Locale.getDefault(),
                            "Failed to download from %s", path));
                }
            }
        };
    }

    private void setViewIds() {
       imageview1 =  (ImageView) findViewById(R.id.imageView1);
        imageview2 =  (ImageView) findViewById(R.id.imageView2);
        imageview3 =  (ImageView) findViewById(R.id.imageView3);
        imageview4 =  (ImageView) findViewById(R.id.imageView4);
        imageview5 =  (ImageView) findViewById(R.id.imageView5);
        imageview6 =  (ImageView) findViewById(R.id.imageView6);
        imageview1.setOnClickListener(this);
        imageview2.setOnClickListener(this);
        imageview3.setOnClickListener(this);
        imageview4.setOnClickListener(this);
        imageview5.setOnClickListener(this);
        imageview6.setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        updateUI(mAuth.getCurrentUser());

        // Register download receiver
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mDownloadReceiver, MyDownloadService.getIntentFilter());
    }


    @Override
    public void onStop() {
        super.onStop();

        // Unregister download receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mDownloadReceiver);
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        out.putParcelable(KEY_FILE_URI, mFileUri);
        out.putParcelable(KEY_DOWNLOAD_URL, mDownloadUrl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArmsLogs.i(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        if (requestCode == RC_TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                if (mFileUri != null) {
                    uploadFromUri(mFileUri);
                } else {
                    ArmsLogs.i(TAG, "File URI is null");
                }
            } else {
                Toast.makeText(this, "Taking picture failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // [START upload_from_uri]
    private void uploadFromUri(Uri fileUri) {
        ArmsLogs.i(TAG, "uploadFromUri:src:" + fileUri.toString());

        // [START get_child_ref]
        // Get a reference to store file at photos/<FILENAME>.jpg
        final StorageReference photoRef = mStorageRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("photos")
                .child(fileUri.getLastPathSegment());
        // [END get_child_ref]

        // Upload file to Firebase Storage
        // [START_EXCLUDE]
        showProgressDialog();
        // [END_EXCLUDE]
        ArmsLogs.i(TAG, "uploadFromUri:dst:" + photoRef.getPath());
        photoRef.putFile(fileUri)
                .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Upload succeeded
                        ArmsLogs.i(TAG, "uploadFromUri:onSuccess");

                        // Get the public download URL
                        mDownloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                        ArmsLogs.i(TAG, "onSuccess: "+mDownloadUrl.toString());
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        updateUI(mAuth.getCurrentUser());
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Upload failed
                        ArmsLogs.e(TAG, "uploadFromUri:onFailure: "+ exception);

                        mDownloadUrl = null;

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        Toast.makeText(UploadProfilePicturesActivity.this, "Error: upload failed",
                                Toast.LENGTH_SHORT).show();
                        updateUI(mAuth.getCurrentUser());
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END upload_from_uri]

    @AfterPermissionGranted(RC_STORAGE_PERMS)
    private void launchCamera(int i) {
        ArmsLogs.i(TAG, "launchCamera");

        // Check that we have permission to read images from external storage.
        String perm = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !EasyPermissions.hasPermissions(this, perm)) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_storage),
                    RC_STORAGE_PERMS, perm);
            return;
        }

        // Create intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Choose file storage location
//        File file = new File(Environment.getExternalStorageDirectory(), UUID.randomUUID().toString() + ".jpg");
        File file = new File(Environment.getExternalStorageDirectory(), FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+String.valueOf(i) + ".jpg");
        ArmsLogs.i(TAG, "Launch camera, file: "+file.toString());
        mFileUri = Uri.fromFile(file);
        ArmsLogs.i(TAG, "Launch camera, file: "+mFileUri.toString());
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

        // Launch intent
        startActivityForResult(takePictureIntent, RC_TAKE_PICTURE);
    }



    private void beginDownload() {
        // Get path
        String path = "photos/" + mFileUri.getLastPathSegment();

        // Kick off download service
        Intent intent = new Intent(this, MyDownloadService.class);
        intent.setAction(MyDownloadService.ACTION_DOWNLOAD);
        intent.putExtra(MyDownloadService.EXTRA_DOWNLOAD_PATH, path);
        startService(intent);

        // Show loading spinner
        showProgressDialog();
    }

    private void updateUI(FirebaseUser user) {
        // Signed in or Signed out
//        if (user != null) {
////            findViewById(R.id.layout_signin).setVisibility(View.GONE);
//            findViewById(R.id.layout_storage).setVisibility(View.VISIBLE);
//        } else {
////            findViewById(R.id.layout_signin).setVisibility(View.VISIBLE);
//            findViewById(R.id.layout_storage).setVisibility(View.GONE);
//        }

        // Download URL and Download button
        if (mDownloadUrl != null) {
//            ((TextView) findViewById(R.id.picture_download_uri))
//                    .setText(mDownloadUrl.toString());
            if (clickedPosition == 1)
            Glide.with(this)
                    .load(mDownloadUrl).centerCrop()
                    .into(imageview1);
          else  if (clickedPosition == 2)
                Glide.with(this)
                        .load(mDownloadUrl).centerCrop()
                        .into(imageview2);
        else    if (clickedPosition == 3)
                Glide.with(this)
                        .load(mDownloadUrl).centerCrop()
                        .into(imageview3);
         else   if (clickedPosition == 4)
                Glide.with(this)
                        .load(mDownloadUrl).centerCrop()
                        .into(imageview4);
          else  if (clickedPosition == 5)
                Glide.with(this)
                        .load(mDownloadUrl).centerCrop()
                        .into(imageview5);
          else  if (clickedPosition == 6)
                Glide.with(this)
                        .load(mDownloadUrl).centerCrop()
                        .into(imageview6);

            ArmsLogs.i(TAG, "updateUI, mDownloadUrl: "+mDownloadUrl.toString());
//            findViewById(R.id.layout_download).setVisibility(View.VISIBLE);
        } else {
//            ((TextView) findViewById(R.id.picture_download_uri))
//                    .setText(null);
//            findViewById(R.id.layout_download).setVisibility(View.GONE);
        }
    }

    private void showMessageDialog(String title, String message) {
        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create();
        ad.show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    private static int clickedPosition = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView1:
                clickedPosition = 1;
                launchCamera(1);

                break;
            case R.id.imageView2:
                clickedPosition = 2;
                launchCamera(2);

                break;
            case R.id.imageView3:
                clickedPosition = 3;
                launchCamera(3);
                break;
            case R.id.imageView4:
                clickedPosition = 4;
                launchCamera(4);
                break;
            case R.id.imageView5:
                clickedPosition = 5;
                launchCamera(5);
                break;
            case R.id.imageView6:
                clickedPosition = 6;
                launchCamera(6);
                break;
//            case R.id.button_sign_in:
//                signInAnonymously();
//                break;
//            case R.id.button_download:
//                beginDownload();
//                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {}

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {}
}
