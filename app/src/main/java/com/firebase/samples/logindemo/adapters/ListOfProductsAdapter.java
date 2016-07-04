//package com.firebase.samples.logindemo.adapters;
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.firebase.client.Firebase;
//import com.firebase.client.Query;
//import com.firebase.samples.logindemo.R;
//import com.firebase.samples.logindemo.androidchat.FirebaseListAdapter;
//import com.firebase.samples.logindemo.extra.TextViewHolder;
//import com.firebase.samples.logindemo.models.Chat;
//import com.firebase.samples.logindemo.models.Product;
//
///**
// * Created by arms on 2/24/16.
// */
//public class ListOfProductsAdapter extends FirebaseRecyclerAdapter{
//
//// The mUsername for this client. We use this to indicate which messages originated from this user
//private String mUsername;
//    Class<TextViewHolder> mViewHolderClass;
//
//    /**
//     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
//     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
//     *                        instance of the corresponding view with the data from an instance of modelClass.
//     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
//     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
//     */
//    public ListOfProductsAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
//        super(modelClass, modelLayout, viewHolderClass, ref);
//    }
//
//    /**
//     * Each time the data at the given Firebase location changes, this method will be called for each item that needs
//     * to be displayed. The first two arguments correspond to the mLayout and mModelClass given to the constructor of
//     * this class. The third argument is the item's position in the list.
//     * <p/>
//     * Your implementation should populate the view using the data contained in the model.
//     *
//     * @param viewHolder The view to populate
//     * @param model      The object containing the data used to populate the view
//     * @param position   The position in the list of the view being populated
//     */
//    @Override
//    protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Object model, int position) {
//
//    }
//
//    /**
//     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
//     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
//     *                        instance of the corresponding view with the data from an instance of modelClass.
//     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
//     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
//     */
////    public ListOfProductsAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Firebase ref, String mUsername, Class<TextViewHolder> mViewHolderClass) {
////        super(modelClass, modelLayout, viewHolderClass, ref);
////        this.mUsername = mUsername;
////        this.mViewHolderClass = mViewHolderClass;
////    }
//
//
//    /**
//     * Each time the data at the given Firebase location changes, this method will be called for each item that needs
//     * to be displayed. The first two arguments correspond to the mLayout and mModelClass given to the constructor of
//     * this class. The third argument is the item's position in the list.
//     * <p/>
//     * Your implementation should populate the view using the data contained in the model.
//     *
//     * @param viewHolder The view to populate
//     * @param model      The object containing the data used to populate the view
//     * @param position   The position in the list of the view being populated
//     */
//
//}
