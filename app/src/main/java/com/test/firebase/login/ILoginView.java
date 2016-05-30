package com.test.firebase.login;

public interface ILoginView {
    void loadData();
    String getEmail();
    String getPassword();
    boolean isValidForm();
    void onLoginSuccess();
    void onLoginFail();
    void goToMainActivity();
}
