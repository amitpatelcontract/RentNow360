package com.firebase.samples.logindemo.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by arms on 7/11/16.
 */
public class FirebaseUtils {
    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

//    public static Author getAuthor() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) return null;
//        String photoUrl;
//        if (user.getPhotoUrl()!=null)
//            photoUrl = user.getPhotoUrl().toString();
//        else
//            photoUrl = "";
//        return new Author(user.getDisplayName(), photoUrl, user.getUid());
//    }

    public static DatabaseReference getCurrentUserRef() {
        String uid = getCurrentUserId();
        if (uid != null) {
            return getBaseRef().child("people").child(getCurrentUserId()).child("posts");
        }
        return null;
    }

    public static DatabaseReference getPropertyRef() {
        return getBaseRef().child("property");
    }

    public static String getPostsPath() {
        return "posts/";
    }

    public static DatabaseReference getUsersRef() {
        return getBaseRef().child("users");
    }

    public static String getPeoplePath() {
        return "people/";
    }

    public static DatabaseReference getPeopleRef() {
        return getBaseRef().child("people");
    }

    public static DatabaseReference getCommentsRef() {
        return getBaseRef().child("comments");
    }

    public static DatabaseReference getFeedRef() {
        return getBaseRef().child("feed");
    }

    public static DatabaseReference getLikesRef() {
        return getBaseRef().child("likes");
    }

    public static DatabaseReference getFollowersRef() {
        return getBaseRef().child("followers");
    }
}
