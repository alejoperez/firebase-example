package com.test.firebase.regiter;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface IRegisterView {
    void onRegisterSuccess();
    void onRegisterFail(@NonNull Task<AuthResult> task);
    boolean isValidForm();
    String getEmail();
    String getPassword();
    void goToMainActivity();
}
