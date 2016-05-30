package com.test.firebase.base;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

public interface IBaseView {
    void replaceFragment(@IdRes int idFrameLayout, Fragment fragment);
}
