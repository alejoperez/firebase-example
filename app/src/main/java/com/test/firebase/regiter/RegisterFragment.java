package com.test.firebase.regiter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.test.firebase.R;
import com.test.firebase.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment implements IRegisterView {

    @Bind(R.id.register_edit_text_email)
    EditText editTextEmail;

    @Bind(R.id.register_edit_text_password)
    EditText editTextPassword;

    private IRegisterPresenter registerPresenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        loadData();
        return view;
    }

    @Override
    public void loadData() {
        registerPresenter = new RegisterPresenter(this);
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
        return  !TextUtils.isEmpty(getEmail()) && !TextUtils.isEmpty(getPassword());
    }

    @OnClick(R.id.register_button)
    public void register() {
        if (isValidForm()) {
            registerPresenter.register(getEmail(),getPassword());
        } else {
            Toast.makeText(getContext(),R.string.register_empty_form,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegisterSuccess() {
        goToMainActivity();
    }

    @Override
    public void onRegisterFail() {
        Toast.makeText(getContext(),R.string.register_fail,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
