//package com.firebase.samples.logindemo.androidchat;
//
//import android.app.Dialog;
//import android.app.ListActivity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.DataSetObserver;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.firebase.samples.logindemo.R;
//import com.firebase.samples.logindemo.firebasereferenceurls.FirebaseUrls;
//import com.firebase.samples.logindemo.models.Chat;
//import com.firebase.samples.logindemo.models.User;
//import com.firebase.samples.logindemo.utils.HelpUtils;
//
//
//public class MainActivity_old extends ListActivity {
//
//    private String mUsername, mAge, mGender;
//    private Firebase mFirebaseRef, firebaseUser;
//    private ValueEventListener mConnectedListener;
//    private ChatListAdapter mChatListAdapter;
//    private Button guysList;
//    private String userKey;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_oldd);
//        guysList = (Button) findViewById(R.id.showGuys);
//        guysList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showMaleUsers();
//            }
//        });
//        // Make sure we have a mUsername
//        setupUsername();
//
//        setTitle("Chatting as " + mUsername);
//
//        // Setup our Firebase mFirebaseRef
//        mFirebaseRef = new Firebase(FirebaseUrls.FIREBASE_URL).child("chatd");
//
//
//        firebaseUser = new Firebase(FirebaseUrls.FIREBASE_USERS_URL);
//
//
//        // Setup our input methods. Enter key on the keyboard or pushing the send button
//        EditText inputText = (EditText) findViewById(R.id.messageInput);
//        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                    sendMessage();
//                }
//                return true;
//            }
//        });
//
//        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendMessage();
//            }
//        });
//
//    }
//
//    private void setupUser() {
//
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
//                R.drawable.lips);//your image
////        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
////        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
////        bmp.recycle();
////        byte[] byteArray = bYtE.toByteArray();
////        String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
//
//
//        String imageFile = HelpUtils.BitMapToString(bmp);
//
//        // new Firebase.CompletionListener()  for acknolegment
//        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
//        if (prefs.getString("username", null) == null)
////        firebaseUser.push().setValue(new User(30, mUsername, mGender, "location Ny", imageFile));
//            userKey = firebaseUser.push().getKey();
//
//
//        prefs.edit().putString("userKey", userKey).commit();
//
//    }
//
//    private void showMaleUsers() {
//        startActivity(new Intent(MainActivity_old.this, MaleUserList.class));
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
//        final ListView listView = getListView();
//        // Tell our list adapter that we only want 50 messages at a time
//        mChatListAdapter = new ChatListAdapter(mFirebaseRef.limit(50), this, R.layout.chat_message, mUsername);
//        listView.setAdapter(mChatListAdapter);
//        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                listView.setSelection(mChatListAdapter.getCount() - 1);
//            }
//        });
//
//        // Finally, a little indication of connection status
//        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                boolean connected = (Boolean) dataSnapshot.getValue();
//                if (connected) {
//                    Toast.makeText(MainActivity_old.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity_old.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                // No-op
//            }
//        });
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
//        mChatListAdapter.cleanup();
//    }
//
//    private void setupUsername() {
//
//
//        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
//
//        mUsername = prefs.getString("username", null);
//        mGender = prefs.getString("gender", null);
//        if (mUsername == null) {
//
//            prefs.edit().putString("username", mUsername).commit();
//
//            if (mUsername == null) {
//                // custom dialog
//                final Dialog dialog = new Dialog(MainActivity_old.this);
//                dialog.setContentView(R.layout.user_name_dialog);
//
//                // set the custom dialog components - text, image and button
//                final EditText username_edit = (EditText) dialog.findViewById(R.id.username_edit);
//                final EditText age_edit = (EditText) dialog.findViewById(R.id.age_edit);
//                final EditText gender_edit = (EditText) dialog.findViewById(R.id.gender_edit);
//                Button dialogButton = (Button) dialog.findViewById(R.id.set_user);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
//                        mUsername = prefs.getString("username", null);
//                        mGender = prefs.getString("gender", null);
//
//                        if (mUsername == null) {
//                            // Assign a random user name if we don't have one saved.
//                            mUsername = username_edit.getText().toString();
//                            mAge = age_edit.getText().toString();
//                            mGender = gender_edit.getText().toString();
//                            setupUser();
//                            prefs.edit().putString("username", mUsername).commit();
//                            prefs.edit().putString("gender", mGender).commit();
//                        }
//
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        }
//    }
//
//
//    private void sendMessage() {
//        EditText inputText = (EditText) findViewById(R.id.messageInput);
//        String input = inputText.getText().toString();
//        if (!input.equals("")) {
//            // Create our 'model', a Chat object
//            Chat chat = new Chat(input, mUsername);
//            // Create a new, auto-generated child of that chat location, and save our chat data there
//            mFirebaseRef.push().setValue(chat);
//
//            inputText.setText("");
//        }
//    }
//}
