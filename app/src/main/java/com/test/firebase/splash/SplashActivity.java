package com.test.firebase.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.test.firebase.R;
import com.test.firebase.base.BaseActivity;
import com.test.firebase.home.HomeActivity;
import com.test.firebase.main.MainActivity;

public class SplashActivity extends BaseActivity implements ISplashView {

    private ISplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadData();
        startSplash();
    }

    @Override
    public void loadData() {
        splashPresenter = new SplashPresenter(this);
    }

    @Override
    public void startSplash() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                splashPresenter.checkCurrentUser();
            }
        }, 2000);
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
