package com.test.firebase.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements ILoginPresenter, OnCompleteListener<AuthResult> {

    private ILoginView loginView;
    private long time;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this);
        time = System.currentTimeMillis();
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.e("Login","login = "+(System.currentTimeMillis()-time));
        if (task.isSuccessful()) {
            loginView.onLoginSuccess();
        } else {
            loginView.onLoginFail();
        }
    }
}
