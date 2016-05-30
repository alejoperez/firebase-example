package com.test.firebase.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements IBaseView{


    @Override
    public void replaceFragment(@IdRes int idFrameLayout, Fragment fragment) {
        try{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(idFrameLayout, fragment);
            fragmentTransaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
