package com.firebase.samples.logindemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.activities.ListofProducts;
import com.firebase.samples.logindemo.adapters.UserRecycleAdapter;
import com.firebase.samples.logindemo.extra.DividerItemDecoration;
import com.firebase.samples.logindemo.listener.RecyclerTouchListener;
import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
     private RecyclerView recyclerView;
    private UserRecycleAdapter mAdapter;
    private Context context;
    private DatabaseReference mDatabase;
    private String TAG = "OneFragment";

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    final ArrayList<UserModel> userModels = new ArrayList<UserModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_main_fragment);

        mDatabase.child("user-profiles").addChildEventListener(
                new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                        ArmsLogs.i(TAG, "User " + dataSnapshot.getValue());
                        // Get user value
                        UserModel uModel = dataSnapshot.getValue(UserModel.class);

                        if (uModel == null) {
                            // User is null, error out
                            ArmsLogs.i(TAG, "User " + " is unexpectedly null");

                        } else {
                            userModels.add(uModel);
                            ArmsLogs.i(TAG, "uModel sfsdvdsvsvs " + uModel.getDisplayName());

                        }

                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "postComments:onCancelled", databaseError.toException());

                    }
                });

        ArmsLogs.i(TAG, "User " + "here");

        mAdapter = new UserRecycleAdapter(userModels);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new ListofProducts.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                UserModel userModel = userModels.get(position);
                Toast.makeText(context, userModel.getDisplayName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return rootView;

    }







    /*    mDatabase.child("users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArmsLogs.e(TAG, "User " + dataSnapshot.getValue());
                        // Get user value
                        UserModel uModel = dataSnapshot.getValue(UserModel.class);

                        if (uModel == null) {
                            // User is null, error out
                            ArmsLogs.e(TAG, "User " + " is unexpectedly null");

                        } else {

                            ArmsLogs.e(TAG, "uModel " +  uModel.getDisplayName());

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });*/
}

