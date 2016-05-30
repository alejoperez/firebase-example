package com.test.firebase.profile;


public interface IProfileView {
    void loadData();
    void loadView();
    String getName();
    String getEmail();
    boolean hasProfileChanged();
}
