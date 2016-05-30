package com.test.firebase.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface ILoginView {
    String getEmail();
    String getPassword();
    boolean isValidForm();
    void onLoginSuccess();
    void onLoginFail(@NonNull Task<AuthResult> task);
    void goToMainActivity();
}
