package com.test.firebase.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.test.firebase.R;
import com.test.firebase.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment implements ILoginView , OnCompleteListener<AuthResult> {

    @Bind(R.id.login_edit_text_email)
    EditText editTextEmail;

    @Bind(R.id.login_edit_text_password)
    EditText editTextPassword;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @OnClick(R.id.login_button)
    public void login() {
        if (isValidForm()) {
            FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(getEmail(),getPassword())
                    .addOnCompleteListener(this);
        } else {
            Toast.makeText(getContext(),R.string.login_empty_form,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isValidForm() {
        return !TextUtils.isEmpty(getEmail())
                && !TextUtils.isEmpty(getPassword());
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            onLoginSuccess();
        } else {
            onLoginFail(task);
        }
    }

    @Override
    public void onLoginSuccess() {
        goToMainActivity();
    }

    @Override
    public void onLoginFail(@NonNull Task<AuthResult> task) {
        if (task.getException() != null && !TextUtils.isEmpty(task.getException().getMessage())) {
            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void goToMainActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
