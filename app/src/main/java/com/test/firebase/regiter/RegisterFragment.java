package com.test.firebase.regiter;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment implements IRegisterView, OnCompleteListener<AuthResult> {

    @Bind(R.id.register_edit_text_name)
    EditText editTextName;

    @Bind(R.id.register_edit_text_email)
    EditText editTextEmail;

    @Bind(R.id.register_edit_text_password)
    EditText editTextPassword;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public String getName() {
        return editTextName.getText().toString();
    }

    @Override
    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @Override
    public boolean isValidForm() {
        return !TextUtils.isEmpty(getName())
                && !TextUtils.isEmpty(getEmail())
                && !TextUtils.isEmpty(getPassword());
    }

    @OnClick(R.id.register_button)
    public void register() {
        if (isValidForm()) {
            FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(getEmail(),getPassword())
                    .addOnCompleteListener(this);
        } else {
            Toast.makeText(getContext(),R.string.register_empty_form,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            onRegisterSuccess();
        } else {
            onRegisterFail(task);
        }
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(getContext(),R.string.register_success,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFail(@NonNull Task<AuthResult> task) {
        if (task.getException() != null && !TextUtils.isEmpty(task.getException().getMessage())) {
            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
