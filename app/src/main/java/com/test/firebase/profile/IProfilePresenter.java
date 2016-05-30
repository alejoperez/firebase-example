package com.test.firebase.profile;

public interface IProfilePresenter {
    void loadUserProfile();
    boolean hasProfileChanged(String name);
    void updateProfile(String name);
    void logout();
}
