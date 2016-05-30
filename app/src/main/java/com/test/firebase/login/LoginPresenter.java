package com.test.firebase.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements ILoginPresenter, OnCompleteListener<AuthResult> {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            loginView.onLoginSuccess();
        } else {
            loginView.onLoginFail();
        }
    }
}
