package com.test.firebase.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.firebase.R;
import com.test.firebase.base.BaseActivity;
import com.test.firebase.home.HomeActivity;
import com.test.firebase.main.MainActivity;

public class SplashActivity extends BaseActivity implements ISplashView {

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadData();
        startSplash();
    }

    @Override
    public void loadData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void startSplash() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                checkCurrentUser();
            }
        }, 2000);
    }

    @Override
    public void checkCurrentUser() {
        if (user != null) {
            openMainActivity();
        } else {
            openHomeActivity();
        }
    }

    @Override
    public void openMainActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void openHomeActivity() {
        Intent i = new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}
