package com.test.firebase.profile;


public interface IProfileView {
    void loadData();
    void loadView(String name, String email);
    String getName();
    void onUpdateProfileSuccess();
    void onUpdateProfileFail();
}
