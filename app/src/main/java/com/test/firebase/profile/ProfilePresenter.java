package com.test.firebase.profile;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfilePresenter implements IProfilePresenter, OnCompleteListener<Void> {

    private IProfileView profileView;

    private FirebaseUser user;

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
        return name.equals(user.getDisplayName());
    }

    @Override
    public void updateProfile(String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates).addOnCompleteListener(this);
    }


    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            profileView.onUpdateProfileSuccess();
        } else {
            profileView.onUpdateProfileFail();
        }
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        profileView.onLogoutSuccess();
    }
}
