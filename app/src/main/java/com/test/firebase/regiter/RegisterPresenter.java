package com.test.firebase.regiter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenter implements IRegisterPresenter, OnCompleteListener<AuthResult> {

    private IRegisterView registerView;
    private long time;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void register(String email, String password) {
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this);
        time = System.currentTimeMillis();
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.e("register","register = "+(System.currentTimeMillis()-time));
        if (task.isSuccessful()) {
            registerView.onRegisterSuccess();
        } else {
            registerView.onRegisterFail();
        }
    }
}
