//package com.firebase.samples.logindemo.androidchat;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.Query;
//import com.firebase.client.ValueEventListener;
//import com.firebase.samples.logindemo.R;
//import com.firebase.samples.logindemo.firebasereferenceurls.FirebaseUrls;
//import com.firebase.samples.logindemo.models.User;
//import com.firebase.samples.logindemo.utils.HelpUtils;
//
//import java.util.ArrayList;
//
//
//
///**
// * Created by arms on 2/7/16.
// */
//public class MaleUserList extends Activity {
//    private static final String TAG = "MaleUserList";
//    private Firebase maleFirebaseRef;
//    private ListView lv;
//    private ImageView imageview;
//    Bitmap decodedByte;
//
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.user_list);
//        imageview = (ImageView)findViewById(R.id.imageView);
//        maleFirebaseRef = new Firebase(FirebaseUrls.FIREBASE_USERS_URL);
//        Query qMale = maleFirebaseRef.orderByChild("gender").equalTo("Male");
//        final ArrayList<User> finalValues = new ArrayList<User>();
//        qMale.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    User users = postSnapshot.getValue(User.class);
//                    finalValues.add(users);
////                    if (users.getDrawable()!=null) {
////
////                          decodedByte = HelpUtils.stringToBitMap(users.getDrawable());
////                    }
//                    imageview.setImageBitmap(decodedByte);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//                Log.i(TAG, error.toString());
//            }
//
//        });
//        lv=(ListView) findViewById(R.id.listView);
////        lv.setAdapter(new UserAdapter(MaleUserList.this, finalValues));
//    }
//
//}
