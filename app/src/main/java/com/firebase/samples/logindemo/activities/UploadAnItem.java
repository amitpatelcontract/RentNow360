//package com.firebase.samples.logindemo.activities;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.provider.MediaStore.MediaColumns;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//
//import com.firebase.samples.logindemo.R;
//import com.firebase.samples.logindemo.utils.HelpUtils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//
////import com.firebase.client.Firebase;
////import com.firebase.samples.logindemo.utils.NShopConstants;
//
//
///**
// * Ø
// * Created by arms on 2/16/16.
// */
//
//public class UploadAnItem extends Activity {
//
//    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
//    private ArrayList<String> productImagesList   = new ArrayList<String>();
//    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5;
//    private ImageView imageClicked;
//
//    private Spinner spinner_amount, spinner_major_category, spinner_minor_category;
//    private EditText product_title, product_description, amount;
//    private Button uploadItem;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.upload_item);
//        setIdForTheView();
//        setEffects();
//        uploadItem = (Button)findViewById(R.id.publish);
//        uploadItem.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadItem();
//            }
//        });
//
//    }
//
//    private void uploadItem() {
//
//        setBitmapStringsForPush();
//
//        // String productDescription, String productId, ArrayList<String> productImagesList, double[] productLocation,
//        // String productMajorCategoryId, String productMinorCategoryId, String productPrice, String productPriceUnit
//        // String productTitle, String userIdFromFacebook, String userIdFromPush)
////        Firebase firebaseProduct = new Firebase(FirebaseUrls.FIREBASE_PRODUCT_URL);
////        firebaseProduct.push().setValue(new Product(product_description.getText().toString(), "product id", productImagesList,
////                HelpUtils.getuserDataFromSharedPreferance(UploadAnItem.this).getLocation(), spinner_major_category.getSelectedItem().toString(),
////                spinner_major_category.getSelectedItem().toString(), amount.getText().toString(), spinner_amount.getSelectedItem().toString(),
////                product_title.getText().toString(), HelpUtils.getuserDataFromSharedPreferance(UploadAnItem.this).getId(),
////                HelpUtils.getUniqueUserId(UploadAnItem.this)));
////
////        final String uniqueProductId = firebaseProduct.push().getKey();
////        HelpUtils.setUniqueProductId(uniqueProductId, UploadAnItem.this);
//
//    }
//
//    private void setBitmapStringsForPush() {
//        Bitmap bImage1 = ((BitmapDrawable) ivImage1.getDrawable()).getBitmap();
//        Bitmap bImage2 = ((BitmapDrawable) ivImage2.getDrawable()).getBitmap();
//        Bitmap bImage3 = ((BitmapDrawable) ivImage3.getDrawable()).getBitmap();
//        Bitmap bImage4 = ((BitmapDrawable) ivImage4.getDrawable()).getBitmap();
//        Bitmap bImage5 = ((BitmapDrawable) ivImage5.getDrawable()).getBitmap();
//        productImagesList.add(HelpUtils.BitMapToString(bImage1));
//        productImagesList.add(HelpUtils.BitMapToString(bImage2));
//        productImagesList.add(HelpUtils.BitMapToString(bImage3));
//        productImagesList.add(HelpUtils.BitMapToString(bImage4));
//        productImagesList.add(HelpUtils.BitMapToString(bImage5));
//    }
//
//    private void setIdForTheView() {
//        ivImage1 = (ImageView) findViewById(R.id.ivImage1);
//        ivImage2 = (ImageView) findViewById(R.id.ivImage2);
//        ivImage3 = (ImageView) findViewById(R.id.ivImage3);
//        ivImage4 = (ImageView) findViewById(R.id.ivImage4);
//        ivImage5 = (ImageView) findViewById(R.id.ivImage5);
//
//        spinner_amount = (Spinner) findViewById(R.id.spinner_amount);
//        spinner_major_category = (Spinner) findViewById(R.id.spinner_major_category);
//        spinner_minor_category = (Spinner) findViewById(R.id.spinner_minor_category);
//
//        product_title = (EditText) findViewById(R.id.product_title);
//        product_description = (EditText) findViewById(R.id.product_description);
//        amount = (EditText) findViewById(R.id.amount);
//
//
//    }
//
//    private void setEffects() {
//        ivImage1.setOnClickListener(grabImageAndSetOnImageView);
//        ivImage2.setOnClickListener(grabImageAndSetOnImageView);
//        ivImage3.setOnClickListener(grabImageAndSetOnImageView);
//        ivImage4.setOnClickListener(grabImageAndSetOnImageView);
//        ivImage5.setOnClickListener(grabImageAndSetOnImageView);
//
//
//        Resources res = getResources();
//        ArrayAdapter<String> amountTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.amount_type));
//        spinner_amount.setAdapter(amountTypeAdapter);
//        spinner_amount.setSelection(0);
//
//
//        ArrayAdapter<String> majorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.major_categories));
//        spinner_major_category.setAdapter(majorAdapter);
//        spinner_major_category.setSelection(0);
//
//
//        ArrayAdapter<String> minorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.major_categories));
//        spinner_minor_category.setAdapter(minorAdapter);
//        spinner_minor_category.setSelection(0);
//    }
//
//
//    ImageView.OnClickListener grabImageAndSetOnImageView = new ImageView.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectImage();
//            imageClicked = (ImageView) v;
//        }
//    };
//
//    private void selectImage() {
//        final CharSequence[] items = {"Take Photo", "Choose from Library",
//                "Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(UploadAnItem.this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (items[item].equals("Take Photo")) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, REQUEST_CAMERA);
//                } else if (items[item].equals("Choose from Library")) {
//                    Intent intent = new Intent(
//                            Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(
//                            Intent.createChooser(intent, "Select File"),
//                            SELECT_FILE);
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == SELECT_FILE)
//                onSelectFromGalleryResult(data);
//            else if (requestCode == REQUEST_CAMERA)
//                onCaptureImageResult(data);
//        }
//    }
//
//    private void onCaptureImageResult(Intent data) {
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//
//        File destination = new File(Environment.getExternalStorageDirectory(),
//                System.currentTimeMillis() + ".jpg");
//
//        FileOutputStream fo;
//        try {
//            destination.createNewFile();
//            fo = new FileOutputStream(destination);
//            fo.write(bytes.toByteArray());
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        imageClicked.setImageBitmap(thumbnail);
//    }
//
//    @SuppressWarnings("deprecation")
//    private void onSelectFromGalleryResult(Intent data) {
//        Uri selectedImageUri = data.getData();
//        String[] projection = {MediaColumns.DATA};
//        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
//                null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//        cursor.moveToFirst();
//
//        String selectedImagePath = cursor.getString(column_index);
//
//        Bitmap bm;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(selectedImagePath, options);
//        final int REQUIRED_SIZE = 200;
//        int scale = 1;
//        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
//            scale *= 2;
//        options.inSampleSize = scale;
//        options.inJustDecodeBounds = false;
//        bm = BitmapFactory.decodeFile(selectedImagePath, options);
//
//        imageClicked.setImageBitmap(bm);
//    }
//
//}
