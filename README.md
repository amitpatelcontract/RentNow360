*While the code in this repo is still valid, have a look at [FirebaseUI-Android](https://github.com/firebase/FirebaseUI-Android) for a more up to date library that handles Firebase Authentication on Android.*

# Firebase Login Demo for Android

This demo Android app demonstrates authenticating with Firebase Login for Facebook, Google, Twitter,
email & password, and anonymous.

![screenshot showing authentication provider buttons](/screenshot.png)

### Setup
0. Sign up and create a new [Firebase](https://www.firebase.com).
0. Create apps for each provider on their developer consoles. See the Firebase
   [authentication provider docs](https://www.firebase.com/docs/android/guide/user-auth.html#section-providers)
   for more details.
    - [Facebook](https://developers.facebook.com/docs/android/getting-started)
    - [Google](https://developers.google.com/+/mobile/android/getting-started)
    - [Twitter](https://apps.twitter.com/app/new)
0. Enable Facebook, Google, Twitter, Email, and Anonymous providers on the Firebase Dashboard for
   your app. See the
   [enabling providers section](https://www.firebase.com/docs/android/guide/user-auth.html#section-enable-providers)
   of the Firebase user authentication docs for more details.
0. Populate all of the values in [`res/values/keys.xml`](/app/src/main/res/values/keys.xml).

-> to unlick / delete account
https://firebase.google.com/docs/auth/android/account-linking#link-auth-provider-credentials-to-a-user-account
Unlink an auth provider from a user account

You can unlink an auth provider from an account, so that the user can no longer sign in with that provider.

To unlink an auth provider from a user account, pass the provider ID to the unlink method. You can get the provider IDs of the auth providers linked to a user by calling getProviderData.

FirebaseAuth.getInstance().getCurrentUser().unlink(providerId)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // Auth provider unlinked from account
                }
            }
        });
        Delete a user

        You can delete a user account with the delete method. For example:

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                        }
                    }
                });

                Update a user's profile

                You can update a user's basic profile information—the user's display name and profile photo URL—with the updateProfile method. For example:

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName("Jane Q. User")
                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                }
                            }
                        });

                      -->   Configure Firebase Database Rules

                        The Realtime Database provides a declarative rules language that allows you to define how your data should be structured, how it should be indexed, and when your data can be read from and written to. By default, read and write access to your database is restricted except to authenticated users, so you should configure your rules before getting started.

                       -->  Prepare for Launch

                        Before launching your app, we recommend walking through our launch checklist to make sure your app is ready to go!

                        --> remote config
                         * for version update






                         QA :

                       bugs:-  when u logging again with same account, it is recreating the personal profile




                       Requirements:
