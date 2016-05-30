package com.test.firebase.splash;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenter implements ISplashPresenter {

    private ISplashView splashView;

    public SplashPresenter(ISplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            splashView.openMainActivity();
        } else {
            splashView.openHomeActivity();
        }

    }
}
