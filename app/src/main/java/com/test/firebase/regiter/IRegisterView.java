package com.test.firebase.regiter;

public interface IRegisterView {
    void loadData();
    void onRegisterSuccess();
    void onRegisterFail();
    boolean isValidForm();
    String getEmail();
    String getPassword();
    void goToMainActivity();
}
