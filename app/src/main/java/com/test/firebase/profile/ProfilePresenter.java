package com.test.firebase.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfilePresenter implements IProfilePresenter, OnCompleteListener<Void> {

    private IProfileView profileView;

    private FirebaseUser user;

    private long time;

    public ProfilePresenter(IProfileView profileView) {
        this.profileView = profileView;
    }

    @Override
    public void loadUserProfile() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        profileView.loadView(user.getDisplayName(),user.getEmail());
    }

    @Override
    public boolean hasProfileChanged(String name) {
        return !name.equals(user.getDisplayName());
    }

    @Override
    public void updateProfile(String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates).addOnCompleteListener(this);
        time = System.currentTimeMillis();
    }


    @Override
    public void onComplete(@NonNull Task<Void> task) {
        Log.e("update","update = "+(System.currentTimeMillis()-time));
        if (task.isSuccessful()) {
            profileView.onUpdateProfileSuccess();
        } else {
            profileView.onUpdateProfileFail();
        }
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}
