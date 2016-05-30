package com.test.firebase.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.firebase.R;
import com.test.firebase.home.HomeActivity;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startSplash();
    }

    @Override
    public void startSplash() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                openHomeActivity();
            }
        }, 2000);
    }

    @Override
    public void openHomeActivity() {
        Intent i = new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}
