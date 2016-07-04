//package com.firebase.samples.logindemo.androidchat;
//
//import android.app.ListActivity;
//import android.content.SharedPreferences;
//import android.database.DataSetObserver;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
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
//import com.firebase.samples.logindemo.firebasereferenceurls.NCHATIntents;
//import com.firebase.samples.logindemo.models.Chat;
//
//
///**
// * Created by arms on 2/8/16.
// */
//public class IndividualChat extends ListActivity {
//
//    private String userKey, userName, age;
//
//    private ValueEventListener mConnectedListener;
//    private ChatListAdapter mChatListAdapter;
//    private Firebase mFirebaseIndividualRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_oldd);
//
//
//        setTitle("Chatting as " + userKey);
//
//        userKey = getIntent().getExtras().getString(NCHATIntents.USER_KEY);
//        userName = getIntent().getExtras().getString(NCHATIntents.USER_NAME);
//        age = getIntent().getExtras().getString(NCHATIntents.USER_AGE);
//
//        // Setup our Firebase mFirebaseRef
//        SharedPreferences prefss = getSharedPreferences("ChatPrefs", 0);
//        userKey = prefss.getString("userKey", null);
//        mFirebaseIndividualRef = new Firebase(FirebaseUrls.FIREBASE_URL).child("userKey");//.child(userKey + userName + age);
//
//
////        mFirebaseIndividualRef.push().setValue("Chant started buddy");
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
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
//        final ListView listView = getListView();
//        // Tell our list adapter that we only want 50 messages at a time
//        mChatListAdapter = new ChatListAdapter(mFirebaseIndividualRef.limit(50), this, R.layout.chat_message, userName);
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
//        mConnectedListener = mFirebaseIndividualRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                boolean connected = (Boolean) dataSnapshot.getValue();
//                if (connected) {
//                    Toast.makeText(IndividualChat.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(IndividualChat.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
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
//        mFirebaseIndividualRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
//        mChatListAdapter.cleanup();
//    }
//
//
//    private void sendMessage() {
//        EditText inputText = (EditText) findViewById(R.id.messageInput);
//        String input = inputText.getText().toString();
//        if (!input.equals("")) {
//            // Create our 'model', a Chat object
//            Chat chat = new Chat(input, userName);
//            // Create a new, auto-generated child of that chat location, and save our chat data there
//            mFirebaseIndividualRef.push().setValue(chat);
//
//            inputText.setText("");
//        }
//    }
//}
